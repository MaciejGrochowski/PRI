import React from 'react';
import { Tooltip } from '@material-ui/core';
import { IconButton } from '@material-ui/core';
import InfoOutlinedIcon from '@material-ui/icons/InfoOutlined';
import {actualPlaceText, birthYearText, nameText, raceText, emotionText, religionText, skillText,
    characterText, professionText, appearanceText} from "./tooltipMethods";

class GeneratorTooltip extends React.Component {

    constructor(probs) {
        super(probs);
        this.state = {

        }
    }

    tooltipTextGenerator(typeName){
        switch(typeName) {
            case 'Imię':
                return nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute,this.props.content)
            case 'Nazwisko':
                return nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute,this.props.content)
            case 'Wzrost':
                return nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute,this.props.content)
            case 'Waga':
                return nameText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute,this.props.content)
            case 'Rok urodzenia':
                return birthYearText(this.props.raceTooltipAtribute,this.props.content)
            case 'Rasa':
                return raceText(this.props.birthPlaceTooltipAtribute,this.props.content)
            case 'Płeć':
                return birthYearText(this.props.raceTooltipAtribute,this.props.content)
            case 'Kolor włosów':
                return birthYearText(this.props.raceTooltipAtribute,this.props.content)
            case 'Kolor oczu':
                return birthYearText(this.props.raceTooltipAtribute,this.props.content)
            case 'Zdolności':
                return skillText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,this.props.content)
            case 'Umiejętności':
                return skillText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,this.props.content)
            case 'Dominujące emocje':
                return emotionText(this.props.raceTooltipAtribute,this.props.yearOfBirthTooltipAtribute,this.props.content)
            case 'Religia':
                return religionText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,
                    this.props.sexTooltipAtribute,this.props.birthPlaceTooltipAtribute,this.props.content)
            case 'Miejsce pobytu':
                return actualPlaceText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,
                    this.props.sexTooltipAtribute,this.props.birthPlaceTooltipAtribute,this.props.religionTooltipAtribute,this.props.content)
            case 'Cechy charakteru':
                return characterText(this.props.raceTooltipAtribute,this.props.professionTooltipAtribute,
                    this.props.yearOfBirthTooltipAtribute,this.props.religionTooltipAtribute, this.props.content)
            case 'Profesja':
                return professionText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute,
                    this.props.birthPlaceTooltipAtribute,this.props.baseStatsTooltipAtributet,this.props.content)
            case 'Cechy wyglądu':
                return appearanceText(this.props.raceTooltipAtribute,this.props.sexTooltipAtribute,this.props.professionTooltipAtribute,
                    this.props.yearOfBirthTooltipAtribute,this.props.weightTooltipAtributet,this.props.weightTooltipAtributet
                    ,this.props.religionTooltipAtribute,this.props.content)
            case 'Poprzednie profesje':
                return this.props.content
            default:
                return 'dont work';
        }
    }


    render() {
        let cool="";
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