import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {ItemMenu} from "./ExampleMenu.style";
import "../../styles/menu.css";
import "../../styles/globalStyles.css";
import AppBar from "@material-ui/core/AppBar";

const menuKatalogItems = [
    {
        label: 'Postacie',
        link: fronendUrls.characterList
    },
    // {
    //     label: 'Historie',
    //     link: fronendUrls.historyList
    // },


]

const menuGeneratorItems = [
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
            <div>
            <AppBar position="fixed" color="transparent">
            <div className ="globalStyles">
                {this.state.isExpanded && (<nav className="menuBody">

                <div className= "menu-column">
                    <div className = "menu-title">Katalog</div>
                    {
                    this.state.isExpanded ? menuKatalogItems && menuKatalogItems.map((item, i) => (
                    <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                )) : ""
                }
                </div>
                <div className= "menu-column">
                    <div className = "menu-title">Stwórz</div>
                    {
                    this.state.isExpanded ? menuGeneratorItems && menuGeneratorItems.map((item, i) => (
                    <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                )) : ""
                }
                
                </div>
            </nav>)}
                <button className="menubutton" onClick={this.onExpanded}>Menu</button>
            </div>
            </AppBar>
            <div className ="globalStyles">
            {this.state.isExpanded && (<nav className="menuBody">

                    <div className= "menu-column">
                        <div className = "menu-title">Katalog</div>
                        {
                            this.state.isExpanded ? menuKatalogItems && menuKatalogItems.map((item, i) => (
                                <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                            )) : ""
                        }
                    </div>
                    <div className= "menu-column">
                        <div className = "menu-title">Stwórz</div>
                        {
                            this.state.isExpanded ? menuGeneratorItems && menuGeneratorItems.map((item, i) => (
                                <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                            )) : ""
                        }

                    </div>
                </nav>)}
        <button className="menubutton" onClick={this.onExpanded}>Menu</button>
        </div>

        </div>
    )
    }

}

export default Menu;