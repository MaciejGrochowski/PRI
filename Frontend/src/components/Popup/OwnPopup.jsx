import React from 'react';
import Modal from 'react-modal';
import style from './../../styles/popup.css';

// Make sure to bind modal to your appElement (http://reactcommunity.org/react-modal/accessibility/)

class OwnPopup extends React.Component {

    render() {
        const {isOpen, title} = this.props;
        return (
          <div>
              <a class="button" href="#popup1">Let me Pop up</a>
            <div id="popup1" className="overlay">
                <div className="popup">
                <label className="constainer">One
                    <input type="checkbox" checked="checked"/>
                <span className="checkmark"></span>
                </label>
                <label className="container">Two
                    <input type="checkbox"/>
                    <span className = "checkmark"></span>
                </label>
                <a className = "close" href="#">&times;</a>
                </div>
            </div>
        </div>


            );
    }
}

export default OwnPopup;