import React from 'react';
import '../../App.css';
import Table from "../../components/Table/Table";
import characterService from "../../services/characterService";
import {textsPolish} from "../../commons/texts-pl";
import Filter from "../../components/Filter/Filter";
import {columnConfig} from "./ColumnsConfig";
import "../../styles/globalStyles.css";
import "../../styles/tables.css";
import CustomizeFiltersPopup from "../../components/Popup/CustomizeFiltersPopup/CustomizeFiltersPopup";
import {starSigns} from "../../enums/StarSigns";
import {religions} from "../../enums/Religions";
import {table} from "../../styles/tables.css"
import button from "../../styles/buttons.css";
import Modal from "react-modal";
import {fronendUrls} from "../../commons/urls";

class CharactersListPage extends React.Component {

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
            autocompleteData: {},
            filterObject: {},
            visibilityProperties: {
                name: true,
                surname: true,
                livePlace: true,
                sex: true,
                race: true,
                currentCareer: true
            },
            columnsConfig: []
        }
    }

    async componentDidMount() {
        this.getCharacters();
        this.getAutoCompleteCharacters();
        this.setColumnsConfig()
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if (prevState.autocompleteData !== this.state.autocompleteData) this.setColumnsConfig();
    }

    setColumnsConfig = () => {
        this.setState({
            columnsConfig: columnConfig(this.state.autocompleteData, this.state.visibilityProperties)
        })
    }

    getAutoCompleteCharacters = async () => {
        await characterService.getAutocompleteFilters()
            .then(r => this.getAutocompleteFiltersSuccessHandler(r));
    }

    getAutocompleteFiltersSuccessHandler = response => {
        response.data.skillNames = response.data.skillNames.filter(s => s.includes("+20")).map(s => s.split(" +")[0])
        this.setState({autocompleteData: response.data})
    }

    onChangePage = async page => {
        await this.setState({page: page})
        this.getCharacters();
    }

    onChangeCountPerPage = async countPerPage => {
        await this.setState({countPerPage: countPerPage, page: 0})
        this.getCharacters();
    }

    mapFilterArrayToString = (array, options) => {
        let string = ""
        for (const element in array) {
            let name;
            name = array[element]; //ToDo prawdopodobnie jest lepsza metoda, ale wymaga analizy
            string = string + name + ","
        }
        return string.substring(0, string.length - 1);
    }

    onFilter = async data => { //ToDo refactor with redux or contextApi
        let filterObject = {}
        const name = document.getElementById('characterFilterName');
        if (name && name.value !== "") filterObject = {...filterObject, name: name.value}

        const surname = document.getElementById('characterFilterSurname');
        if (surname && surname.value !== "") filterObject = {...filterObject, surname: surname.value}

        const sex = document.getElementById('characterFilterSex');
        if (sex) {
            if (sex.value === "Mężczyzna") filterObject = {...filterObject, sex: "MALE"}
            if (sex.value === "Kobieta") filterObject = {...filterObject, sex: "FEMALE"}
        }

        const races = Array.from(document.getElementsByClassName("characterFilterRace")).map(c => c.textContent);
        let raceStr = "";
        if (races.includes("Człowiek")) raceStr += "HUMAN,";
        if (races.includes("Elf")) raceStr += "ELF,";
        if (races.includes("Krasnolud")) raceStr += "DWARF,"
        if (races.includes("Niziołek")) raceStr += "HALFLING,"
        if (raceStr.length > 0) {
            filterObject = {...filterObject, race: raceStr.substring(0, raceStr.length - 1)}
        }

        const currentCareer = Array.from(document.getElementsByClassName("characterFilterCareers")).map(c => c.textContent);
        if (currentCareer.length > 0) filterObject = {
            ...filterObject,
            currentCareer: this.mapFilterArrayToString(currentCareer, this.state.autocompleteData.careerNames)
        }

        const eyeColor = document.getElementById('characterFilterEyeColor');
        if (eyeColor && eyeColor.value !== "") filterObject = {...filterObject, eyeColor: eyeColor.value}

        const prediction = document.getElementById('characterFilterPrediction');
        if (prediction && prediction.value !== "") filterObject = {...filterObject, prediction: prediction.value};

        const hairColor = document.getElementById('characterFilterHairColor');
        if (hairColor && hairColor.value !== "") filterObject = {...filterObject, hairColor: hairColor.value};

        const livePlace = Array.from(document.getElementsByClassName("characterFilterLivePlaces")).map(c => c.textContent)
        if (livePlace.length > 0) filterObject = {
            ...filterObject,
            livePlace: this.mapFilterArrayToString(livePlace, this.state.placeNames)
        }

        const birthPlace = Array.from(document.getElementsByClassName("characterFilterBirthPlaces")).map(c => c.textContent)
        if (birthPlace.length > 0) filterObject = {
            ...filterObject,
            birthPlace: this.mapFilterArrayToString(birthPlace, this.state.placeNames)
        }


        const starSign = Array.from(document.getElementsByClassName("characterFilterStarSigns")).map(c => c.textContent)
        if (starSign.length > 0) filterObject = {
            ...filterObject,
            starSign: this.mapFilterArrayToString(starSign, starSigns)
        }

        const emotions = Array.from(document.getElementsByClassName("characterFilterEmotions")).map(c => c.textContent)
        if (emotions.length > 0) filterObject = {
            ...filterObject,
            dominatingEmotions: this.mapFilterArrayToString(emotions, this.state.autocompleteData.emotionNames)
        }

        const religion = Array.from(document.getElementsByClassName("characterFilterReligions")).map(c => c.textContent)
        if (religion.length > 0) filterObject = {
            ...filterObject,
            religion: this.mapFilterArrayToString(religion, religions)
        }

        const skills = Array.from(document.getElementsByClassName("characterFilterSkills")).map(c => c.textContent)
        if (skills.length > 0) filterObject = {
            ...filterObject,
            skills: this.mapFilterArrayToString(skills, this.state.autocompleteData.skillNames)
        }

        const talents = Array.from(document.getElementsByClassName("characterFilterTalents")).map(c => c.textContent)
        if (talents.length > 0) filterObject = {
            ...filterObject,
            talents: this.mapFilterArrayToString(talents, this.state.autocompleteData.talentNames)
        }

        const personalities = Array.from(document.getElementsByClassName("characterFilterPersonalities")).map(c => c.textContent)
        if (personalities.length > 0) filterObject = {
            ...filterObject,
            personalities: this.mapFilterArrayToString(personalities, this.state.autocompleteData.personalityNames)
        }

        const apperances = Array.from(document.getElementsByClassName("characterFilterApperances")).map(c => c.textContent)
        if (apperances.length > 0) filterObject = {
            ...filterObject,
            apperances: this.mapFilterArrayToString(apperances, this.state.apperanceNames)
        }

        await this.setState({filterObject: filterObject, page: 0})

        this.getCharacters();
    }

    onOrderChange = async (param1, param2) => {
        if (param1 === -1) {
            await this.setState({sortBy: null})
        } else {
            const config = columnConfig()
            await this.setState({sortBy: config[param1].field})
        }
        await this.setState({sortOrder: param2})

        this.getCharacters()
    }

    saveChangeColumns = async data => {
        await this.setState({visibilityProperties: data, isFilterListExpanded: false})
        this.setColumnsConfig();
    }

    getCharacters = async () => {

        const username = this.props.match.params.username
        if (username && username.value !== "") await this.setState({
            filterObject: {
                ...this.state.filterObject,
                createdBy: username
            }
        })

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

    mapArrayToString = array => {
        let output = "";
        for (const i in array) {
            output = output + array[i] + ", ";
        }
        return output.substring(0, output.length - 2);
    }

    mapSkillsToString = skills => {
        let output = ""
        skills = skills.map(s => s.name)
        skills = [...new Set(skills)]
        for (const i in skills) {
            output = output + skills[i] + ", ";
        }
        return output.substring(0, output.length - 2);
    }

    getCharactersSuccessHandler = response => {
        let data = [];
        for (const i in response.data.list) {
            let element = response.data.list[i];
            let object = {
                name: element.name,
                birthPlace: element.birthPlace,
                race: element.race,
                eyeColor: element.eyeColor,
                hairColor: element.hairColor,
                birthDate: element.birthDate,
                starSign: element.starSign,
                dominatingEmotions: this.mapArrayToString(element.dominatingEmotions),
                sex: element.sex,
                religion: element.religion,
                height: element.height,
                weight: element.weight,
                surname: element.surname,
                prediction: element.prediction,
                currentCareer: element.currentCareer,
                talents: this.mapArrayToString(element.talents),
                personalities: this.mapArrayToString(element.personality),
                apperances: this.mapArrayToString(element.apperance),
                livePlace: element.livePlace,
                skills: this.mapSkillsToString(element.skills),
                id: element.id
            }
            data.push(object) //ToDo ta konwersja obiektów powinna się odbywać na backendzie!!!
        }


        this.setState({
            count: response.data.totalCount,
            data: data
        })
    }

    onClosePopup = () => {
        this.setState({isFilterListExpanded: false})
    }


    expandFilterList = () => {
        this.setState({isFilterListExpanded: true})
    }

    onDetailsClick = rowData => {
        return fronendUrls.characterDetails + "/" + rowData.id;
    }

    render() {
        return (
            <div className="globalStyles">
                <header className="App-header">

                    {this.props.match.params.username &&
                    <div>Postacie użytkownika {this.props.match.params.username}</div>
                    }

                    <Filter
                        columnsConfig={this.state.columnsConfig}
                        onFilter={this.onFilter}
                        expandFilterList={this.expandFilterList}
                    />
                    <CustomizeFiltersPopup
                        onRequestClose={this.onClosePopup}
                        className="Modal"
                        onSave={this.saveChangeColumns}
                        overlayClassName="Overlay"
                        columnsConfig={this.state.columnsConfig}
                        title="Przykład"
                        isOpen={this.state.isFilterListExpanded}
                    />
                    <div className="table">
                        <Table
                            style={table}
                            title={"Lista postaci"}
                            columnsConfig={this.state.columnsConfig}
                            data={this.state.data}
                            noRecordsMessage={textsPolish.noRecordsOnCharactersList}
                            countPerPage={this.state.countPerPage}
                            page={this.state.page}
                            ownOnChangePage={this.onChangePage}
                            onChangeCountPerPage={this.onChangeCountPerPage}
                            count={this.state.count}
                            onDetailsClick={this.onDetailsClick}
                            detailsLink={fronendUrls.characterDetails}
                            onOrderChange={this.onOrderChange}
                            selection
                        />
                    </div>
                </header>
            </div>
        )
    }
}

export default CharactersListPage;
