import React from "react";
import "../../styles/globalStyles.css";
import Wysiwyg from "../../components/Wysiwyg/Wysiwyg";
import historyService from "../../services/historyService";
import {fronendUrls} from "../../commons/urls";
import placeService from "../../services/placeService";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import TextField from "@material-ui/core/TextField";
import {months} from "../../enums/Months";
import Autocomplete from "@material-ui/lab/Autocomplete";


class HistoryCreatorPage extends React.Component {


    constructor(){
        super();

        this.state = {
            characters: [],
            places: []
        }
    }

    componentDidMount() {
        historyService.getCharactersCreatingHistory()
            .then(r => this.getCharactersCreatingHistorySuccessHandler(r))
            .catch(e => this.getCharactersCreatingHistoryErrorHandler(e))
        placeService.getAllPlaceNames()
            .then(r => this.getPlaceNamesSuccessHandler(r))
            .catch(e => this.getPlaceNamesErrorHandler(e))

    }

    getCharactersCreatingHistorySuccessHandler = response => {
        let characters = response.data.map(r => ({text: r.text, value: r.value, url: fronendUrls.characterDetails +"/" + r.url}))
        this.setState({characters: characters})
    }

    getCharactersCreatingHistoryErrorHandler = error => {
        console.log(error);
    }

    getPlaceNamesSuccessHandler = response => {
        this.setState({places: response.data})
    }

    getPlaceNamesErrorHandler = error => {
        console.log(error);
    }


    saveHistory(){
        console.log("SAVED");
    }


    render(){
        return (<div className="globalStyles">

            <header className="App-header">

                <TextField value={this.state.day} onChange={(event) => {
                    this.setState({day: event.target.value});
                }}  label="Dzień"/>

                {/*<div className="ziemniak"><DefaultMultipleAutocomplete*/}
                {/*    labelName="Miesiąc historii"*/}
                {/*    options={months}*/}
                {/*    value*/}
                {/*/></div>*/}

                <div className="ziemniak">
                <Autocomplete
                    options={months}
                    value={this.state.month}
                    onChange={(event, newValue) => {
                        this.setState({month: newValue});
                    }}
                    renderInput={(params) => <TextField {...params} label="Miesiąc" />}
                />
                </div>

                <TextField value={this.state.year} onChange={(event) => {
                    this.setState({year: event.target.value});
                }}  label="year"/>

                <div className="ziemniak"><DefaultMultipleAutocomplete
                    labelName="Miejsce"
                    options={this.state.places || []}
                /></div>

                <Wysiwyg
                characterTags={this.state.characters}
                saveHistory={this.saveHistory}

                />


            </header>
        </div>
    )
}
}


export default HistoryCreatorPage;