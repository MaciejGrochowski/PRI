package com.example.PRI.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Religion {
    ASURYAN("Asuryan", ""),
    ESMERALDA("Esmeralda", ""),
    GAZUL("Gazul", ""),
    GRIMNIR("Grimnir", ""),
    GRUNGNI("Grungni", ""),
    HOETH("Hoeth", ""),
    ISHA("Isha", ""),
    KHAINE("Khaine", ""),
    KHORNE("Khorne", ""),
    KURNOUS("Kurnous", ""),
    LILEATH("Lileath", ""),
    MANANN("Manann", ""),
    MATHLANN("Mathlann", ""),
    MORAIHEG("Moraiheg", ""),
    MORR("Morr", ""),
    MYRMYDIA("Myrmydia", ""),
    NOONE("Brak bóstwa opiekuńczego", ""),
    NURGLE("Nurgle", ""),
    OLD_GODS("Stara Wiara", ""),
    RANALD("Ranald", ""),
    SARRIEL("Sarriel", ""),
    SHALLYA("Shallya", ""),
    SIGMAR("Sigmar", ""),
    SLAANESH("Slaanesh", ""),
    TAALRHYA("Taal&Rhya", ""),
    TZEENTCH("Tzeentch", ""),
    ULRIC("Ulryk", ""),
    VALAYA("Valaya", ""),
    VAUL("Vaul", ""),
    VERENA("Verena", ""),
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
