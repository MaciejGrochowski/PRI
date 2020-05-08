import React from "react";
import {Link} from "react-router-dom";
import characterService from "../../services/characterService";



class CharacterDetailsStatsView extends React.Component {

    render(){
        const {data={}, title} = this.props;
        return (
            <div>
                <div>{title}</div>
                <div>Imię</div> <div>{data.name}</div>
                <div>Nazwisko</div> <div>{data.surname}</div>
                <div>Profesja</div> <div>{data.currentCareer}</div>
                <div>Poprzednie profesje</div> <div>{data.previousCareers}</div>
                <div>Miejsce pobytu</div> <div>{data.livePlace}</div>
                <div>Miejsce urodzenia</div> <div>{data.birthPlace}</div>
                <div>Rasa</div> <div>{data.race}</div>
                <div>Płeć</div> <div>{data.sex}</div>
                <div>Data urodzenia</div> <div>TUTAJ PÓKI CO NULL</div>
                <div>Wzrost</div> <div>{data.height}</div>
                <div>Waga</div> <div>{data.weight}</div>
                <div>Znak gwiezdny</div> <div>{data.starSign}</div>
                <div>Kolor oczu</div> <div>{data.eyeColor}</div>
                <div>Kolor włosów</div> <div>{data.hairColor}</div>
                <div>Cechy charakteru</div> <div>{data.personality}</div>
                <div>Cechy wyglądu</div> <div>{data.apperance}</div>
                <div>Emocje</div> <div>{data.dominatingEmotions}</div>
                <div>Wiara</div> <div>{data.religion}</div>
                <div>Przepowiednia</div> <div>{data.prediction}</div>

            </div>
        )
    }

}

export default CharacterDetailsStatsView;
