import {TextField} from "@material-ui/core";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";
//ToDo Więcej autocompletów...

export const columnConfig = (autocompleteData={}, isCharacterFilter) => {
    console.log(isCharacterFilter);
    return [
        {
            title: 'Twórca', field: 'createdBy',
            removable: true,
            filter: <div class="item-div"><TextField label="Twórca" id="historyCreatedBy"/></div>
        },
        {
            title: 'Dzień',
            field: 'historyDay',
            filter: <div class="item-div"><TextField label="Dzień" id="historyDay"/></div>,
            customSort: (a,b) => parseInt(a) > parseInt(b)
        },
        {
            title: 'Miesiąc',
            field: 'historyMonth',
            filter: <div className="item-div"><DefaultMultipleAutocomplete
                labelName="Miesiąc"
                options={autocompleteData.monthNames || []}
                id="historyMonth"
                multiple
            /></div>
        },
        {
            title: 'Rok',
            field: 'historyYear',
            filter: <div class="item-div"><TextField label="Rok" id="historyYear"/></div>
        },
        {
            title: 'Miejsce',
            field: 'historyPlace',
            filter: <div className="item-div"><DefaultMultipleAutocomplete
                labelName="Miejsce"
                options={autocompleteData.placeNames || []}
                id="historyFilterPlace"
                multiple
            /></div>
        },
        {
            title: 'Postacie uczestniczące',
            field: 'historyCharacters',
            hidden: isCharacterFilter,
            filter: <div className="item-div"><DefaultMultipleAutocomplete
                labelName="Postacie uczestniczące"
                options={autocompleteData.characterNames || []}
                id="historyFilterCharacters"
            /></div>
        },
    ]
}
