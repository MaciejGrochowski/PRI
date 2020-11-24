import React from 'react';
import { Tooltip } from '@material-ui/core';
import { IconButton } from '@material-ui/core';
import InfoOutlinedIcon from '@material-ui/icons/InfoOutlined';

class GeneratorTooltip extends React.Component {

    constructor(probs) {
        super(probs);
        this.state = {

        }
    }

    tooltipTextGenerator(typeName){
        switch(typeName) {
            case 'Imię':
                return this.nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute)
            case 'Nazwisko':
                return this.nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute)
            case 'Wzrost':
                return this.nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute)
            case 'Waga':
                return this.nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute)
            case 'Rok urodzenia':
                return this.birthYearText(this.props.raceTooltipAtribute)
            case 'Rasa':
                return this.raceText(this.props.birthPlaceTooltipAtribute)
            case 'Płeć':
                return this.birthYearText(this.props.raceTooltipAtribute)
            case 'Kolor włosów':
                return this.birthYearText(this.props.raceTooltipAtribute)
            case 'Kolor oczu':
                return this.birthYearText(this.props.raceTooltipAtribute)
            case 'Zdolności':
                return this.skillText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute)
            case 'Umiejętności':
                return this.skillText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute)
            case 'Dominujące emocje':
                return this.emotionText(this.props.raceTooltipAtribute,this.props.yearOfBirthTooltipAtribute)
            case 'Religia':
                return this.religionText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,
                    this.props.sexTooltipAtribute,this.props.birthPlaceTooltipAtribute)
            case 'Miejsce pobytu':
                return this.actualPlaceText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,
                    this.props.sexTooltipAtribute,this.props.birthPlaceTooltipAtribute,this.props.religionTooltipAtribute)
            case 'Poprzednie profesje':
                return this.props.content
            default:
                return 'dont work';
        }
    }

//TODO dorobić reszte inteaktywnych tooltipów
    nameText(showRace, showSex){
        let textContent = this.props.content
        if (showRace && showSex){
            return textContent += "rasy, płci"
        }
        else if(showRace && !showSex){
            return textContent += "rasy"
        }
        else if(!showRace && showSex){
            return textContent += "płci"
        }
    }

    birthYearText(showBirthYear){
        let textContent = this.props.content
        if(showBirthYear){
            return textContent += "rasy"
        }
    }

    raceText(showRace){
        let textContent = this.props.content
        if(showRace){
            return textContent += "miejsca urodzenia"
        }
    }

    skillText(showRace, showProfession){
        let textContent = this.props.content
        if (showRace && showProfession){
            return textContent += "rasy, profesji"
        }
        else if(showRace && !showProfession){
            return textContent += "rasy"
        }
        else if(!showRace && showProfession){
            return textContent += "profesji"
        }
    }

    emotionText(showRace, showBirthYear){
        let textContent = this.props.content
        if (showRace && showBirthYear){
            return textContent += "rasy, roku urodzenia"
        }
        else if(showRace && !showBirthYear){
            return textContent += "rasy"
        }
        else if(!showRace && showBirthYear){
            return textContent += "roku urodzenia"
        }
    }

    religionText(showRace, showProfession, showSex, showBirthPlace){
        let textContent = this.props.content
        if (showRace && !(showSex || showProfession || showBirthPlace)){ return textContent += "rasy"}
        else if(showProfession && !(showRace || showSex || showBirthPlace)) return textContent += "profesji"
        else if(showSex && !(showRace || showProfession || showBirthPlace)) return textContent += "płci"
        else if(showBirthPlace && !(showRace || showProfession || showSex)) return textContent += "miejsca urodzenia"
        if (showRace && (showSex || showProfession || showBirthPlace)) textContent += "rasy, "
        if (showSex && (showProfession || showBirthPlace)) textContent += "płci, "
        if (showProfession && showBirthPlace) textContent += "profesji, "
        if (showBirthPlace) textContent += "miejsca urodzenia"
        if (showRace && showSex && !(showBirthPlace || showProfession)) textContent += "płci"
        if ((showProfession && showRace && !(showBirthPlace || showSex)) || (showProfession && showSex && !(showRace || showBirthPlace))) textContent += "profesji"
        return textContent
    }

    //TODO do sprawdzenia!!!!!
    actualPlaceText(showRace, showProfession, showSex, showBirthPlace, showReligion){
        let textContent = this.props.content
        if (showRace && !(showSex || showProfession || showBirthPlace || showReligion)) return textContent += "rasy"
        else if(showProfession && !(showRace || showSex || showBirthPlace || showReligion)) return textContent += "profesji"
        else if(showSex && !(showRace || showProfession || showBirthPlace || showReligion)) return textContent += "płci"
        else if(showBirthPlace && !(showRace || showProfession || showSex || showReligion)) return textContent += "miejsca urodzenia"
        else if(showReligion && !(showRace || showProfession || showSex || showBirthPlace)) return textContent += "relgii"
        if (showRace && (showSex || showProfession || showBirthPlace || showReligion)) textContent += "rasy, "
        if (showSex && (showProfession || showBirthPlace || showReligion)) textContent += "płci, "
        if (showProfession && (showBirthPlace || showReligion)) textContent += "profesji, "
        if (showBirthPlace && showReligion) textContent += "miejsca urodzenia, "
        if (showReligion) textContent += "religii"
        if (showRace && showSex && !(showBirthPlace || showProfession)) textContent += "płci"
        if ((showProfession && showRace && !(showBirthPlace || showSex)) || (showProfession && showSex && !(showRace || showBirthPlace))) textContent += "profesji"
        return textContent
    }

    render() {
        let tooltipClassName = 'CoolTooltip';
        if (this.props.showIt && this.props.tooltipTypeName!=="Poprzednie profesje") {
            tooltipClassName += '-NonActive';
        }
    return(
        <div className={tooltipClassName}>
            <Tooltip title={this.tooltipTextGenerator(this.props.tooltipTypeName)} placement={"right-start"} arrow
                     classes={{ label: 'myTooltip' }}>
                <IconButton aria-label="info">
                    <InfoOutlinedIcon />
                </IconButton>
            </Tooltip>
        </div>
        )
    }
}

export default GeneratorTooltip;