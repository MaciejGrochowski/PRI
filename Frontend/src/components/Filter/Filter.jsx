import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";



class Filter extends React.Component {

    render(){

        const {columnsConfig, onFilter} = this.props;
        return (
            <div class = "filter-menu">
            <div class = "filter">
                {
                    columnsConfig && columnsConfig.map((item, i) => (
                        !item.hidden &&<span>{item.filter}</span>
                    ))
                }
            </div>
                <button className = "button" onClick={onFilter}>Filtruj</button>
                
</div>

        )
    }

}

export default Filter;