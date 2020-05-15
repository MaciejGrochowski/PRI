package com.example.PRI.services.character.generator;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperJsonStringToMap {

    public static Map<String, String> mapJsonStringToMap(String properties) {
        return Arrays.stream(properties.split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
    }


}
