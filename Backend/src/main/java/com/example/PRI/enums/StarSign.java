package com.example.PRI.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum StarSign {
    WYMUND_THE_ANCHORITE("Wymund the Anchorite", "Wymenos", "Sign of Enduring",	"22/12", "7/1"),
    THE_BIG_CROSS("The Big Cross", "Azurins", "Sign of Clarity", "8/1", "27/1"),
    THE_LIMNERS_LINE("The Limner's Line", "Verros", "Sign of Precision", "28/1", "15/2"),
    GNUTHUS_THE_OX("Gnuthus the Ox", "Nuthios", "Sign of Dutiful Service", "16/2", "1/3"),
    DRAGOMAS_THE_DRAKE("Dragomas the Drake", "Drakonos", "Sign of Courage", "2/3", "21/3"),
    THE_GLOAMING("The Gloaming", "Tarotes", "Sign of Illusion and Mystery", "22/3", "8/4"),
    GRUNGNIS_BALDRIC("Grungni's Baldric", "Gileon", "Sign of Martial Pursuits", "9/4", "15/5");
//    Mammit the Wise 	Mammius 	Sign of Wisdom 	29/4 - 15/5
//    Mummit the Fool 	Mammios 	Sign of the Indistinct 	16/5 - 1/6
//    The Two Bullocks 	Hashoor 	Sign of Fertility and Craftsmanship 	2/6 - 21/6
//    The Dancer 	Adamnos 	Sign of Love and Attraction 	22/6 - 7/7
//    The Drummer 	Lupios 	Sign of Excess and Hedonism 	8/7 - 27/7
//    The Piper 	Sangist 	Sign of the Trickster 	28/7 - 15/8
//    Vobist the Faint 	Vobist 	Sign of Darkness and Uncertainty 	16/8 - 1/9
//    The Broken Cart 	Kharnos 	Sign of Pride 	2/9 - 21/9
//    The Greased Goat 	Talios 	Sign of Denied Passions 	22/9 - 8/10
//    Rhya's Cauldron 	Rionyes 	Sign of Mercy, Death, and Creation 	9/10 - 28/10
//    Cacklefax the Cockerel 	Kakeros 	Sign of Money and Merchants 	29/10 - 15/11
//    The Bone Saw (Grimoire) 	Alyoi 	Sign of Skill and Learning 	16/11 - 1/12
//    The Witchling Star 	Solkios 	Sign of Magic 	2/12 - 21/12
//




    private String name;
    private String shortName;
    private String description;
    private String startDate;
    private String endDate;

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
