import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";


class CharacterDetailsStatsView extends React.Component {

    render(){
        const {data={}, title} = this.props;
        return (
            <div>
                <div className="sub-title">{title}</div>
                <div className = "wrap-element">
                <div className="flex-component"><div className = "block-element">Imię</div> <div className = "block-element yellow-color" >{data.name}</div></div>
                <div className="flex-component"><div className = "block-element">Nazwisko</div> <div className = "block-element yellow-color">{data.surname}</div></div>
                <div className="flex-component"><div className = "block-element">Profesja</div> <div className = "block-element yellow-color">{data.currentCareer}</div></div>
                <div className="flex-component"><div className = "block-element">Poprzednie profesje</div> <div className = "block-element yellow-color">{data.previousCareers}</div></div>
                <div className="flex-component"><div className = "block-element">Miejsce pobytu</div> <div className = "block-element yellow-color">{data.livePlace}</div></div>
                <div className="flex-component"><div className = "block-element">Miejsce urodzenia</div> <div className = "block-element yellow-color">{data.birthPlace}</div></div>
                <div className="flex-component"><div className = "block-element">Rasa</div> <div className = "block-element yellow-color">{data.race}</div></div>
                <div className="flex-component"><div className = "block-element">Płeć</div> <div className = "block-element yellow-color">{data.sex}</div></div>
                <div className="flex-component"><div className = "block-element">Data urodzenia</div> <div className = "block-element yellow-color">TUTAJ PÓKI CO NULL</div></div>
                <div className="flex-component"><div className = "block-element">Wzrost</div> <div className = "block-element yellow-color">{data.height}</div></div>
                <div className="flex-component"><div className = "block-element">Waga</div> <div className = "block-element yellow-color">{data.weight}</div></div>
                <div className="flex-component"><div className = "block-element">Znak gwiezdny</div> <div className = "block-element yellow-color">{data.starSign}</div></div>
                <div className="flex-component"><div className = "block-element">Kolor oczu</div> <div className = "block-element yellow-color">{data.eyeColor}</div></div>
                <div className="flex-component"><div className = "block-element">Kolor włosów</div> <div className = "block-element yellow-color">{data.hairColor}</div></div>
                <div className="flex-component"><div className = "block-element">Cechy charakteru</div> <div className = "block-element yellow-color">{data.personality}</div></div>
                <div className="flex-component"><div className = "block-element">Cechy wyglądu</div> <div className = "block-element yellow-color">{data.apperance}</div></div>
                <div className="flex-component"><div className = "block-element">Emocje</div> <div className = "block-element yellow-color">{data.dominatingEmotions}</div></div>
                <div className="flex-component"><div className = "block-element">Wiara</div> <div className = "block-element yellow-color">{data.religion}</div></div>
                <div className="flex-component"><div className = "block-element">Przepowiednia</div> <div className = "block-element yellow-color">{data.prediction}</div></div>

                </div>
            </div>
        )
    }

}

export default CharacterDetailsStatsView;
