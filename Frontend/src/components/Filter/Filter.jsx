import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";



class Filter extends React.Component {

    render(){


        const {columnsConfig, onFilter} = this.props;
        return (
            <div className = "filter-menu">
                <div className = "filter">
                    {
                        columnsConfig && columnsConfig.map((item, i) => (
                            !item.hidden &&<span styleName = "filter">{item.filter}</span>
                        ))
                    }
                </div>
                <div className = "filter-button">
                    <button className = "button" onClick={onFilter}>Filtruj</button>
                </div>
                
            </div>

        )
    }

}

export default Filter;