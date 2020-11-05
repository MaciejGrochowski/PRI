import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";
//ToDo Więcej autocompletów...

export const columnConfig = (autocompleteData={}, visibilityProperties={}) => {
    return [
        {
            title: 'Twórca', field: 'createdBy',
            removable: true,
            filter: <div class="ziemniak"><TextField label="Twórca" id="historyCreatedBy"/></div>
        },
        {
            title: 'Dzień',
            field: 'historyDay',
            filter: <div class="ziemniak"><TextField label="Dzień" id="historyDay"/></div>,
            customSort: (a,b) => parseInt(a) > parseInt(b)
        },
        {
            title: 'Miesiąc',
            field: 'historyMonth',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Miesiąc"
                options={autocompleteData.monthNames || []}
                id="historyMonth"
                multiple
            /></div>
        },
        {
            title: 'Rok',
            field: 'historyYear',
            filter: <div class="ziemniak"><TextField label="Rok" id="historyYear"/></div>
        },
        {
            title: 'Miejsce',
            field: 'historyPlace',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Miejsce"
                options={autocompleteData.placeNames || []}
                id="historyFilterPlace"
                multiple
            /></div>
        },
        {
            title: 'Postacie uczestniczące',
            field: 'historyCharacters',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Postacie uczestniczące"
                options={autocompleteData.characterNames || []}
                id="historyFilterCharacters"
            /></div>
        },
    ]
}
