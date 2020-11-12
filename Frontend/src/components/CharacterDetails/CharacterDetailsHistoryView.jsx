import React from "react";
import {fronendUrls} from "../../commons/urls";

class CharacterDetailsHistoryView extends React.Component {


    constructor() {
        super();
        this.state = {
            characterHistories: []
        }
    }

    render(){
        const {historyData=[], onGetMoreHistoriesClick} = this.props;
        console.log(historyData);
        return (
            <div>
                {historyData===[] && <div>Brak historii do wyświetlenia.</div>}


                {historyData && historyData.map((item, i) => (
                    <div>
                    <span>{item.beginDescription.substring(0, 40)}</span>
                    <button onClick = {() => window.location.assign(fronendUrls.historyList + "/" + item.id) }>Więcej</button>
                    </div>
                        ))}
            <button onClick = {() => onGetMoreHistoriesClick()}>Więcej historii z tą postacią</button>

            </div>
        )
    }

}

export default CharacterDetailsHistoryView;
