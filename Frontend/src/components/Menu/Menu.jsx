import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {ItemMenu} from "./ExampleMenu.style";
import "../../styles/menu.css";
import "../../styles/globalStyles.css";

const menuItems = [
    {
        label: 'Postacie',
        link: fronendUrls.characterList
    },
    {
        label: 'Historie',
        link: fronendUrls.historyList
    },
    {
        label: 'Generator',
        link: fronendUrls.characterGenerator
    }

]


class Menu extends React.Component {

    constructor() {
        super();
        this.state = {
            isExpanded: false
        }
    }

    onExpanded = () => {
        this.setState({
            isExpanded: !this.state.isExpanded
        })
    }

    render(){
        return (
            <div className ="globalStyles">
            <div className="menuBody">
                {
                    this.state.isExpanded ? menuItems && menuItems.map((item, i) => (
                    <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                )) : ""
                }
            </div>
                <button className="menubutton" onClick={this.onExpanded}>Menu</button>


            </div>
        )
    }

}

export default Menu;