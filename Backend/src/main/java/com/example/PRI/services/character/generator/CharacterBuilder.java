package com.example.PRI.services.character.generator;

import com.example.PRI.entities.Place;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.example.PRI.entities.character.Character;

import java.util.HashMap;
import java.util.Map;

import static com.example.PRI.services.character.generator.MapperJsonStringToMap.mapJsonStringToMap;

@NoArgsConstructor
@AllArgsConstructor
public class CharacterBuilder {

    private Character character;
    private HashMap<String, String> properties;

    public CharacterBuilder initialize(){
        character = new Character();
        properties = new HashMap<>();
        return this;
    }

    public Character getCharacter(){
        return character;
    }

    public HashMap<String, String> getProperties(){
        return properties;
    }


    public CharacterBuilder buildBirthPlace(CharacterBirthPlaceGenerator service){
        HashMap<String, String> newProps = service.generateBirthPlace(character);
        this.putAllProperties(newProps);
        Place parent = character.getBirthPlace().getParent();
        while(parent != null){
            this.putAllProperties(mapJsonStringToMap(parent.getProperties()));
            parent = parent.getParent();
        }

        return this;
    }

    public CharacterBuilder buildRace(RaceGenerator service){
        Map<String, String> newProps = service.generateRace(character, properties);

        return this;
    }


    private void putAllProperties(Map<String, String> newProps){
        for(String key: newProps.keySet()){
            if(!this.properties.containsKey(key)){
                this.properties.put(key, newProps.get(key));
            }
            else{
                if(this.properties.get(key).equals("0") || newProps.get(key).equals("0")){
                    this.properties.put(key, "0");
                    continue;
                }
                try{
                    Double oldPropValue = Double.parseDouble(properties.get(key));
                    Double newPropValue = Double.parseDouble(newProps.get(key));
                    this.properties.put(key, String.valueOf(oldPropValue+newPropValue));
                }
                catch(NumberFormatException e){
                    System.err.println(key);
                    System.err.println(properties.get(key));
                    System.err.println(newProps.get(key));
                    System.err.println("Błąd przy łączeniu propertiesów!");
                }
            }
        }


    }



}
