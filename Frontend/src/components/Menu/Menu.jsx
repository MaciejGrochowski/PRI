import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {ItemMenu} from "./ExampleMenu.style";
import "../../styles/menu.css";
import "../../styles/globalStyles.css";
import AppBar from "@material-ui/core/AppBar";
import {getInfoFromToken, getToken} from "../../services/util";
import LoginButton from "../LoginButton/LoginButton";
import {loginStatusChange} from "../../actions";
import {connect} from "react-redux";

const menuCatalogItems = [
    {
        label: 'Postacie',
        link: fronendUrls.characterList
    },
    {
        label: 'Historie',
        link: fronendUrls.historyList
    },


]

const menuGeneratorItems = [
    {
        label: 'Postać',
        link: fronendUrls.characterGenerator
    },
    {
        label: 'Historię',
        link: fronendUrls.historyCreator
    }
]

const menuOwnItems = [

    {
        label: "Postacie",
        link: getInfoFromToken(getToken()) ? fronendUrls.characterList + '/' + getInfoFromToken(getToken()).sub : ""
    },
    {
        label: "Historie",
        link: getInfoFromToken(getToken()) ? fronendUrls.historyList + '/user/' + getInfoFromToken(getToken()).sub : ""
    },
    {
        label: "Sesje",
        link: getInfoFromToken(getToken()) ? fronendUrls.sessionList + '/' + getInfoFromToken(getToken()).sub : ""
    }


]

class Menu extends React.Component {

    constructor() {
        super();
        this.state = {
            isExpanded: false,
            randomId: 1
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

                    {this.props.isLogged && <div className= "menu-column">
                        <div className = "menu-title">Moje</div>
                        {
                            this.state.isExpanded ? menuOwnItems && menuOwnItems.map((item, i) => (
                                <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                            )) : ""
                        }
                    </div>}

                <div className= "menu-column">
                    <div className = "menu-title">Katalog</div>
                    {
                    this.state.isExpanded ? menuCatalogItems && menuCatalogItems.map((item, i) => (
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
<div className= "menu-column">
                    <LoginButton
                    notLoggedShowComponent={<Link className="menuLink" to={fronendUrls.loginPage}><ItemMenu>Zaloguj się</ItemMenu></Link>}
                    /> </div>
            </nav>)}
                <button className="menubutton" onClick={this.onExpanded}>Menu</button>
            </div>
            </AppBar>
            <div className ="globalStyles">
            {this.state.isExpanded && (<nav className="menuBody">

                {getInfoFromToken(getToken()) && <div className= "menu-column">
                    <div className = "menu-title">Moje</div>
                    {
                        this.state.isExpanded ? menuOwnItems && menuOwnItems.map((item, i) => (
                            <Link className="menuLink" to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                        )) : ""
                    }
                </div>}

                    <div className= "menu-column">
                        <div className = "menu-title">Katalog</div>
                        {
                            this.state.isExpanded ? menuCatalogItems && menuCatalogItems.map((item, i) => (
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

const mapStateToProps = (state) => {
    return {
        isLogged: state.isLogged // (1)
    }
};
const mapDispatchToProps = { loginStatusChange }; // (2)


export default Menu = connect(mapStateToProps, mapDispatchToProps)(Menu);