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
import historyService from "../../services/historyService";
import Wysiwyg from "../../components/Wysiwyg/Wysiwyg";
import HistoryDetailsPopup from "../../components/Popup/HistoryDetailsPopup/HistoryDetailsPopup";
import {fronendUrls} from "../../commons/urls";


//ToDo refactor - id of current history should have source only in URL; this.state.idPopupHistory is reduntant.
//ToDo refactor - use react-router, not url as a string
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
            columnsConfig: [],
            isPopupOpen: false,
            idPopupHistory: 0,
            characterLoadByPage: ""
        }
    }

    async componentDidMount() {
        if(window.location.pathname.includes("character")){
            const tmp = window.location.pathname.split("/");
            let name = tmp[tmp.length-2];
            const id = tmp[tmp.length-1];
            name = name.replace("-", " ");
            const tag = name + "#" + id;
            await this.setState({
                characterLoadByPage: tag
            })
            // setTimeout(function(){ document.getElementById("historyFilterCharacters").value = tag }, 3000);
            // window.history.pushState({}, null, window.location.href.substring(0, window.location.href.indexOf("/character")));
        }

        if(window.location.pathname.includes("user")){
            const tmp = window.location.pathname.split("/");
            const username = tmp[tmp.length-1];
            await this.setState({
                userLoadByPage: username
            })
            // setTimeout(function(){ document.getElementById("historyFilterCharacters").value = tag }, 3000);
            // window.history.pushState({}, null, window.location.href.substring(0, window.location.href.indexOf("/character")));
        }

        this.getHistories();
        this.getAutoCompleteHistories();
        this.setColumnsConfig()

        let historyId = this.getHistoryId();
        if(historyId > 0 && this.state.characterLoadByPage === "" ){
            this.setState({historyWithoutPreviousOrNext: true})
            this.setState({isPopupOpen: true, idPopupHistory: historyId})

        }
    }

    async componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevState.autocompleteData !== this.state.autocompleteData) this.setColumnsConfig();

        if(this.state.userLoadByPage && this.state.userLoadByPage!=="" && !window.location.pathname.includes("user")){
            await this.setState({userLoadByPage: ""})
            this.getHistories();
            this.getAutoCompleteHistories();
        }

        if((!this.state.userLoadByPage || (this.state.userLoadByPage && this.state.userLoadByPage==="")) && window.location.pathname.includes("user")){
            const tmp = window.location.pathname.split("/");
            const username = tmp[tmp.length-1];
            await this.setState({
                userLoadByPage: username
            })
            this.getHistories();
            this.getAutoCompleteHistories();
        }

    }

    getHistoryId = () => {
        const tmp = window.location.pathname.split("/");
        if(Number.isInteger(parseInt(tmp[tmp.length-1]))){
            return parseInt(tmp[tmp.length-1])
        }
        return 0;
    }



    setColumnsConfig = async () => {
        await this.setState({
            columnsConfig: columnConfig(this.state.autocompleteData, this.state.characterLoadByPage, this.state.userLoadByPage)
        })
    }

    getAutoCompleteHistories = async () => {

        await historyService.getAutocompleteFilters()
            .then(r => this.getAutocompleteFiltersSuccessHandler(r))
    }

    getAutocompleteFiltersSuccessHandler = response => {
        this.setState({autocompleteData: response.data})
    }

    onChangePage = async page => {
        await this.setState({page: page})
        return this.getHistories();
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

        const historyTitle = document.getElementById('historyTitle');
        if(historyTitle && historyTitle.value!=="") filterObject = {...filterObject, historyTitle: historyTitle.value};


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

        // const historyFilterCharacters = Array.from(document.getElementsByClassName("historyFilterCharacters")).map(c => c.textContent)
        // if(historyFilterCharacters.length > 0) filterObject = {...filterObject, historyFilterCharacters: this.mapFilterArrayToString(historyFilterCharacters, this.state.characterNames)}

        const historyFilterCharacters = document.getElementById('historyFilterCharacters');
        if(historyFilterCharacters && historyFilterCharacters.value !== "") filterObject = {...filterObject, historyFilterCharacters: historyFilterCharacters.value};


        await this.setState({filterObject: filterObject, page: 0})

        this.getHistories();
    }

    getHistories = () => {
        let requestBody = {
            sortedBy: this.state.sortBy,
            filters: this.state.filterObject,
            isAscending: this.state.sortOrder === "asc",
            currentPage: this.state.page,
            rowsPerPage: this.state.countPerPage
        }

        if(this.state.characterLoadByPage && this.state.characterLoadByPage !== "") {
            requestBody.filters = {
                ...requestBody.filters,
                historyFilterCharacters: decodeURI(this.state.characterLoadByPage)
            };
        }
        if(this.state.userLoadByPage && this.state.userLoadByPage !== "") {
            requestBody.filters = {
                ...requestBody.filters,
                createdBy: decodeURI(this.state.userLoadByPage)
            };
        }

        return historyService.getHistories(requestBody)
            .then(r => this.getHistoriesSuccessHandler(r))
    }

    getHistoriesSuccessHandler = async response => {
        if(response && response.data && response.data.list && response.data.list.length === 0 && response.data.totalCount === -1){
            return;
        }
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

    onDetailsClick = rowData => {
        this.setState({isPopupOpen: true, idPopupHistory: rowData.id})
    }

    changeHistoryFromDetails = async (currentHistoryId, isNext) => {
        let currentHistory = this.state.data.filter(h => h.id === currentHistoryId)[0];
        let indexOfCurrentHistory = this.state.data.indexOf(currentHistory);
        if(!isNext && this.state.page === 0 && indexOfCurrentHistory === 0) return;
        if(isNext && this.state.page * this.state.countPerPage + indexOfCurrentHistory +1 === this.state.count) return;

        if(isNext){
            if(indexOfCurrentHistory+1 < this.state.countPerPage) {
                this.setState({idPopupHistory: this.state.data[indexOfCurrentHistory+1].id})
                window.history.pushState({}, null, this.getUrl() + "/" + this.state.data[indexOfCurrentHistory+1].id);
            }
            else await this.onChangePage(this.state.page+1).then(r => {
                this.setState({idPopupHistory: this.state.data[0].id})
                window.history.pushState({}, null, this.getUrl() + "/" + this.state.data[0].id);
            })
        }
        else{
            if(indexOfCurrentHistory-1 >= 0) {
                this.setState({idPopupHistory: this.state.data[indexOfCurrentHistory-1].id});
                window.history.pushState({}, null, this.getUrl() + "/" +this.state.data[indexOfCurrentHistory-1].id );

            }
            else await this.onChangePage(this.state.page-1).then(r => {
                this.setState({idPopupHistory: this.state.data[this.state.countPerPage-1].id});
                window.history.pushState({}, null, this.getUrl() + "/" + this.state.data[indexOfCurrentHistory-1].id );
            })

        }

    }

    getUrl = () => {
        return fronendUrls.historyList + (this.state.userLoadByPage ? "/user/" + this.state.userLoadByPage : "") +
            (this.state.characterLoadByPage ? "/character/" + this.state.characterLoadByPage : "")
    }

    render(){
        return (
            <div className="globalStyles">
                <header className="App-header">

                    <HistoryDetailsPopup
                    isOpen={this.state.isPopupOpen}
                    title={"Szczegóły"}
                    onRequestClose={() => {this.setState({isPopupOpen: false, idPopupHistory: 0}); window.history.pushState({}, null, this.getUrl());}}
                    historyId={this.state.idPopupHistory}
                    changeHistoryToNext={this.changeHistoryFromDetails}
                    isPreviousButtonHidden={this.state.page === 0 && this.state.data.indexOf(this.state.data.filter(h => h.id === this.state.idPopupHistory)[0]) === 0}
                    isNextButtonHidden={this.state.page * this.state.countPerPage + 1 + this.state.data.indexOf(this.state.data.filter(h => h.id === this.state.idPopupHistory)[0]) === this.state.count}
                    removeButtons={this.state.historyWithoutPreviousOrNext} //ToDo refactor that

                    />

                    <Filter
                        columnsConfig={this.state.columnsConfig}
                        onFilter={this.onFilter}
                        isHiddenExpandListButton={true}
                    />

                    <div>
                        {this.state.characterLoadByPage && "Historie postaci " + decodeURI(this.state.characterLoadByPage)}
                        {this.state.userLoadByPage && "Historie użytkownika " + decodeURI(this.state.userLoadByPage)}

                    </div>
                    <div className="table">
                        <Table
                            style = {table}
                            title={"Lista historii"}
                            columnsConfig={this.state.columnsConfig}
                            notVisibleColumns={"Postacie uczestniczące"}
                            data={this.state.data}
                            noRecordsMessage={textsPolish.noRecordsOnHistoryList}
                            countPerPage={this.state.countPerPage}
                            page={this.state.page}
                            ownOnChangePage={this.onChangePage}
                            onChangeCountPerPage={this.onChangeCountPerPage}
                            count={this.state.count}
                            onDetailsClick={this.onDetailsClick}
                            // onDetailsClick={() => console.log("onDetailsClick")}
                            detailsLink={this.getUrl()}
                            onOrderChange={this.onOrderChange}
                        />
                    </div>
                </header>
            </div>
        )
    }
}

export default HistoriesListPage;
