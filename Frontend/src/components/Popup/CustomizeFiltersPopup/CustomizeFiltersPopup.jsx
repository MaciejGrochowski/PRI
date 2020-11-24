import React from 'react';
import Modal from 'react-modal';
import test from '../../../styles/popup.css';
import button from "../../../styles/buttons.css";
import popup from "../../../styles/popup.css";

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

class CustomizeFiltersPopup extends React.Component {

    save = columnsConfig => {
        let object = {}
        const form = document.getElementById("visibilityCharactersColumns");

        for (let i=0; i<columnsConfig.length ; i++){
            const fieldName = columnsConfig[i].field;
            if(form.getElementsByTagName("input")[i] === undefined) continue;
            const checked = form.getElementsByTagName("input")[i].checked;
            object[fieldName] = checked;
        }
        this.props.onSave(object)
    }

    render() {
        const {isOpen, title, expandFilterList, columnsConfig, onRequestClose} = this.props;
        let columnsConfigReal = [];
        for(let i in columnsConfig){
            if(columnsConfig[i].title !== "Detale") columnsConfigReal.push(columnsConfig[i])
        }


        return (
            <div className = "customize-button">
                <Modal
                    isOpen={isOpen}
                    // onAfterOpen={() => console.log("open")}
                    onRequestClose={() => onRequestClose()}
                    style={customStyles}
                    contentLabel={title}
                >
                    <form id="visibilityCharactersColumns" style={{zIndex: '100!important'}}>
                        <div className = "popup-body">
                        {columnsConfigReal && columnsConfigReal.map((item, i) => (
                            <label className="container"><div className = "label">{item.title}</div>
                                <input type="checkbox" defaultChecked={!item.hidden} id={item.field + "VisibilityCheckbox"}/>
                                <span className="checkmark"/>
                            </label>
                            
                        ))
                        }
                        </div>
                    </form>
                    <div className="flex-center-element">
                    <button type="submit" className="button" onClick={() => this.save(columnsConfig)}>Zastosuj</button>
                    </div>
                </Modal>
            </div>
        );
    }
}

export default CustomizeFiltersPopup;