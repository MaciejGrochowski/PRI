import React from 'react';
import Modal from 'react-modal';
import test from '../../../styles/popup.css';
import button from "../../../styles/buttons.css";
import popup from "../../../styles/popup.css";
import {Checkbox} from "@material-ui/core";

const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)',
        backgroundColor: '#292F2F',
        color: 'white',
        zIndex: '100!important'
    }
};

export const columnsConfig = [
    {
        title: "Imię",
        name: "name"
    },
    {
        title: "Nazwisko",
        name: "surname"
    },
    {
        title: "Rasa",
        name: "race"
    },
    {
        title: "Płeć",
        name: "sex"
    },
    {
        title: "Profesja",
        name: "currentCareer"
    },
    {
        title: "Miejsce pobytu",
        name: "livePlace"
    },
    {
        title: "Miejsce urodzenia",
        name: "birthPlace"
    },
    {
        title: "Dzień urodzenia",
        name: "dayOfBirth"
    },
    {
        title: "Miesiąc urodzenia",
        name: "monthOfBirth"
    },
    {
        title: "Rok urodzenia",
        name: "yearOfBirth"
    },
    {
        title: "Religia",
        name: "religion"
    },
    {
        title: "Wzrost",
        name: "height"
    },
    {
        title: "Waga",
        name: "weight"
    },
    {
        title: "Kolor oczu",
        name: "eyeColor"
    },
    {
        title: "Kolor włosów",
        name: "hairColor"
    },
    {
        title: "Cechy charakteru",
        name: "personality"
    },
    {
        title: "Cechy wyglądu",
        name: "apperance"
    },
    {
        title: "Poprzednie profesje",
        name: "previousCareers"
    },
    {
        title: "Dominujące emocje",
        name: "emotion"
    },
    {
        title: "Umiejętności",
        name: "skills"
    },
    {
        title: "Zdolności",
        name: "talents"
    },
    {
        title: "Przepowiednia",
        name: "prediction"
    },
    {
        title: "Znak gwiezdny",
        name: "starSign"
    },
    {
        title: "Walka wręcz",
        name: "weaponSkill"
    },
    {
        title: "Umiejętności Strzeleckie",
        name: "ballisticSkill"
    },
    {
        title: "Krzepa",
        name: "strength"
    },
    {
        title: "Odporność",
        name: "toughness"
    },
    {
        title: "Zręczność",
        name: "agility"
    },
    {
        title: "Inteligencja",
        name: "intelligence"
    },
    {
        title: "Siła Woli",
        name: "willPower"
    },
    {
        title: "Ogłada",
        name: "fellowship"
    },
    {
        title: "Ataki",
        name: "attacks"
    },
    {
        title: "Żywotność",
        name: "wounds"
    },
    {
        title: "Magia",
        name: "magic"
    },
    {
        title: "Szybkość",
        name: "movement"
    },
];

class ChangeVisibilityModal extends React.Component {

    constructor() {
        super();
        this.state = {
            checkboxData: {}
        }
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(!prevProps.initialValues && this.props.initialValues){
            let tmp = {};
            Object.keys(this.props.initialValues).forEach(v => tmp[v] = !!this.props.initialValues[v]);
            this.setState({checkboxData: tmp})
        }
    }

    onCheckboxClick = (name, value) => {
        let checkboxData = this.state.checkboxData;
        if(!value){
            delete checkboxData[name];
        }
        if(value){
            checkboxData[name] = true
        }
        this.setState({checkboxData: checkboxData})
    }


    render() {
        const {isOpen, title, onRequestClose, isGlobal} = this.props;

        return (
            <div>
                <Modal
                    isOpen={isOpen}
                    onRequestClose={() => onRequestClose()}
                    style={customStyles}
                    contentLabel={title}
                >
                    <div>{title}</div>
                    {isGlobal && <div>UWAGA - te zmiany nadpiszą wszystkie ustawienia widoczności konkretnych postaci!</div>}
                            {columnsConfig && columnsConfig.map((item, i) => (
                                <div>
                                    <Checkbox
                                        onChange={(event) => this.onCheckboxClick(item.name, event.target.checked)}
                                        key={item.title}
                                        checked={this.state.checkboxData[item.name]}
                                    />
                                    {item.title}
                                </div>

                            ))
                            }
                    <div className="flex-center-element">
                        <button type="submit" className="button" onClick={() => this.props.onSave(this.state.checkboxData)}>Zastosuj</button>
                    </div>
                </Modal>
            </div>
        );
    }
}

export default ChangeVisibilityModal;