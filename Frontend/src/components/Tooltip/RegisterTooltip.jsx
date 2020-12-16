import React from 'react';
import { Tooltip } from '@material-ui/core';
import { IconButton } from '@material-ui/core';
import InfoOutlinedIcon from '@material-ui/icons/InfoOutlined';
import {textsPolish} from "../../commons/texts-pl";

class RegisterTooltip extends React.Component {

    constructor(probs) {
        super(probs);
        this.state = {

        }
    }

    tooltipTextGenerator(typeName){
        switch(typeName) {
            case 'username':
                return
            case 'password':
                return
            case 'facebookLink':
                return
            case 'discord':
                return
            case 'description':
                return
            default:
                return 'dont work';
        }
    }


    render() {
        return(
            <div className={"registerTooltip"}>
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

export default RegisterTooltip;