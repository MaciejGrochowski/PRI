package com.example.PRI.jsonDeserializationController;

import com.example.PRI.entities.Place;
import com.example.PRI.entities.character.*;
import com.example.PRI.services.PlaceService;
import com.example.PRI.services.character.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@RestController
public class DeserializationController {

    @Autowired
    NameService nameService;

    @Autowired
    SurnameService surnameService;

    @Autowired
    TalentService talentService;

    @Autowired
    SkillService skillService;

    @Autowired
    PredictionService predictionService;

    @Autowired
    HairColorService hairColorService;

    @Autowired
    EyeColorService eyeColorService;

    @Autowired
    ApperanceService apperanceService;

    @Autowired
    PersonalityService personalityService;

    @Autowired
    PlaceService placeService;

    @Autowired
    EmotionService emotionService;

    @Autowired
    CareerService careerService;

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    CareerSkillService careerSkillService;

    @Autowired
    CareerTalentService careerTalentService;


    final String path = "/home/janietakiork/projekt/PRI/Backend/";

    @RequestMapping("/json/name")
    public void nameDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = this.path + "src/jsons/name.json";
        String contents = Files.readString(Paths.get(path));
        List<Name> listName = objectMapper.readValue(contents, new TypeReference<List<Name>>() {
        });
        for (Name name : listName) {
            nameService.save(name);
        }
    }

    public static void main(String[] args) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String path = "src/jsons/name.json";
//        String contents = Files.readString(Paths.get(path));
//        List<Name> listName = objectMapper.readValue(contents, new TypeReference<List<Name>>() {
//        });
//
//        List<Name> humanMaleNames = listName.stream().filter(n -> n.isHuman() && n.isMale()).collect(Collectors.toList());
//        List<Name> humanFemaleNames = listName.stream().filter(n -> n.isHuman() && n.isFemale()).collect(Collectors.toList());
//        objectMapper.writeValue(new File("target/maleNames.json"), humanMaleNames);
//        objectMapper.writeValue(new File("target/femaleNames.json"), humanFemaleNames);

//        ArrayList<String> al = new ArrayList<String>();
//        al.add("kiasarin99@gmail.com");
//        System.out.println("start");
//        try {
//            EmailService es = new EmailService();
//            es.sendMail(al, "Temat", "text", "janietakiork@gmail.com");
//            System.out.println("poszło");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

        // ^^^
//        Random r = new Random(1234);
//        for(int i=0; i<4; i++) {
//        //    System.out.println(r.ints(2, 20).findFirst().getAsInt());
//            System.out.println(r.ints(2, 20).findFirst().getAsInt());
//        //System.err.println(r.doubles().findFirst().getAsDouble());
//        }
//        System.out.println(r.ints(2, 20).findFirst().getAsInt());
//
//

        //doubles().findFirst().getAsDouble()
//
//        List<Name> malePopularNames = listName.stream().filter(n -> n.isHuman() && n.getProbabilityNotGentry() > 0.01 && n.isMale()).collect(Collectors.toList());
//        List<Name> femalePopularNames = listName.stream().filter(n -> n.isHalfling() && n.getProbabilityNotGentry() > 0.01 && n.isFemale()).collect(Collectors.toList());
//        double malePopularChance = malePopularNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum();
//        double femalePopularChance = femalePopularNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum();
//
//        System.out.println(malePopularChance);
//        System.out.println(femalePopularChance);
//
//
//
//        List<Name> maleNames = listName.stream().filter(n -> n.isHalfling() && n.getProbabilityNotGentry() <= 0.01 && n.isMale()).collect(Collectors.toList());
//        List<Name> femaleNames = listName.stream().filter(n -> n.isHalfling() && n.getProbabilityNotGentry() <= 0.01 && n.isFemale()).collect(Collectors.toList());
//
//        double averageProbability = (1-malePopularChance)/maleNames.size();
//        Random generator = new Random();
//        DecimalFormat twoDForm = new DecimalFormat("#.####");
//
//
//        for (Name name: maleNames){
//        double probability = Double.parseDouble(twoDForm.format((averageProbability/2 + generator.nextDouble()*averageProbability*1.5)).replace(",", "."));
//        name.setUsedByGenerator(true);
//        name.setProbabilityNotGentry(probability);
//        }
//
//        while(maleNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum() + malePopularChance != 1){
//            double sum = maleNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum() + malePopularChance;
//            System.out.println(sum);
//            int randomIndex = generator.nextInt(maleNames.size());
//            Name name = maleNames.get(randomIndex);
//            if (sum > 1 && name.getProbabilityNotGentry() > 0.001) maleNames.get(randomIndex).setProbabilityNotGentry(name.getProbabilityNotGentry()- 0.0001);
//            if (sum < 1) maleNames.get(randomIndex).setProbabilityNotGentry(name.getProbabilityNotGentry() + 0.0001);
//        }
//
//        averageProbability = (1-femalePopularChance)/femaleNames.size();
//
//        for (Name name: femaleNames){
//            double probability = Double.parseDouble(twoDForm.format((averageProbability/2 + generator.nextDouble()*averageProbability*1.5)).replace(",", "."));
//            name.setUsedByGenerator(true);
//            name.setProbabilityNotGentry(probability);
//        }
//
//        while(femaleNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum() + femalePopularChance != 1){
//            double sum = femaleNames.stream().mapToDouble(Name::getProbabilityNotGentry).sum() + femalePopularChance;
//            System.out.println(sum);
//            int randomIndex = generator.nextInt(femaleNames.size());
//            Name name = femaleNames.get(randomIndex);
//            if (sum > 1 && name.getProbabilityNotGentry() > 0.001) femaleNames.get(randomIndex).setProbabilityNotGentry(name.getProbabilityNotGentry()- 0.0001);
//            if (sum < 1) femaleNames.get(randomIndex).setProbabilityNotGentry(name.getProbabilityNotGentry() + 0.0001);
//        }
//
//        List<Name> output = new ArrayList<>();
//        output.addAll(femaleNames);
//        output.addAll(maleNames);
//        output.addAll(malePopularNames);
//        output.addAll(femalePopularNames);
//
//        for (Name name: output){
//            name.setProbabilityNotGentry(Double.parseDouble(twoDForm.format(name.getProbabilityNotGentry()).replace(",", ".")));
//        }
//
//        objectMapper.writeValue(new File("target/halflingNames.json"), output);




    }

    @RequestMapping("/json/surname")
    public void surnameDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path +"src/jsons/surname.json";
        String contents = Files.readString(Paths.get(path));
        List<Surname> listSurname = objectMapper.readValue(contents, new TypeReference<List<Surname>>() {
        });
        for (Surname surName : listSurname) {
            surnameService.save(surName);
        }
    }

    @RequestMapping("/json/talent")
    public void talentDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/talent.json";
        String contents = Files.readString(Paths.get(path));
        List<Talent> listTalent = objectMapper.readValue(contents, new TypeReference<List<Talent>>() {
        });
        for (Talent talent : listTalent) {
            talentService.save(talent);
        }
    }

    @RequestMapping("/json/skill")
    public void skillDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/skill.json";
        String contents = Files.readString(Paths.get(path));
        List<Skill> listSkill = objectMapper.readValue(contents, new TypeReference<List<Skill>>() {
        });
        for (Skill skill : listSkill) {
            skillService.save(skill);
        }
    }

    @RequestMapping("/json/prediction")
    public void predictionDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/prediction.json";
        String contents = Files.readString(Paths.get(path));
        List<Prediction> listPrediction = objectMapper.readValue(contents, new TypeReference<List<Prediction>>() {
        });
        for (Prediction prediction : listPrediction) {
            predictionService.save(prediction);
        }
    }

    @RequestMapping("/json/haircolor")
    public void hairColorDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/hairColor.json";
        String contents = Files.readString(Paths.get(path));
        List<HairColor> listHairColor = objectMapper.readValue(contents, new TypeReference<List<HairColor>>() {
        });
        for (HairColor hairColor : listHairColor) {
            hairColorService.save(hairColor);
        }
    }

    @RequestMapping("/json/eyecolor")
    public void eyeColorDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/eyeColor.json";
        String contents = Files.readString(Paths.get(path));
        List<EyeColor> listEyeColor = objectMapper.readValue(contents, new TypeReference<List<EyeColor>>() {
        });
        for (EyeColor eyeColor : listEyeColor) {
            eyeColorService.save(eyeColor);
        }
    }

    @RequestMapping("/json/apperance")
    public void appearanceDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/appearance.json";
        String contents = Files.readString(Paths.get(path));
        List<Apperance> listAperance = objectMapper.readValue(contents, new TypeReference<List<Apperance>>() {
        });
        for (Apperance apperance : listAperance) {
            apperanceService.save(apperance);
        }
    }
    @RequestMapping("/json/personality")
    public void personalityDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/personality.json";
        String contents = Files.readString(Paths.get(path));
        List<Personality> listPersonality = objectMapper.readValue(contents, new TypeReference<List<Personality>>() {
        });
        for (Personality personality : listPersonality) {
            personalityService.save(personality);
        }
    }

    @RequestMapping("/json/place")
    public void placeDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/place.json";
        String contents = Files.readString(Paths.get(path));
        List<Place> listPlace = objectMapper.readValue(contents, new TypeReference<List<Place>>() {
        });
        for (Place place : listPlace) {
            placeService.save(place);
        }
    }

    @RequestMapping("/json/emotion")
    public void emotionDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String path = this.path + "src/jsons/emotion.json";
        String contents = Files.readString(Paths.get(path));
        List<Emotion> listEmotion = objectMapper.readValue(contents, new TypeReference<List<Emotion>>() {
        });
        for (Emotion emotion : listEmotion) {
            emotionService.save(emotion);
        }
    }

    @RequestMapping("/json/statistics")
    public void statisticsDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = this.path + "src/jsons/statistics.json";
        String contents = Files.readString(Paths.get(path));
        List<Statistics> listStatistics = objectMapper.readValue(contents, new TypeReference<List<Statistics>>() {
        });
        for (Statistics statistics : listStatistics) {
            statisticsService.save(statistics);
        }
    }

    @RequestMapping("/json/career")
    public void carrierDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = this.path + "src/jsons/career_base.json";
        String contents = Files.readString(Paths.get(path));
        List<Career> listCareer = objectMapper.readValue(contents, new TypeReference<List<Career>>() {
        });
        for (Career career : listCareer) {
            careerService.save(career);
        }
        String path2 = this.path + "src/jsons/career_full.json";
        String contents2 = Files.readString(Paths.get(path2));
        List<Career> listCareer2 = objectMapper.readValue(contents2, new TypeReference<List<Career>>() {
        });
        for (Career career : listCareer2) {
            careerService.save(career);
        }
    }

    @RequestMapping("/json/career_skill")
    public void carrierSkillDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = this.path + "src/jsons/career_skill.json";
        String contents = Files.readString(Paths.get(path));
        List<CareerSkill> listCareerSkill = objectMapper.readValue(contents, new TypeReference<List<CareerSkill>>() {
        });
        for (CareerSkill careerSkill : listCareerSkill) {
            careerSkillService.save(careerSkill);
        }
    }

    @RequestMapping("/json/career_talent")
    public void carrierTalentDeserializationAndDatabaseUpdate() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String path = this.path + "src/jsons/career_talent.json";
        String contents = Files.readString(Paths.get(path));
        List<CareerTalent> listCareerTalent = objectMapper.readValue(contents, new TypeReference<List<CareerTalent>>() {
        });
        for (CareerTalent careerTalent : listCareerTalent) {
            careerTalentService.save(careerTalent);
        }
    }

    @RequestMapping("/json/all")
    public void UpdateAll() throws IOException {
        nameDeserializationAndDatabaseUpdate();
        surnameDeserializationAndDatabaseUpdate();
        talentDeserializationAndDatabaseUpdate();
        skillDeserializationAndDatabaseUpdate();
        predictionDeserializationAndDatabaseUpdate();
        hairColorDeserializationAndDatabaseUpdate();
        eyeColorDeserializationAndDatabaseUpdate();
        appearanceDeserializationAndDatabaseUpdate();
        personalityDeserializationAndDatabaseUpdate();
        placeDeserializationAndDatabaseUpdate();
        emotionDeserializationAndDatabaseUpdate();
        statisticsDeserializationAndDatabaseUpdate();
        carrierDeserializationAndDatabaseUpdate();
        carrierSkillDeserializationAndDatabaseUpdate();
        carrierTalentDeserializationAndDatabaseUpdate();
    }
}

