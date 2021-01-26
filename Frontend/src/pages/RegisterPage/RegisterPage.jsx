import React from "react";
import "../../styles/globalStyles.css";
import {TextField} from "@material-ui/core";
import {polishCodeErrors, textsPolish} from "../../commons/texts-pl";
import {
    validationConfirmPassword, validationDescription, validationDiscord,
    validationFacebook,
    validationMail,
    validationPassword,
    validationUsername
} from "./validation";
import loginService from "../../services/loginService";
import PasswordField from "../../components/PasswordField/PasswordField";
import RegisterTooltip from "../../components/Tooltip/RegisterTooltip";
import {Link} from "react-router-dom";
import {fronendUrls} from "../../commons/urls";


class RegisterPage extends React.Component {

    constructor(){
        super();
        this.state = {
            errorState: {},
            errorText: {},
            registerData: {},
            registered: false,
            isStatuteCheck: false
        }
    }

    componentDidUpdate(prevProps, prevState) {
        if(prevState.registerData.password !== this.state.registerData.password && prevState.registerData.confirmPassword === this.state.registerData.confirmPassword){
            this.checkPasswordsSame();
        }
    }

    checkPasswordsSame = () => {
        if(this.state.registerData.password && this.state.registerData.confirmPassword && this.state.registerData.password !== this.state.registerData.confirmPassword){
            this.setState({errorState: {...this.state.errorState, confirmPassword: true},
            errorText: {...this.state.errorText, confirmPassword: textsPolish.register.notSamePasswords}})
        }
        else{
            this.setState({errorState: {...this.state.errorState, confirmPassword: undefined},
                errorText: {...this.state.errorText, confirmPassword: undefined}})
        }
    }

    onChangeFunction = async (event, labelName, setStateFunction, validationFunction) => {
        event.persist();
        await setStateFunction(event.target.value);

        if(validationFunction){
            const validOutput = validationFunction(event.target.value);
            let errorText = this.state.errorText;
            let errorState = this.state.errorState;
            if(validOutput.errorState || !validOutput.errorState && this.state.errorState[labelName]){
                errorState[labelName] = validOutput.errorState;
                errorText[labelName] = validOutput.errorText;
                this.setState({
                    errorText: errorText, errorState: errorState
                })
            }
        }
        if(labelName==="password" || labelName==="confirmPassword") this.checkPasswordsSame();
    }

    register = () => {
        const requestBody = this.state.registerData;
        loginService.register(requestBody)
            .then(r => this.setState({registered: true}))
            .catch(e => this.setState({errorResponse: e.response.data}))
        // this.setState({registered: true})


    }

    isPreparedForRegister = () => {
        const registerData = this.state.registerData;
        const errorData = this.state.errorState;
        return !errorData.username && !errorData.password && !errorData.confirmPassword && !errorData.mail && !errorData.discord
        && !errorData.facebook && !errorData.description && registerData.mail && registerData.username && registerData.password &&
            registerData.confirmPassword && this.state.isStatuteCheck;
    }

    setStatuteCheck = event => {
        this.setState({isStatuteCheck: event.target.checked})
    }

    enterListener = event => {
        if (event.keyCode === 13 && this.isPreparedForRegister()) {
            this.register();
        }
    }


    render(){

        if(this.state.registered)
            return (
                <div className = "positive-message">Zarejestrowałeś się! Otrzymasz od nas e-mail pozwalający dokończyć rejestrację. Postepuj zgodnie ze wskazówkami w wiadomości.</div>
            )


        return (
                <div className = "container-login-page">
                <div className = "login-body">
                <div className = "margin-login-body">
                <div className = "login-title">Zarejestruj się</div>
                    {this.state.errorResponse === "MAIL_EXISTS" &&
                    <div className = "error-message">{polishCodeErrors.MAIL_EXISTS}
                    </div>}

                <div className="block-component">
                <TextField error={this.state.errorState.username}
                           label={textsPolish.register.username}
                           value={this.state.registerData.username}
                           helperText = {this.state.errorText.username}
                           onChange={event => this.onChangeFunction(event, "username",
                                   element => this.setState({registerData: {...this.state.registerData, username: element}}),
                               validationUsername)}
                />
                <RegisterTooltip tooltipTextName={textsPolish.registerTooltipTexts.username}/>
</div>
                <div className="block-component">
                <TextField error={this.state.errorState.mail}
                           label={textsPolish.register.mail}
                           value={this.state.registerData.mail}
                           helperText = {this.state.errorText.mail}
                           onChange={event => this.onChangeFunction(event, "mail",
                               element => this.setState({registerData: {...this.state.registerData, mail: element}}),
                               validationMail)}
                />
                    <RegisterTooltip tooltipTextName={textsPolish.registerTooltipTexts.mail}/>
</div>

                <div className="block-component">

                    <PasswordField
                    error={this.state.errorState.password}
                    label={textsPolish.register.password}
                    valueName={"password"}
                    value={this.state.registerData.password}
                    errorText={this.state.errorText.password}
                    handleChangePassword={this.onChangeFunction}
                    setStateFunction={ element => this.setState({registerData: {...this.state.registerData, password: element}})}
                    validationFunc={validationPassword}
                    />
                    <RegisterTooltip tooltipTextName={textsPolish.registerTooltipTexts.password}/>
</div>

                <div className="block-component">
                    <PasswordField
                        error={this.state.errorState.confirmPassword}
                        label={textsPolish.register.confirmPassword}
                        valueName={"confirmPassword"}
                        value={this.state.registerData.confirmPassword}
                        errorText={this.state.errorText.confirmPassword}
                        handleChangePassword={this.onChangeFunction}
                        setStateFunction={ element => this.setState({registerData: {...this.state.registerData, confirmPassword: element}})}
                    />
</div>
                <div className="block-component">
                <TextField error={this.state.errorState.facebook}
                           label={textsPolish.register.facebook}
                           value={this.state.registerData.facebook}
                           helperText = {this.state.errorText.facebook}
                           onChange={event => this.onChangeFunction(event, "facebook",
                               element => this.setState({registerData: {...this.state.registerData, facebook: element}}),
                               validationFacebook)}
                />
                    <RegisterTooltip tooltipTextName={textsPolish.registerTooltipTexts.facebook}/>
</div>
                <div className="block-component">

                <TextField error={this.state.errorState.discord}
                           label={textsPolish.register.discord}
                           value={this.state.registerData.discord}
                           helperText = {this.state.errorText.discord}
                           onChange={event => this.onChangeFunction(event, "discord",
                               element => this.setState({registerData: {...this.state.registerData, discord: element}}),
                               validationDiscord)}
                />
                    <RegisterTooltip tooltipTextName={textsPolish.registerTooltipTexts.discord}/>
</div>
                <div className="block-component">
                <TextField error={this.state.errorState.description}
                           label={textsPolish.register.description}
                           value={this.state.registerData.description}
                           helperText = {this.state.errorText.description}
                           onKeyDown={(e) => this.enterListener(e)}
                           onChange={event => this.onChangeFunction(event, "description",
                               element => this.setState({registerData: {...this.state.registerData, description: element}}),
                               validationDescription)}
                />
                    <RegisterTooltip tooltipTextName={textsPolish.registerTooltipTexts.description}/>
</div>

                    <div>
                        <label className="container" style={{width: '100%'}}>
                            <input type="checkbox" onClick={(event) => this.setStatuteCheck(event)}/>
                            <span className="checkmark"/>
                            <div className = "label margin-label">Akceptuję <Link to={fronendUrls.statute}>Regulamin</Link></div>
                        </label>
                    </div>

                <div className="block-component">
                <button className = "zaloguj-button" disabled={!this.isPreparedForRegister()} onClick={() => this.register()}>Zarejestruj</button>
</div>




            </div>
            </div>
            </div>

        )
    }

}

export default RegisterPage;

// String username;
// String password;
// String description;
// String mail;
// String discord;
// String facebook;
