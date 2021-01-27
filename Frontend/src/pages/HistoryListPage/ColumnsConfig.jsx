import {TextField} from "@material-ui/core";
import ziemniak from "../../styles/filters.css";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";
//ToDo Więcej autocompletów...

export const columnConfig = (autocompleteData={}, isCharacterFilter, isUserFilter) => {
    return [
        {
            title: 'Twórca', field: 'createdBy',
            removable: true,
            hidden: isUserFilter,
            sorting: false,
            filter: <div class="item-div"><TextField label="Twórca" id="historyCreatedBy"/></div>
        },
        {
            title: 'Tytuł', field: 'historyTitle',
            removable: true,
            sorting: false,
            filter: <div class="item-div"><TextField label="Tytuł" id="historyTitle"/></div>
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
                labelName="Postać uczestnicząca"
                options={autocompleteData.characterNames || []}
                id="historyFilterCharacters"
            /></div>
        },
    ]
}
