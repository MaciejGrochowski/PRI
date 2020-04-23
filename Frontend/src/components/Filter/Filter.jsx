import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";



class Filter extends React.Component {

    render(){

        const {columnsConfig, onFilter} = this.props;
        const containerStyle = {
            backfaceVisibility: 'hidden',
        };
        return (
            <div class = "filter">
                {
                    columnsConfig && columnsConfig.map((item, i) => (
                        <span>{item.filter}</span>
                    ))
                }
                <button class = "button" onClick={onFilter}>Filtruj</button>
            </div>
        )
    }

}

export default Filter;