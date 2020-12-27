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


    render() {
        return(
            <div class="registerTooltip">
                <Tooltip title={this.props.tooltipTextName} placement={"right-start"} arrow
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