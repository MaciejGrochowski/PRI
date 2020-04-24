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

class OwnPopup extends React.Component {

    render() {
        const {isOpen, title} = this.props;
        return (
          <div>

              <a class="button" href="#popup1">Let me Pop up</a>

            <div id="popup1">
            <div>
            <label>One
                <input type="checkbox" checked="checked"/>
            <span/>
         </label>
                <label>Two
            <input type="checkbox"/>
            <span/>
         </label>
    <a href="#">&times;</a>
    </div>
    </div>
          </div>


            );
    }
}

export default OwnPopup;