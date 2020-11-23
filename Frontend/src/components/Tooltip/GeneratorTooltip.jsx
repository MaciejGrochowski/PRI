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
                return this.nameText(this.props.nameTooltipAtribute,this.props.sexTooltipAtribute)
            default:
                return 'foo';
        }
    }

//TODO dorobić reszte inteaktywnych tooltipów
    nameText(showName, showSex){
        let textContent = this.props.content
        if (showName && showSex){
            return textContent += "rasy, płci"
        }
        else if(showName && !showSex){
            return textContent += "rasy"
        }
        else if(!showName && showSex){
            return textContent += "płci"
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