
import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";
import list from "../../styles/globalStyles.css";

class CharacterDetailsSkillsView extends React.Component {

    render(){
        const {data, title} = this.props;
        console.log(data);
        return (
            <div className = "standard-component">
                <div className="sub-title">{title}</div>
                <div className="list-decoration">
                {data !== undefined && data.map((item, i) => (
                    <div className = "skill-name">
                        <span>{item.name}</span>
                        {item.level ? <span>{item.level}</span> : ""}
                    </div>

                ))
                }

            </div>
            </div>
        )
    }

}

export default CharacterDetailsSkillsView;