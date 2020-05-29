package com.example.PRI.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Religion { //ToDo to musi być alfabetycznie
    SIGMAR("Sigmar", ""),
    ULRIC("Ulryk", ""),
    TAALRHYA("Taal&Rhya", ""),
    MYRMYDIA("Myrmydia", ""),
    SHALLYA("Shallya", ""),
    MORR("Morr", ""),
    RANALD("Ranald", ""),
    MANANN("Manann", ""),
    VERENA("Verena", ""),

    ASURYAN("Asuryan", ""),
    SARRIEL("Sarriel", ""),
    KHAINE("Khaine", ""),
    ISHA("Isha", ""),
    KURNOUS("Kurnous", ""),
    HOETH("Hoeth", ""),
    VAUL("Vaul", ""),
    LILEATH("Lileath", ""),
    MORAIHEG("Moraiheg", ""),
    MATHLANN("Mathlann", ""),

    GRUNGNI("Grungni", ""),
    GAZUL("Gazul", ""),
    GRIMNIR("Grimnir", ""),
    VALAYA("Valaya", ""),

    ESMERALDA("Esmeralda", ""),

    KHORNE("Khorne", ""),
    NURGLE("Nurgle", ""),
    SLAANESH("Slaanesh", ""),
    TZEENTCH("Tzeentch", ""),

    OLD_GODS("Stara Wiara", ""),
    NOONE("Brak bóstwa opiekuńczego", ""),
    OTHER("Inne", "");

    //ToDo frontend i backend muszą mieć tych samych bogów!

    String godName;
    String properties;

    public static Religion findByGodName(String name){
        Optional<Religion> output = Arrays.asList(Religion.values()).stream().filter(r -> r.godName.equals(name)).findFirst();
        return output.orElse(null);
    }

    public String getGodName(){
        return this.godName;
    }
    public String getProperties() {return this.properties;}
}
