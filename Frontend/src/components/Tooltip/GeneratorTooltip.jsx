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
            case 'Rasa':
                return this.raceText(this.props.birthPlaceTooltipAtribute)
            default:
                return 'foo';
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

    raceText(showBirthPlace){
        let textContent = this.props.content
        if(showBirthPlace){
            return textContent += "miejsca urodzenia"
        }
        else {
            return textContent
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