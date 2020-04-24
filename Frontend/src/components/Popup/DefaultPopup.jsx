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

    render() {
        const {isOpen, title} = this.props;
        return (
            <div>

                <Modal
                    isOpen={isOpen}
                    onAfterOpen={() => console.log("open")}
                    onRequestClose={() => console.log("close")}
                    style={customStyles}
                    contentLabel={title}
                >
                    <div>I am a modal</div>
                    <form>
                        <label className="container">Raz
                            <input type="checkbox"/>
                            <span className = "checkmark"></span>
                        </label>
                        <label className="container">Dwa
                            <input type="checkbox"/>
                            <span className = "checkmark"></span>
                        </label>
                        <label className="container">Trzy
                            <input type="checkbox"/>
                            <span className = "checkmark"></span>
                        </label>
                        <button className = "button">close</button>
                    </form>
                </Modal>
            </div>
        );
    }
}

export default DefaultPopup;