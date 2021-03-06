import React from "react";
import "../../styles/globalStyles.css";
import Wysiwyg from "../../components/Wysiwyg/Wysiwyg";
import historyService from "../../services/historyService";
import {fronendUrls} from "../../commons/urls";
import placeService from "../../services/placeService";
import TextField from "@material-ui/core/TextField";
import {months} from "../../enums/Months";
import Autocomplete from "@material-ui/lab/Autocomplete";
import "../../styles/historyCreator.css";
import ErrorGenerator from "../../components/ErrorLayout/ErrorGenerator";
import {polishCodeErrors, textsPolish} from "../../commons/texts-pl";
import NeedLoginInformation from "../../components/NeedLoginInformation/NeedLoginInformation";
import {loginStatusChange} from "../../actions";
import {connect} from "react-redux";
import {getToken, isValidToken} from "../../services/util";
import {Redirect} from "react-router";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";

class HistoryCreatorPage extends React.Component {


    constructor() {
        super();

        this.state = {
            characters: [],
            places: [],
            place: "",
            day: "",
            month: "",
            year: "",
            title: "",
            isError: false,
            errorText: ''

        }
    }

    componentDidMount() {
        if(!this.props.isLogged && getToken() && isValidToken(getToken())) this.props.loginStatusChange(true);
        if(this.props.isLogged && !getToken() || !isValidToken(getToken())) this.props.loginStatusChange(false);
        historyService.getCharactersCreatingHistory()
            .then(r => this.getCharactersCreatingHistorySuccessHandler(r))
            .catch(e => this.getCharactersCreatingHistoryErrorHandler(e))
        placeService.getAllPlaceNames()
            .then(r => this.getPlaceNamesSuccessHandler(r))
            .catch(e => this.getPlaceNamesErrorHandler(e))

    };

    getCharactersCreatingHistorySuccessHandler = response => {
        let characters = response.data.map(r => ({text: r.text, value: r.value, url: fronendUrls.characterDetails +"/" + r.url}))
        this.setState({characters: characters})
    };

    getCharactersCreatingHistoryErrorHandler = error => {
        console.log(error);
    };

    getPlaceNamesSuccessHandler = response => {
        this.setState({places: response.data})
    };

    getPlaceNamesErrorHandler = error => {
        console.log(error);
    };


    saveHistory = description => {
        let data = {
            description: description,
            place: this.state.place,
            day: this.state.day,
            month: this.state.month,
            year: this.state.year,
            title: this.state.title
        };

        historyService.createHistory(data)
            .then(r => this.setState({createdHistoryId: r.data}))
            .catch(e => this.saveHistoryErrorHandler(e))
    };

    saveHistoryErrorHandler = error => {
        if(error.response.data.errors){
            const errorMsg = error.response.data.errors.map(e => e.defaultMessage)[0]
            this.setState({isError: true, errorText: errorMsg})
        }
        else this.setState({isError: true, errorText: error.response.data.message})
    };

    filterOptions = (input, state) => {
        if(this.props.notSortOptions && state.inputValue==="") return input;
        let optionsContains = []
        let optionsStartWith = []
        let allOptions = []
        let inputValue = state.inputValue.toLowerCase()

        for(let option of input){
            let optionLowerCase = option.toLowerCase()
            if(optionLowerCase.startsWith(inputValue))
                optionsStartWith.push(option)
            else{
                if(optionLowerCase.includes(inputValue))
                    optionsContains.push(option)
            }
        }
        optionsContains.sort()
        optionsStartWith.sort()
        allOptions = optionsStartWith.concat(optionsContains)
        return allOptions;
    }


    render(){

        if (this.state.createdHistoryId) {
            return <Redirect push to={fronendUrls.historyList + "/" + this.state.createdHistoryId} />
        }


        return (<div className="globalStyles">

            <header className="App-header">


            <div className = "History-creator-main-div">
                {!this.props.isLogged &&
                <NeedLoginInformation text={textsPolish.needLoginToSaveHistory}/>}
                        <div className="History-creator-upper">
           <div className = "history-column">
           Data:
            <div className="History-buttons-line">
            <div className="item-div">
                <TextField value={this.state.day} onChange={(event) => {
                    this.setState({day: event.target.value});
                }}  label="Dzień"/>
</div>
<div className="item-div">
                <Autocomplete
                    options={months}
                    value={this.state.month}
                    onChange={(event, newValue) => {
                        this.setState({month: newValue});
                    }}
                    renderInput={(params) => <TextField {...params} label="Miesiąc" />}
                />
                </div>
<div className="item-div">
                <TextField value={this.state.year} onChange={(event) => {
                    this.setState({year: event.target.value});
                }}  label="Rok"/>
</div>
</div>
</div>

           <div className = "history-column">
           Miejsce:
            <div className="History-buttons-line">
                <div className="item-div">

                    <Autocomplete
                    renderInput={(params) => <TextField {...params} label="Miejsce" />}
                    options={this.state.places || []}
                    value={this.state.place}
                    onChange={(event, newValue) => {
                        this.setState({place: newValue});
                    }}
                    noOptionsText={"Brak opcji"}
                    filterOptions={(input, state) => this.filterOptions(input, state)}
                    />

                </div>
</div>
</div>

                        </div>

<div className = "History-creator-upper">
            <div className = "history-column">
                Tytuł:
                <div className="title-history-creator History-creator-upper item-div">
                <TextField value={this.state.title} onChange={(event) => {
                    this.setState({title: event.target.value});
                }}  label="Tytuł"/>
                </div></div></div>
                <div className="block-element">{this.state.isError &&
                <ErrorGenerator errorText={"Błąd: " + polishCodeErrors[this.state.errorText]}/>}</div>

                <Wysiwyg
                characterTags={this.state.characters}
                saveHistory={this.saveHistory}
                disabledSave={!this.props.isLogged}

                />

            </div>
            </header>
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


export default HistoryCreatorPage = connect(mapStateToProps, mapDispatchToProps)(HistoryCreatorPage);