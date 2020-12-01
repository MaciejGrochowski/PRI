import React from "react";
import {fronendUrls} from "../../commons/urls";
import "./../../styles/history.css";
class CharacterDetailsHistoryView extends React.Component {


    constructor() {
        super();
        this.state = {
            characterHistories: []
        }
    }

    render(){
        const {historyData=[], onGetMoreHistoriesClick} = this.props;


        return (
            <div className = "globalStyles">
            <div className = "pageWithContext">
            <div className = "pageName">
            Historie:
            </div>
            <div className = "border-left">
                {historyData && historyData.length<1 && <div className = "one-history-brief">Brak historii do wyświetlenia.</div>}


                {historyData && historyData.map((item, i) => (
                    <div className = "one-history-brief">
                    <span>{item.beginDescription.substring(0, 80)}</span>
                    <button className = "detaleButton" onClick = {() => window.location.assign(fronendUrls.historyList + "/" + item.id) }><div className = "normal-text">Więcej</div></button>
                    </div>
                        ))

                        }
                      </div>
                     {historyData && historyData.length > 0 &&<button className = "detaleButton" onClick = {() => onGetMoreHistoriesClick()}><div className = "normal-text">Więcej historii z tą postacią</div></button>}
                      </div>
            </div>
        )
    }

}

export default CharacterDetailsHistoryView;
