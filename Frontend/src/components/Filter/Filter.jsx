import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";



class Filter extends React.Component {

    render(){

        const {columnsConfig, onFilter} = this.props;

        return (
            <div style={{backgroundColor: 'purple', width: '800px'}}>
                {
                    columnsConfig && columnsConfig.map((item, i) => (
                        item.filter
                    ))
                }
                <button onClick={onFilter}>Filtruj</button>
            </div>
        )
    }

}

export default Filter;