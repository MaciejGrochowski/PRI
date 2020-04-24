import React from 'react';
import '../../App.css';
import Table from "../../components/Table/Table";
import {Cat, Dog, randomStyle} from '../../commons/example-global-styles.js'
import characterService from "../../services/characterService";
import careerService from "../../services/careerService";
import {textsPolish} from "../../commons/texts-pl";
import Filter from "../../components/Filter/Filter";
import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import {columnConfig} from "./ColumnsConfig";
import "../../styles/globalStyles.css";
import "../../styles/tables.css";
import DefaultPopup from "../../components/Popup/DefaultPopup";
import OwnPopup from "../../components/Popup/OwnPopup";

class CharactersListPage extends React.Component{

    constructor() {
        super();
        this.state = {
            page: 0,
            countPerPage: 10,
            count: 0,
            careerNames: ["test", "test2"],
            isFilterListExpanded: false
        }
    }

    componentDidMount() {
        this.getAllCharactersByCountAndPage(this.state.countPerPage, this.state.page);
        this.getCountOfCharacters();
        this.getCareerNames();
    }

    getCareerNames = () => {
        careerService.getAllCareerNames()
            .then(r => this.getCareerNamesSuccessHandler(r))
    }

    getCareerNamesSuccessHandler = response => {
        this.setState({careerNames: response.data})
    }

    getCountOfCharacters = () => {
        characterService.getCountOfCharacters()
            .then(r => this.setState({count: r.data}))
    }

    getAllCharactersByCountAndPage = (count, page) => {
        characterService.getCharactersByCountAndPage(count, page)
            .then(r => this.getAllCharactersSuccessHandler(r.data))
            .catch(e => this.getAllCharactersErrorHandler(e))
    }

    getAllCharactersErrorHandler = error => {
        console.error("Błąd przy pobieraniu postaci");
        console.error(error);
    }

    getAllCharactersSuccessHandler = data => {
        this.setState({data: data})
    }

    onChangePage = (page) => {
        this.setState({page: page})
        this.getAllCharactersByCountAndPage(this.state.countPerPage, page);
    }

    onChangeCountPerPage = countPerPage => {
        this.setState({countPerPage: countPerPage, page: 1})
        this.getAllCharactersByCountAndPage(countPerPage, 1)
    }

    onFilter = data => {
        console.log(document.getElementById('characterFilterName').value)
        console.log(document.getElementById('characterFilterSurname').value)
        console.log(document.getElementById('characterFilterSex').nextSibling.value)
        console.log(document.getElementById('characterFilterRace').nextSibling.value)
        console.log(Array.from(document.getElementsByClassName("characterFilterCareers")).map(c => c.textContent));
        //ToDo Backend filtering
    }

    expandFilterList = () => {
        this.setState({isFilterListExpanded: true})
    }

    render(){
        const divStyle = {
            backgroundColor: '#292F2F',
            width: '600px !important',
        };
        return (
            <div className="GlobalStyles">
                <header className="App-header">
                    {/*<Cat>Kotek</Cat>*/}
                    {/*<Cat color='#292F2F'>Kotek niebieski</Cat>*/}
                    {/*<Dog>Piesek</Dog>*/}
                    {/*<div style={randomStyle}>ZIemniak</div>*/}
                    <Filter
                        columnsConfig={columnConfig(this.state.careerNames)}
                        onFilter={this.onFilter}
                    />
                    <button onClick={this.expandFilterList}>Dostosuj</button>
                    <DefaultPopup
                    title="Przykład"
                    isOpen={this.state.isFilterListExpanded}

                    />
                    <OwnPopup/>
                    <Table
                    style = {divStyle}
                        title={"Lista postaci"}
                        columnsConfig={columnConfig(this.state.careerNames)}
                        data={this.state.data}
                        noRecordsMessage={textsPolish.noRecordsOnCharactersList}
                        countPerPage={this.state.countPerPage}
                        page={this.state.page}
                        ownOnChangePage={this.onChangePage}
                        onChangeCountPerPage={this.onChangeCountPerPage}
                        count={this.state.count}
                    />
                </header>
            </div>
        )
    }
}

export default CharactersListPage;
