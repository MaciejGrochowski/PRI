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
                    <div className="flex-element">
                        <div className = "block-element"><div className="flex-component"><div className = "stats-name">Imię</div><div className = "yellow-color" >{data.name}</div></div></div>
                        <div className = "block-element"><div className="flex-component"><div className = "stats-name">Nazwisko</div> <div className = "yellow-color">{data.surname}</div></div></div>
                    </div>
                <div className="flex-component"><div className = "stats-name">Profesja</div> <div className = "yellow-color">{data.currentCareer}</div></div>
                <div className="flex-component"><div className = "stats-name">Poprzednie profesje</div> <div className = "yellow-color">{data.previousCareers}</div></div>
                <div className="flex-component"><div className = "stats-name">Miejsce pobytu</div> <div className = "yellow-color">{data.livePlace}</div></div>
                <div className="flex-component"><div className = "stats-name">Miejsce urodzenia</div> <div className = "yellow-color">{data.birthPlace}</div></div>
                <div className="flex-element">
                <div className = "block-element"><div className="flex-component"><div className = "stats-name">Rasa</div> <div className = "yellow-color">{data.race}</div></div></div>
                <div className = "block-element"><div className="flex-component"><div className = "stats-name">Płeć</div> <div className = "yellow-color">{data.sex}</div></div></div>
                </div>
                <div className="flex-component"><div className = "stats-name">Data urodzenia</div> <div className = "yellow-color">{data.dayOfBirth} {data.monthOfBirth} {data.yearOfBird}</div></div>
                <div className="flex-element">
                <div className = "block-element"><div className="flex-component"><div className = "stats-name">Wzrost</div> <div className = "yellow-color">{data.height}</div></div></div>
                <div className = "block-element"><div className="flex-component"><div className = "stats-name">Waga</div> <div className = "yellow-color">{data.weight}</div></div></div>
                <div className = "block-element"><div className="flex-component"><div className = "stats-name">Znak gwiezdny</div> <div className = "yellow-color">{data.starSign}</div></div></div>
                </div>
                <div className="flex-component"><div className = "stats-name">Kolor oczu</div> <div className = "yellow-color">{data.eyeColor}</div></div>
                <div className="flex-component"><div className = "stats-name">Kolor włosów</div> <div className = "yellow-color">{data.hairColor}</div></div>
                <div className="flex-component"><div className = "stats-name">Cechy charakteru</div> <div className = "yellow-color">{data.personality}</div></div>
                <div className="flex-component"><div className = "stats-name">Cechy wyglądu</div> <div className = "yellow-color">{data.apperance}</div></div>
                <div className="flex-component"><div className = "stats-name">Emocje</div> <div className = "yellow-color">{data.dominatingEmotions}</div></div>
                <div className="flex-component"><div className = "stats-name">Wiara</div> <div className = "yellow-color">{data.religion}</div></div>
                <div className="flex-component"><div className = "stats-name">Przepowiednia</div> <div className = "yellow-color">{data.prediction}</div></div>

                </div>
            </div>
        )
    }

}

export default CharacterDetailsStatsView;
