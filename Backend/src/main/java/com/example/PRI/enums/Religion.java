package com.example.PRI.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Religion { //ToDo to musi być alfabetycznie
    SIGMAR("Sigmar"),
    ULRIC("Ulryk"),
    TAALRHYA("Taal&Rhya"),
    MYRMYDIA("Myrmydia"),
    SHAYILA("Shalyia"),
    MORR("Morr"),
    RANALD("Ranald"),
    MANANN("Manann"),
    VERENA("Verena"),

    ASURYAN("Asuryan"),
    KHAINE("Khaine"),
    ISHA("Isha"),
    KURNOUS("Kurnous"),
    HOETH("Hoeth"),
    VAUL("Vaul"),
    LILEATH("Lileath"),
    MORAIHEG("Moraiheg"),
    MATHLANN("Mathlann"),

    GRUNGNI("Grungni"),
    GRIMNIR("Grimnir"),
    VALAYA("Valaya"),

    KHORNE("Khorne"),
    NURGLE("Nurgle"),
    SLAANESH("Slaanesh"),
    TZEENTCH("Tzeentch"),

    OLD_GODS("Stara Wiara"),
    NOONE("Brak bóstwa opiekuńczego"),
    OTHER("Inne");

    String godName;

    public static Religion findByGodName(String name){
        Optional<Religion> output = Arrays.asList(Religion.values()).stream().filter(r -> r.godName.equals(name)).findFirst();
        return output.orElse(null);
    }

    public String getGodName(){
        return this.godName;
    }
}
