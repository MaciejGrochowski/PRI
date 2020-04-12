import React from "react";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";
import {ItemMenu} from "./ExampleMenu.style";

const menuItems = [
    {
        label: 'Postacie',
        link: fronendUrls.characterList
    },
    {
        label: 'Historie',
        link: fronendUrls.historyList
    }

]


class Menu extends React.Component {

    render(){
        return (
            <div>
                {
                    menuItems && menuItems.map((item, i) => (
                    <Link to={item.link}><ItemMenu>{item.label}</ItemMenu></Link>
                ))


                }



            </div>
        )
    }

}

export default Menu;