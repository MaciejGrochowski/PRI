import React from 'react';
import Modal from 'react-modal';

const customStyles = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)'
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
                        <input/>
                        <button>tab navigation</button>
                        <button>stays</button>
                        <button>inside</button>
                        <button>the modal</button>
                    </form>
                </Modal>
            </div>
        );
    }
}

export default DefaultPopup;