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
import {Tooltip} from "@material-ui/core";
import IconButton from "@material-ui/core/IconButton";
import InfoOutlinedIcon from "@material-ui/icons/InfoOutlined";
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
        // let tmp = parse(draftToHtml(convertToRaw(this.state.editorState.getCurrentContent())))
        // console.log(tmp);

        this.setState({
            editorState,
        });
    };


    render() {
        const {characterTags, saveHistory, disabledSave} = this.props;
        return (<>
            <Editor
                wrapperClassName="demo-wrapper"
                placeholder={<div style={{fontSize: '24px'}}>Możesz wpisać @ aby szybko umieścić postać z katalogu postaci w historii.</div>}
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
                stripPastedStyles={true}
                editorClassName="demo-editor"
                onEditorStateChange={this.onEditorStateChange}
                editorState={this.state.editorState}
                mention={{
                    separator: ' ',
                    trigger: '@',
                    suggestions: characterTags
                }}
            />
                <div className = "center-content"><button className="green-button saveButton" disabled={disabledSave} onClick={() => saveHistory(draftToHtml(convertToRaw(this.state.editorState.getCurrentContent())))}>Zapisz <span>{element}</span></button>
                    <Tooltip arrow={true} placement={"right"} title="Przed zapisem upewnij się, że dane są poprawne. Raz stworzonej historii nie można łatwo edytować.">
                        <IconButton aria-label="info">
                            <InfoOutlinedIcon />
                        </IconButton>
                    </Tooltip>
                </div>

</>

        )

    }
}


export default Wysiwyg;
