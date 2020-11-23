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

    render() {
        let tooltipClassName = 'CoolTooltip';
        if (this.props.showIt) {
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