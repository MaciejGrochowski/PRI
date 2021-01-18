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

const getMenuOwnItems = username => [

    {
        label: "Postacie",
        link: username ? fronendUrls.characterList + '/' + username: ""
    },
    {
        label: "Historie",
        link: username? fronendUrls.historyList + '/user/' + username : ""
    },
    {
        label: "Sesje",
        link: username ? fronendUrls.sessionList + '/' + username : ""
    }


]

class Menu extends React.Component {

    constructor() {
        super();
        this.state = {
            isExpanded: false,
            randomId: 1,
            username: ""
        }
    }

    componentDidMount() {
        if(!this.props.isLogged && getInfoFromToken(getToken())){
            this.props.loginStatusChange(true);
        }
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(!prevProps.isLogged && this.props.isLogged){
            while(!getInfoFromToken(getToken()).sub){
                this.setState({menuOwnItems: []})
            }
            this.setState({menuOwnItems: getMenuOwnItems(getInfoFromToken(getToken()).sub)})
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
                            this.state.isExpanded && this.props.isLogged ? this.state.menuOwnItems && this.state.menuOwnItems.map((item, i) => (
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
                    notLoggedShowComponent={<div className="menu-title"><Link to={fronendUrls.loginPage}><ItemMenu>Zaloguj się</ItemMenu></Link></div>}
                    />
                    <Link className="menuLink menuLinkLogIn" to={fronendUrls.mainPage}>Pomoc</Link> </div>
            </nav>)}
                <button className="menubutton" onClick={this.onExpanded}>Menu</button>
            </div>
            </AppBar>
            <div className ="globalStyles">
            {this.state.isExpanded && (<nav className="menuBody">

                {getInfoFromToken(getToken()) && <div className= "menu-column">
                    <div className = "menu-title">Moje</div>
                    {
                        this.state.isExpanded && this.props.isLogged ? this.state.menuOwnItems && this.state.menuOwnItems.map((item, i) => (
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
        {/*<button className="menubutton" onClick={this.onExpanded}>Menu</button>*/}
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