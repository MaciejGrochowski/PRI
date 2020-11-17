import React from 'react';
import {Editor} from 'react-draft-wysiwyg';
import Table from "../Table/Table";
import '../../../node_modules/react-draft-wysiwyg/dist/react-draft-wysiwyg.css';
import { EditorState, convertToRaw } from 'draft-js';
import draftToHtml from 'draftjs-to-html';
import parse from 'html-react-parser';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faCheck, faTimes} from '@fortawesome/free-solid-svg-icons';
import "../../styles/historyCreator.css";
const element = <FontAwesomeIcon icon={faCheck}/>
const element2 = <FontAwesomeIcon icon={faTimes}/>
class Wysiwyg extends React.Component {

    constructor(){
        super();
        this.state = {
            editorState: EditorState.createEmpty(),
        }
    }

    onEditorStateChange = (editorState) => {
        let tmp = parse(draftToHtml(convertToRaw(this.state.editorState.getCurrentContent())))
        console.log(tmp);

        this.setState({
            editorState,
        });
    };


    render() {
        const {characterTags, saveHistory} = this.props;
        return (<>
            <Editor
                wrapperClassName="demo-wrapper"
                toolbar={{
                    options: ['inline', 'fontSize', 'list', 'textAlign', 'history'],
                    inline: {
                        options: ['bold', 'italic', 'underline', 'strikethrough'],
                        // bold: { className: 'bordered-option-classname' },
                        // italic: { className: 'bordered-option-classname' },
                        // underline: { className: 'bordered-option-classname' },
                        // strikethrough: { className: 'bordered-option-classname' },
                    },
                    // blockType: {
                    //     className: 'bordered-option-classname',
                    // },
                    // fontSize: {
                    //     className: 'bordered-option-classname',
                    // },
                    // fontFamily: {
                    //     className: 'bordered-option-classname',
                    // },
                }}

                editorClassName="demo-editor"
                onEditorStateChange={this.onEditorStateChange}
                editorState={this.state.editorState}
                mention={{
                    separator: ' ',
                    trigger: '@',
                    suggestions: characterTags
                    // suggestions: [
                    //     {text: 'APPLE', value: 'apple', url: 'apple'},
                    //     {text: 'BANANA', value: 'banana', url: '/characterDetails/2'},
                    //     {text: 'CHERRY', value: 'cherry', url: 'cherry'},
                    //     {text: 'DURIAN', value: 'durian', url: 'durian'},
                    //     {text: 'EGGFRUIT', value: 'eggfruit', url: 'eggfruit'},
                    //     {text: 'FIG', value: 'fig', url: 'fig'},
                    //     {text: 'GRAPEFRUIT', value: 'grapefruit', url: 'grapefruit'},
                    //     {text: 'HONEYDEW', value: 'honeydew', url: 'honeydew'},
                    // ],
                }}
                hashtag={{}}
            />
                <button className="green-button" onClick={() => saveHistory(draftToHtml(convertToRaw(this.state.editorState.getCurrentContent())))}>Zapisz <span>{element}</span></button>
                <button className="red-button">Anuluj <span>{element2}</span></button>


               </>

        )

    }
}


export default Wysiwyg;