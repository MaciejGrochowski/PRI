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
            filterObject: null,
            starSigns: [],
            apperances: [],
            personalities: [],
            talents: [],
            skills: [],
            religions: [],
            emotions: [],
            visibilityProperties: {
                name: true,
                surname: true,
                livePlace: true,
                sex: true,
                race: true,
                career: true
            },
            columnsConfig: []

        }
    }

    async componentDidMount() {
        this.getCharacters();
        await this.getAutoCompleteCharacters();
        this.setColumnsConfig()
    }

    setColumnsConfig = () => {
        this.setState({
            columnsConfig: columnConfig(this.state.careerNames, this.state.placeNames, this.state.starSigns,
                this.state.emotions, this.state.religions, this.state.skills, this.state.talents, this.state.personalities,
                this.state.apperances, this.state.visibilityProperties)
        })
    }

    getAutoCompleteCharacters = () => {
        careerService.getAllCareerNames()
            .then(r => this.getCareerNamesSuccessHandler(r))
        placeService.getAllPlaceNames()
            .then(r => this.getPlaceNamesSuccessHandler(r))


        //ToDo apperances, personalities, talents, skills, religions, emotions, starSigns
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
        const name = document.getElementById('characterFilterName');
        if(name && name.value!=="") filterObject = {...filterObject, name: name.value}

        const surname = document.getElementById('characterFilterSurname');
        if(surname && surname.value!=="") filterObject = {...filterObject, surname: surname.value}

        const sex = document.getElementById('characterFilterSex').nextSibling;
        if(sex && sex.value!=='fill') filterObject = {...filterObject, sex: sex.value}

        const race = document.getElementById('characterFilterRace').nextSibling;
        if(race && race.value!=='fill') filterObject = {...filterObject, race: race.value}

        const careers = Array.from(document.getElementsByClassName("characterFilterCareers")).map(c => c.textContent);
        if(careers.length > 0) filterObject = {...filterObject, careers: this.mapFilterArrayToString(careers, this.state.careerNames)}

        const eyeColor = document.getElementById('characterFilterEyeColor');
        if (eyeColor && eyeColor.value !== "") filterObject = {...filterObject, eyeColor: eyeColor.value}

        const prediction = document.getElementById('characterFilterPrediction');
        if(prediction && prediction.value !== "") filterObject = {...filterObject, prediction: prediction.value};

        const hairColor = document.getElementById('characterFilterHairColor');
        if (hairColor && hairColor.value !== "") filterObject = {...filterObject, hairColor: hairColor.value};

        const livePlace = document.getElementById('characterFilterLivePlace');
        if(livePlace && livePlace.value !== "") filterObject = {...filterObject, livePlace: livePlace.value}

        const birthPlace = Array.from(document.getElementsByClassName("characterFilterBirthPlaces")).map(c => c.textContent)
        if(birthPlace.length > 0) filterObject = {...filterObject, birthPlace: this.mapFilterArrayToString(birthPlace, this.state.placeNames)}



        const starSign = Array.from(document.getElementsByClassName("characterFilterStarSigns")).map(c => c.textContent)
        if(starSign.length > 0) filterObject = {...filterObject, starSign: this.mapFilterArrayToString(starSign, this.state.starSigns)}

        const emotions = Array.from(document.getElementsByClassName("characterFilterEmotions")).map(c => c.textContent)
        if(emotions.length > 0) filterObject = {...filterObject, dominatingEmotions: this.mapFilterArrayToString(emotions, this.state.emotions)}

        const religions = Array.from(document.getElementsByClassName("characterFilterReligions")).map(c => c.textContent)
        if(religions.length > 0) filterObject = {...filterObject, religion: this.mapFilterArrayToString(religions, this.state.religions)}

        const skills = Array.from(document.getElementsByClassName("characterFilterSkills")).map(c => c.textContent)
        if(skills.length > 0) filterObject = {...filterObject, skills: this.mapFilterArrayToString(skills, this.state.skills)}

        const talents = Array.from(document.getElementsByClassName("characterFilterTalents")).map(c => c.textContent)
        if(talents.length > 0) filterObject = {...filterObject, talents: this.mapFilterArrayToString(talents, this.state.talents)}

        const personalities = Array.from(document.getElementsByClassName("characterFilterPersonalities")).map(c => c.textContent)
        if(personalities.length > 0) filterObject = {...filterObject, personalities: this.mapFilterArrayToString(personalities, this.state.personalities)}

        const apperances = Array.from(document.getElementsByClassName("characterFilterApperances")).map(c => c.textContent)
        if(apperances.length > 0) filterObject = {...filterObject, apperances: this.mapFilterArrayToString(apperances, this.state.apperances)}

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

    saveChangeColumns = async data => {
        await this.setState({visibilityProperties: data, isFilterListExpanded: false})
        this.setColumnsConfig();
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
                        columnsConfig={this.state.columnsConfig}
                        onFilter={this.onFilter}
                    />
                    <DefaultPopup
                        expandFilterList={this.expandFilterList}
                    isOpen={this.state.showModal}
                    contentLabel="onRequestClose Example"
                    onRequestClose={this.handleClose}
                    className="Modal"
                        onSave={this.saveChangeColumns}
                    overlayClassName="Overlay"
                    columnsConfig={this.state.columnsConfig}
                    title="Przykład"
                    isOpen={this.state.isFilterListExpanded}
                    />

                    <Table
                    style = {divStyle}
                        title={"Lista postaci"}
                        columnsConfig={this.state.columnsConfig}
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
