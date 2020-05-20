package com.example.PRI.services.character.generator;

import com.example.PRI.entities.ImperialDate;
import com.example.PRI.entities.character.Character;
import com.example.PRI.enums.Month;

import java.util.*;

public class BirthDateGenerator {

    private static final int defaultGameYear = 2522;

    public Map<String, String> generateBirthDate(Character character, HashMap<String, String> properties) {
        double randomRoll = new Random().nextDouble();
        if (randomRoll < 0.7) return this.generateAdultCharacter(character, properties);
        else if(randomRoll < 0.9) return this.generateElderCharacter(character, properties);
        else return this.generateOldCharacter(character, properties);
    }

    private Map<String, String> generateOldCharacter(Character character, HashMap<String, String> properties) {
        Map<String, String> output = new HashMap<>();
        output.put("Włosy siwe", "0.5");
        output.put("Włosy popielate", "0.4");
        output.put("Spracowane dłonie", "0.05");
        output.put("Charczący głos", "0.04");
        output.put("Skrzekliwy głos", "0.02");
        output.put("Bielmo na oku", "0.02");
        output.put("Przekrwione oczy", "0.01");
        output.put("Przerzedzone włosy", "0.03");
        output.put("Łysina na czubku głowy", "0.06");
        output.put("Łysy", "0.1");
        output.put("Obwisła skóra", "0.05");
        output.put("Wory pod oczami", "0.05");
        output.put("Zmarszczki", "0.4");
        output.put("Zasuszony", "0.2");
        output.put("Brak zęba na przedzie", "0.1");
        output.put("Szczerbaty", "0.1");
        output.put("Dziewiczy wąs", "0");
        output.put("Dziwna woń ciała", "0.05");
        output.put("Chorobliwy kaszel", "0.15");
        output.put("Ciągłe ślinienie się", "0.01");
        output.put("Dziecinny wygląd", "0");
        output.put("Puszcza smrodliwe wiatry", "0.02");
        output.put("Utykanie", "0.1");
        output.put("Akceptacja", "0.05");
        output.put("Uspokojenie", "0.05");
        output.put("Refleksja", "0.01");
        output.put("Uduchowienie", "0.04");
        output.put("Tęsknota", "0.04");
        output.put("Marudny", "0.04");
        output.put("Nietolerancyjny", "0.02");
        output.put("Niezdarny", "0.01");
        output.put("Ospały", "0.02");
        output.put("Rozważny", "0.01");
        output.put("Szalony", "0.01");
        output.put("Zapominalski", "0.06");
        output.put("Stary", "1");
        Integer minimumAge = Integer.parseInt(properties.get("oldAge"));
        Integer maximumAge = Integer.parseInt(properties.get("maxAge"));

        Integer yearOfBirth = defaultGameYear - minimumAge - new Random().nextInt(maximumAge-minimumAge);
        return generateBirthDayMonthYear(character, yearOfBirth, output);
    }

    private Map<String, String> generateElderCharacter(Character character, HashMap<String, String> properties) {
        Map<String, String> output = new HashMap<>();
        output.put("Włosy siwe", "0.3");
        output.put("Włosy popielate", "0.3");
        output.put("Spracowane dłonie", "0.02");
        output.put("Charczący głos", "0.01");
        output.put("Skrzekliwy głos", "0.01");
        output.put("Bielmo na oku", "0.01");
        output.put("Przekrwione oczy", "0.01");
        output.put("Przerzedzone włosy", "0.03");
        output.put("Łysina na czubku głowy", "0.04");
        output.put("Łysy", "0.05");
        output.put("Obwisła skóra", "0.02");
        output.put("Wory pod oczami", "0.02");
        output.put("Zmarszczki", "0.2");
        output.put("Zasuszony", "0.1");
        output.put("Brak zęba na przedzie", "0.05");
        output.put("Szczerbaty", "0.05");
        output.put("Dziewiczy wąs", "0");
        output.put("Dziwna woń ciała", "0.02");
        output.put("Chorobliwy kaszel", "0.05");
        output.put("Ciągłe ślinienie się", "0.005");
        output.put("Dziecinny wygląd", "0");
        output.put("Puszcza smrodliwe wiatry", "0.01");
        output.put("Utykanie", "0.05");
        output.put("Akceptacja", "0.02");
        output.put("Uspokojenie", "0.02");
        output.put("Refleksja", "0.005");
        output.put("Uduchowienie", "0.02");
        output.put("Tęsknota", "0.02");
        output.put("Marudny", "0.02");
        output.put("Nietolerancyjny", "0.01");
        output.put("Niezdarny", "0.005");
        output.put("Ospały", "0.005");
        output.put("Rozważny", "0.005");
        output.put("Szalony", "0.005");
        output.put("Zapominalski", "0.03");
        output.put("Stary", "0.4");
        Integer minimumAge = Integer.parseInt(properties.get("elderAge"));
        Integer maximumAge = Integer.parseInt(properties.get("oldAge"));
        Integer yearOfBirth = defaultGameYear - minimumAge - new Random().nextInt(maximumAge-minimumAge);
        return generateBirthDayMonthYear(character, yearOfBirth, output);
    }

    private Map<String, String> generateAdultCharacter(Character character, HashMap<String, String> properties) {
        Integer minimumAge = Integer.parseInt(properties.get("adultAge"));
        Integer maximumAge = Integer.parseInt(properties.get("elderAge"));
        Integer yearOfBirth = defaultGameYear - minimumAge - new Random().nextInt(maximumAge-minimumAge);
        Map<String, String> output = new HashMap<>();
        output.put("Młody", "0.1");
        return generateBirthDayMonthYear(character, yearOfBirth, output);
    }

    private Map<String, String> generateBirthDayMonthYear(Character character, Integer yearOfBirth, Map<String, String> output) {
        List<Month> months = Arrays.asList(Month.values());
        Month monthOfBirth = months.get(new Random().nextInt(months.size()));
        Integer dayOfBirth = new Random().nextInt(this.getDaysOfMonth(monthOfBirth));
        character.setBirthDate(new ImperialDate(dayOfBirth, monthOfBirth, yearOfBirth));
        return output;
    }

    private Integer getDaysOfMonth(Month monthOfBirth) {
        Integer days = null;

        switch (monthOfBirth) {
            case KALDEZEIT:
            case JAHRDRUNG:
            case SIGMARZEIT:
            case SOMMERZEIT:
            case NACHGEHEIM:
            case ERNTEZEIT:
            case ULRICZEIT:
            case NACHEXEN:
                days = 33;
                break;
            case PFLUGZEIT:
            case VORGEHEIM:
            case BRAUZEIT:
            case VORHEXEN:
                days = 34;
                break;
        }
        return days;
    }
}
