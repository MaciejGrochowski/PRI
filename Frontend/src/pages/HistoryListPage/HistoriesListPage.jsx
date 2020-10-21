import React from 'react';
import '../../App.css';
import Table from "../../components/Table/Table";
import characterService from "../../services/characterService";
import {textsPolish} from "../../commons/texts-pl";
import Filter from "../../components/Filter/Filter";
import {columnConfig} from "./ColumnsConfig";
import "../../styles/globalStyles.css";
import "../../styles/tables.css";
import DefaultPopup from "../../components/Popup/DefaultPopup";
import {starSigns} from "../../enums/StarSigns";
import {religions} from "../../enums/Religions";
import {table} from "../../styles/tables.css"
import button from "../../styles/buttons.css";
import Modal from "react-modal";
import historyService from "../../services/historyService";

class HistoriesListPage extends React.Component{

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
                createdBy: true,
                historyDay: true,
                historyMonth: true,
                historyYear: true,
                historyPlace: true,
            },
            columnsConfig: []
        }
    }

    componentDidMount() {
        this.getHistories();
        this.getAutoCompleteHistories();
        this.setColumnsConfig()
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevState.autocompleteData !== this.state.autocompleteData) this.setColumnsConfig();
    }

    setColumnsConfig = async () => {
        await this.setState({
            columnsConfig: columnConfig(this.state.autocompleteData, this.state.visibilityProperties)
        })
    }

    getAutoCompleteHistories = async () => {

        await historyService.getAutocompleteFilters()
            .then(r => this.getAutocompleteFiltersSuccessHandler(r))
    }

    getAutocompleteFiltersSuccessHandler = response => {
        // response.data.skillNames = response.data.skillNames.filter(s => s.includes("+20")).map(s => s.split(" +")[0])
        this.setState({autocompleteData: response.data})
    }

    onChangePage = async page => {
        await this.setState({page: page})
        this.getHistories();
    }

    onChangeCountPerPage = async countPerPage => {
        await this.setState({countPerPage: countPerPage, page: 0})
        this.getHistories();
    };

    mapFilterArrayToString = (array, options) => {
        let string = "";
        for (const element in array){
            let name;
            name=array[element]; //ToDo prawdopodobnie jest lepsza metoda, ale wymaga analizy
            string = string + name + ","
        }
        return string.substring(0, string.length-1);
    };

    onFilter = async data => {
        let filterObject = {};

        const createdBy = document.getElementById('historyCreatedBy');
        if(createdBy && createdBy.value!=="") filterObject = {...filterObject, createdBy: createdBy.value};

        const historyDay = document.getElementById('historyDay');
        if(historyDay && historyDay.value!=="") filterObject = {...filterObject, historyDay: historyDay.value};

        // const historyMonth = document.getElementById('historyMonth');
        // if(historyMonth && historyMonth.value!=="") filterObject = {...filterObject, historyMonth: historyMonth.value};

        const historyMonth = Array.from(document.getElementsByClassName("historyMonth")).map(c => c.textContent)
        if(historyMonth.length > 0) filterObject = {...filterObject, historyMonth: this.mapFilterArrayToString(historyMonth, this.state.monthNames)}

        const historyYear = document.getElementById('historyYear');
        if(historyYear && historyYear.value!=="") filterObject = {...filterObject, historyYear: historyYear.value};

        const historyFilterPlace = Array.from(document.getElementsByClassName("historyFilterPlace")).map(c => c.textContent)
        if(historyFilterPlace.length > 0) filterObject = {...filterObject, historyFilterPlace: this.mapFilterArrayToString(historyFilterPlace, this.state.placeNames)}

        await this.setState({filterObject: filterObject, page: 0})

        this.getHistories();
    }

    getHistories = () => {
        const requestBody = {
            sortedBy: this.state.sortBy,
            filters: this.state.filterObject,
            isAscending: this.state.sortOrder === "asc",
            currentPage: this.state.page,
            rowsPerPage: this.state.countPerPage
        }

        historyService.getHistories(requestBody)
            .then(r => this.getHistoriesSuccessHandler(r))
    }

    getHistoriesSuccessHandler = async response => {
        console.log(response);

        this.setState({
            count: response.data.totalCount,
            data: response.data.list
        })
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

        this.getHistories()
    }

    render(){
        return (
            <div className="globalStyles">
                <header className="App-header">

                    <Filter
                        columnsConfig={this.state.columnsConfig}
                        onFilter={this.onFilter}
                        isHiddenExpandListButton={true}
                        // expandFilterList={this.expandFilterList}
                        // expandFilterList={() => console.log("expandFilterList")}
                    />
                    <div className="table">
                        <Table
                            style = {table}
                            title={"Lista historii"}
                            columnsConfig={this.state.columnsConfig}
                            data={this.state.data}
                            noRecordsMessage={textsPolish.noRecordsOnCharactersList}
                            countPerPage={this.state.countPerPage}
                            page={this.state.page}
                            ownOnChangePage={this.onChangePage}
                            onChangeCountPerPage={this.onChangeCountPerPage}
                            count={this.state.count}
                            // onDetailsClick={this.onDetailsClick}
                            onDetailsClick={() => console.log("onDetailsClick")}
                            onOrderChange={this.onOrderChange}
                        />
                    </div>
                </header>
            </div>
        )
    }
}

export default HistoriesListPage;
