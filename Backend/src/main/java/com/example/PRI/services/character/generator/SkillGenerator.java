package com.example.PRI.services.character.generator;

import com.example.PRI.entities.character.Career;
import com.example.PRI.entities.character.CareerSkill;
import com.example.PRI.entities.character.Character;
import com.example.PRI.entities.character.Skill;
import com.example.PRI.enums.Race;
import com.example.PRI.services.GeneralService;
import com.example.PRI.services.character.CareerSkillService;
import com.example.PRI.services.character.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SkillGenerator extends GeneralService {

    @Autowired
    SkillService skillService;

    @Autowired
    CareerSkillService careerSkillService;

    public Map<String, String> generateSkills(Character character, HashMap<String, String> properties) {
        List<Skill> skills = skillService.findAll();
        List<String> skillNames = getRaceSkills(character.getRace());
        Random rand = new Random();

        for(Skill skill : skills.stream().filter(s -> s.getLevel().equals(0)).collect(Collectors.toList())){
            if(properties.containsKey(skill.getName())){
                if(rand.nextDouble() < Double.parseDouble(properties.get(skill.getName()))){
                    skillNames.add(skill.getName());
                }
            }
        }

        if(character.getPreviousCareers().size() == 0) skillNames.addAll(generateSkillNamesFromCareer(character.getCurrentCareer()));
        else{
            for(Career career: character.getPreviousCareers()){
                skillNames.addAll(generateSkillNamesFromCareer(career));
            }
            skillNames.addAll(generateSomeSkillNamesFromCareer(character.getCurrentCareer()));
        }
        skillNames.addAll(generateTotallyRandomSkill(skills));
        List<String> skillNamesDistinct = skillNames.stream().distinct().collect(Collectors.toList());
        List<Skill> characterSkills = new ArrayList<>();

        for(String skillName : skillNamesDistinct){
            Skill skillToAdd = null;
            long count = skillNames.stream().filter(s -> s.equals(skillName)).count();
            if(count==1) skillToAdd = skills.stream().filter(s -> s.getLevel()==0 && s.getName().equals(skillName)).collect(Collectors.toList()).get(0);
            if(count==2) skillToAdd = skills.stream().filter(s -> s.getLevel()==10 && s.getName().equals(skillName)).collect(Collectors.toList()).get(0);
            if(count>=3) skillToAdd = skills.stream().filter(s -> s.getLevel()==20 && s.getName().equals(skillName)).collect(Collectors.toList()).get(0);
            if(!(skillToAdd == null)) characterSkills.add(skillToAdd);
        }
        character.setSkills(characterSkills);
        return new HashMap<>();
    }

    private List<String> generateSomeSkillNamesFromCareer(Career career) {
        List<CareerSkill> careerSkills = careerSkillService.findByCareer(career);
        List<Integer> types = careerSkills.stream().map(CareerSkill::getType).distinct().collect(Collectors.toList());
        List<String> output = new ArrayList<>();
        for(int type: types){
            if(new Random().nextDouble() < 0.5) continue;
            List<CareerSkill> skillsToChosen = careerSkills.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
            CareerSkill chosenSkill = this.chooseSkill(skillsToChosen);
            if(chosenSkill==null) continue;
            output.add(chosenSkill.getSkill().getName());
        }
        return output;

    }

    private List<String> generateSkillNamesFromCareer(Career career) {
        List<CareerSkill> careerSkills = careerSkillService.findByCareer(career);
        List<Integer> types = careerSkills.stream().map(CareerSkill::getType).distinct().collect(Collectors.toList());
        List<String> output = new ArrayList<>();
        for(int type: types){
            List<CareerSkill> skillsToChosen = careerSkills.stream().filter(c -> c.getType() == type).collect(Collectors.toList());
            CareerSkill chosenSkill = this.chooseSkill(skillsToChosen);
            if(chosenSkill==null) continue;;
            output.add(chosenSkill.getSkill().getName());
        }
        return output;
    }

    private CareerSkill chooseSkill(List<CareerSkill> skillsToChosen) {
        double maxRoll = skillsToChosen.stream().mapToDouble(CareerSkill::getProbability).sum();
        double randomRoll = new Random().nextDouble() * maxRoll;

        for(CareerSkill skill : skillsToChosen){
            randomRoll-=skill.getProbability();
            if(randomRoll <= 0) return skill;
        }
        return null;
    }

    private List<String> getRaceSkills(Race race) {
        List<String> output = new ArrayList<>();

        output.add("Znajomość języka (Staroświatowy)");

        if(race.equals(Race.HUMAN)){
            output.add("Wiedza (Imperium)");
            output.add("Plotkowanie");
        }
        if(race.equals(Race.ELF)){
            output.add("Wiedza (elfy)");
            output.add("Znajomość języka (Eltharin)");
        }
        if(race.equals(Race.DWARF)){
            output.add("Wiedza (krasnoludy)");
            output.add("Znajomość języka (Khazalid)");
            double randomRoll = new Random().nextDouble();
            if(randomRoll <= 1.0/3.0) output.add("Rzemiosło (górnictwo)");
            else if (randomRoll <= 2.0/3.0) output.add("Rzemiosło (kamieniarstwo)");
            else output.add("Rzemiosło (kowalstwo)");
        }
        if(race.equals(Race.HALFLING)){
            output.add("Nauka (genealogia/heraldyka)");
            if(new Random().nextDouble() < 0.5) output.add("Rzemiosło (gotowanie)");
            else output.add("Rzemiosło (uprawa ziemi)");
            output.add("Plotkowanie");
            output.add("Wiedza (niziołki)");
            output.add("Znajomość języka (niziołków)");
        }
        return output;
    }


    private List<String> generateTotallyRandomSkill(List<Skill> skills) {
        List<Skill> skillsZeroLevel = skills.stream().filter(s -> s.getLevel()==0).collect(Collectors.toList());
        int countTotallyRandomSkills = getCountTotallyRandomSkills();
        List<String> output = new ArrayList<>();
        while(output.size() < countTotallyRandomSkills){
            Skill skillToAdd = skillsZeroLevel.get(new Random().nextInt(skillsZeroLevel.size()));
            if(!output.contains(skillToAdd.getName())) output.add(skillToAdd.getName());
        }
        return output;
    }

    private int getCountTotallyRandomSkills() {
        double randomRoll = new Random().nextDouble();
        if(randomRoll < 0.85) return 0;
        if(randomRoll < 0.95) return 1;
        if(randomRoll < 0.99) return 2;
        return 3;
    }
}
