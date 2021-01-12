import React from "react";
import filter from "../../styles/filters.css";
import button from "../../styles/buttons.css";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSearch, faTasks} from "@fortawesome/free-solid-svg-icons";


const element = <FontAwesomeIcon icon={faSearch}/>

const element2 = <FontAwesomeIcon icon={faTasks}/>

class Filter extends React.Component {

    render(){


        const {columnsConfig, onFilter, expandFilterList} = this.props;
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
                    <button className = "button reverse" onClick={onFilter}><div className = "text-with-fafa">Wyszukaj </div><span>{element}</span></button>
                    {!this.props.isHiddenExpandListButton && <button className="button reverse" onClick={expandFilterList}><div className = "text-with-fafa">Dostosuj filtry</div> <span>{element2}</span> </button> }
                </div>
                
            </div>

        )
    }

}

export default Filter;