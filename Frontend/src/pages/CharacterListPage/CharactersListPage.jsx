import React from 'react';
import '../../App.css';
import Table from "../../components/Table/Table";
import {Cat, Dog, randomStyle} from '../../commons/example-global-styles.js'
import characterService from "../../services/characterService";
import careerService from "../../services/careerService";
import placeService from "../../services/placeService";
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
            careerNames: [],
            placeNames: [],
            sortBy: null,
            data: [],
            filterObject: null
        }
    }

    componentDidMount() {
        this.getCharacters();
        this.getAutoCompleteCharacters();
    }

    getAutoCompleteCharacters = () => {
        careerService.getAllCareerNames()
            .then(r => this.getCareerNamesSuccessHandler(r))
        placeService.getAllPlaceNames()
            .then(r => this.getPlaceNamesSuccessHandler(r))
    }

    getPlaceNamesSuccessHandler = response => {
        this.setState({placeNames: response.data})
}

    getCareerNamesSuccessHandler = response => {
        this.setState({careerNames: response.data})
    }

    onChangePage = async page => {
        await this.setState({page: page})
        this.getCharacters();
    }

    onChangeCountPerPage = async countPerPage => {
        await this.setState({countPerPage: countPerPage, page: 1})
        this.getCharacters();
    }

    mapFilterArrayToString = (array, options) => {
        let string = ""
        for (const element in array){
            const name = options[element]
            string = string + name + ","
        }
        return string.substring(0, string.length-1);
    }

    onFilter = async data => {
        let filterObject = {}
        const name = document.getElementById('characterFilterName').value;
        if(name && name!=="") filterObject = {...filterObject, name: name}

        const surname = document.getElementById('characterFilterSurname').value;
        if(surname && surname!=="") filterObject = {...filterObject, surname: surname}

        const sex = document.getElementById('characterFilterSex').nextSibling.value;
        if(sex && !(sex==='fill')) filterObject = {...filterObject, sex: sex}

        const race = document.getElementById('characterFilterRace').nextSibling.value;
        if(race && !(race==='fill')) filterObject = {...filterObject, race: race}

        const careers = Array.from(document.getElementsByClassName("characterFilterCareers")).map(c => c.textContent);
        if(careers.length > 0) filterObject = {...filterObject, careers: this.mapFilterArrayToString(careers, this.state.careerNames)}

        const eyeColor = document.getElementById('characterFilterEyeColor').value;
        if (eyeColor && eyeColor !== "") filterObject = {...filterObject, eyeColor: eyeColor}

        const hairColor = document.getElementById('characterFilterHairColor').value;
        if (hairColor && hairColor !== "") filterObject = {...filterObject, hairColor: hairColor};

        const livePlace = document.getElementById('characterFilterLivePlace').value;
        if(livePlace && livePlace !== "") filterObject = {...filterObject, livePlace: livePlace}

        const birthPlace = Array.from(document.getElementsByClassName("characterFilterBirthPlaces")).map(c => c.textContent)
        if(birthPlace.length > 0) filterObject = {...filterObject, birthPlace: this.mapFilterArrayToString(birthPlace, this.state.placeNames)}

        await this.setState({filterObject: filterObject})

        this.getCharacters();

        //ToDo Backend filtering
    }

    onOrderChange = async (param1, param2) => {
        if(param1 === -1){
            await this.setState({sortBy: null})
        }
        else{
            const config = columnConfig()
            await this.setState({sortBy: config[param1].field})
        }
        await this.setState({sortOrder: param2})

        this.getCharacters()

    }

    getCharacters = () => {
        console.log(this.state)
        const requestBody = {
            sortedBy: this.state.sortBy,
            filters: this.state.filterObject,
            isAscending: this.state.sortOrder === "asc",
            currentPage: this.state.page,
            rowsPerPage: this.state.countPerPage
        }

        characterService.getCharacters(requestBody)
            .then(r => this.getCharactersSuccessHandler(r))
    }

    getCharactersSuccessHandler = response => {
        this.setState({
            count: response.data.totalCount,
            data: response.data.list
        })
    }


    expandFilterList = () => {
        this.setState({isFilterListExpanded: true})
    }

    render(){
        const divStyle = {
            backgroundColor: '#292F2F',
            width: '600px !important',
            position: 'relative',
        };
        return (
            <div className="globalStyles">
                <header className="App-header">
                    <Filter
                        columnsConfig={columnConfig(this.state.careerNames, this.state.placeNames)}
                        onFilter={this.onFilter}
                    />
                    <button className= "button" onClick={this.expandFilterList}>Dostosuj</button>
                    <DefaultPopup
                    isOpen={this.state.showModal}
                    contentLabel="onRequestClose Example"
                    onRequestClose={this.handleCloseModal}
                    className="Modal"
                    overlayClassName="Overlay"

                    title="Przykład"
                    isOpen={this.state.isFilterListExpanded}
                    />

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
                        onOrderChange={this.onOrderChange}
                    />
                </header>
            </div>
        )
    }
}

export default CharactersListPage;
