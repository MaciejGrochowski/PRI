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
            hidden: !visibilityProperties.createdBy,
            filter: <div class="ziemniak"><TextField label="Twórca" id="historyCreatedBy"/></div>
        },
        {
            title: 'Dzień',
            field: 'historyDay',
            hidden: !visibilityProperties.historyDay,
            filter: <div class="ziemniak"><TextField label="Dzień" id="historyDay"/></div>,
            customSort: (a,b) => parseInt(a) > parseInt(b)
        },
        {
            title: 'Miesiąc',
            field: 'historyMonth',
            hidden: !visibilityProperties.historyMonth,
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
            hidden: !visibilityProperties.historyYear,
            filter: <div class="ziemniak"><TextField label="Rok" id="historyYear"/></div>
        },
        {
            title: 'Miejsce',
            hidden: !visibilityProperties.historyPlace,
            field: 'historyPlace',
            filter: <div className="ziemniak"><DefaultMultipleAutocomplete
                labelName="Miejsce"
                options={autocompleteData.placeNames || []}
                id="historyFilterPlace"
                multiple
            /></div>
        }
    ]
}
