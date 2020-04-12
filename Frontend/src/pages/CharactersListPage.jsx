import React from 'react';
import '../App.css';
import Table from "../components/Table/Table";
import {Cat, Dog, randomStyle} from '../commons/example-global-styles.js'
import characterService from "../services/characterService";
import {textsPolish} from "../commons/texts-pl";

const columnConfig = () => (
    [
        {
            title: 'Imię', field: 'name',
            removable: true,
            cellStyle: {
                backgroundColor: '#039be5',
                color: 'green'
            },
            headerStyle: {
                backgroundColor: 'blue',
            }
        },
        { title: 'Nazwisko', field: 'surname', removable: true },
        { title: 'Płeć', field: 'sex', lookup: {"MALE": "M", "FEMALE": "K"} },
        { title: 'Rasa', field: 'race', lookup: {"HUMAN": "Człowiek", "DWARF": "Krasnolud", "ELF": "Elf", "HALFLING": "Niziołek"} },
        { title: 'Profesja', field: 'careerName'},
        { title: 'Miejsce pobytu', field: 'livePlace'}
    ]
)

class CharactersListPage extends React.Component{

    constructor() {
        super();
        this.state = {}
    }

    componentDidMount() {
        this.getAllCharacters();
    }

    getAllCharacters = () => {
        characterService.getAllCharacters()
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

    render(){
        return (
            <div className="App">
                <header className="App-header">
                    <Cat>Kotek</Cat>
                    <Cat color='blue'>Kotek niebieski</Cat>
                    <Dog>Piesek</Dog>
                    <div style={randomStyle}>ZIemniak</div>

                    <Table
                        title={"Lista postaci"}
                        columnsConfig={columnConfig()}
                        data={this.state.data}
                        noRecordsMessage={textsPolish.noRecordsOnCharactersList}
                    />
                </header>
            </div>
        )

    }
}
export default CharactersListPage;
