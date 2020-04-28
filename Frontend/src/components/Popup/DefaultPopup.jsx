import React from 'react';
import Modal from 'react-modal';
import test from '../../styles/popup.css';
import button from "../../styles/buttons.css";
import popup from "../../styles/popup.css";

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

    }
};

// Make sure to bind modal to your appElement (http://reactcommunity.org/react-modal/accessibility/)

class DefaultPopup extends React.Component {

    save = columnsConfig => {
        // console.log(document.getElementById("nameVisibilityCheckbox").checked);
        let object = {}
        const form = document.getElementById("visibilityCharactersColumns");

        for (let i=0; i<columnsConfig.length ; i++){
            const fieldName = columnsConfig[i].field;
            const checked = form.getElementsByTagName("input")[i].checked;
            object[fieldName] = checked;
        }
        this.props.onSave(object)
    }

    render() {
        const {isOpen, title, expandFilterList, columnsConfig} = this.props;
        return (
            <div>
                <button className="button" onClick={expandFilterList}>Dostosuj</button>
                <Modal
                    isOpen={isOpen}
                    onAfterOpen={() => console.log("open")}
                    onRequestClose={() => console.log("close")}
                    style={customStyles}
                    contentLabel={title}
                >
                    <form id="visibilityCharactersColumns">
                        {columnsConfig && columnsConfig.map((item, i) => (
                            <label className="container">{item.title}
                                <input type="checkbox" defaultChecked={!item.hidden} id={item.field + "VisibilityCheckbox"}/>
                                <span className="checkmark"/>
                            </label>
                        ))
                        }
                    </form>
                    <button type="submit" className="button" onClick={() => this.save(columnsConfig)}>Jestem ziemniakiem</button>
                </Modal>
            </div>
        );
    }
}

export default DefaultPopup;