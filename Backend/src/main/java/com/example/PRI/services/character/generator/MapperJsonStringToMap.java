package com.example.PRI.services.character.generator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapperJsonStringToMap {

    public static Map<String, String> mapJsonStringToMap(String properties) {
        if(!properties.contains(":")) return new HashMap<>();
//        List<String> tmp = Arrays.asList(properties.split(","));
//        Map<String, String> map = new HashMap<>();
//        for (String s : tmp){
//            List<String> keyValue = Arrays.asList(s.split(":"));
//            map.put(keyValue.get(0), keyValue.get(1));
//        }
//        return map;
//Useful for debugger but slower bcs of not streaming
        Stream<String> tmp1 = Arrays.stream(properties.split(","));
        Stream<String[]> tmp2 = tmp1.map(s -> s.split(":"));
        String tmp4 = tmp2.toString();
        try{
            Map<String, String> tmp3 = tmp2.collect(Collectors.toMap(s -> s[0], s -> s[1]));
            return Arrays.stream(properties.split(","))
                    .map(s -> s.split(":"))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        }
        catch (Exception e){
            System.err.println("Error mapStringToMap with properties: ");
            System.err.println(properties);
            return new HashMap<>();
        }
    }


}
