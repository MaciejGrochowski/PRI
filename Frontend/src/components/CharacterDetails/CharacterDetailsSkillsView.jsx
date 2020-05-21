
import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import list from "../../styles/globalStyles.css";

class CharacterDetailsSkillsView extends React.Component {

    render(){
        const {data, title} = this.props;
        return (
            <div className = "standard-component">
                <div className="sub-title">{title}</div>
                <div className="list-decoration">
                {data !== undefined && data.map((item, i) => (
                    <div className = "skill">
                        <div className = "skill-name">{item.name}</div>
                        {item.level ? <div className = "skill-lvl"> &nbsp;&nbsp;+{item.level}</div> : ""}
                    </div>

                ))
                }

            </div>
            </div>
        )
    }

}

export default CharacterDetailsSkillsView;