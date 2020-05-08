
import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";


class CharacterDetailsSkillsView extends React.Component {

    render(){
        const {data, title} = this.props;
        console.log(data);
        return (
            <div>
                <div>{title}</div>
                {data !== undefined && data.map((item, i) => (
                    <div>
                        <span>{item.name}</span>
                        {item.level ? <span>{item.level}</span> : ""}
                    </div>

                ))
                }


            </div>
        )
    }

}

export default CharacterDetailsSkillsView;