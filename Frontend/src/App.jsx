import React from 'react';
import logo from './logo.svg';
import './App.css';
import Table from "./Table";

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
        { title: 'Płeć', field: 'sex', lookup: {1: "M", 2: "K"} },
        { title: 'Rasa', field: 'race' },
        { title: 'Profesja', field: 'career'},
        { title: 'Miejsce pobytu', field: 'livePlace'}
    ]
)



class App extends React.Component{

    constructor() {
        super();
        this.state = {}
    }

    componentDidMount() {
        const data = [
            { name: 'Johann', surname: 'Schmidt', sex: 1, race: "Człowiek", career: "Rzemieślnik", livePlace: "Altdorf" },
            { name: 'Gertruda', surname: 'Schmidt', sex: 2, race: "Człowiek", career: "Rybak", livePlace: "Altdorf" }
        ]
        this.setState({data: data})
    }

    render(){
    return (
        <div className="App">
            <header className="App-header">
            <Table
            title={"Lista postaci"}
            columnsConfig={columnConfig()}
            data={this.state.data}
            />
            </header>
        </div>
    )

  }
}
export default App;
