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


    render() {
    return(
        <div class={"CoolTooltip"}>
            <Tooltip title={this.props.content} placement={"right-start"} arrow
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