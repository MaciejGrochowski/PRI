import {TextField} from "@material-ui/core";
import MenuItem from "@material-ui/core/MenuItem";
import DefaultMultipleAutocomplete from "../../components/Autocomplete/DefaultMultipleAutocomplete";
import React from "react";

export const columnConfig = (careerNames=[]) => (
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
            },
            filter: <span><TextField label="Imię" id="characterFilterName"/></span>
        },
        {
            title: 'Nazwisko',
            field: 'surname',
            filter: <span><TextField label="Nazwisko" id="characterFilterSurname"/></span>
        },
        {
            title: 'Płeć',
            field: 'sex',
            lookup: {"MALE": "M", "FEMALE": "K"},
            filter: <span>
                <TextField id="characterFilterSex" label="Płeć" defaultValue="MALE" select>
                        <MenuItem value={'MALE'}>Mężczyzna</MenuItem>
                        <MenuItem value={'FEMALE'}>Kobieta</MenuItem>
                </TextField>
                </span>
        },
        {
            title: 'Rasa',
            field: 'race',
            lookup: {"HUMAN": "Człowiek", "DWARF": "Krasnolud", "ELF": "Elf", "HALFLING": "Niziołek"},
            filter: <span>
                <TextField id="characterFilterRace" label="Rasa" defaultValue="HUMAN" select>
                    <MenuItem value={'HUMAN'}>Człowiek</MenuItem>
                    <MenuItem value={'ELF'}>Elf</MenuItem>
                    <MenuItem value={'DWARF'}>Krasnolud</MenuItem>
                    <MenuItem value={'HALFLING'}>Niziołek</MenuItem>
                    </TextField>
            </span>
        },
        {
            title: 'Profesja',
            field: 'careerName',
            filter: <DefaultMultipleAutocomplete
                labelName="Profesje"
                options={careerNames}
                id="characterFilterCareers"
            />
        },
        {
            title: 'Miejsce pobytu',
            field: 'livePlace',
            filter: <span><TextField label="Miejsce pobytu"/></span>
        }
    ]
)