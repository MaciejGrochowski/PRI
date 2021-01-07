import React from "react";
import "../styles/globalStyles.css";
import PasswordField from "../components/PasswordField/PasswordField";
import {textsPolish} from "../commons/texts-pl";
import {validationPassword} from "./RegisterPage/validation";
import loginService from "../services/loginService";
import {Link} from "react-router-dom";
import {fronendUrls} from "../commons/urls";


class NewPasswordPage extends React.Component {

    constructor() {
        super();
        this.state = {
            password: "",
            confirmPassword: "",
            errorState: {},
            errorText: {},
            error: false,
            success: false
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

    checkPasswordsSame = () => {
        if(this.state.password && this.state.confirmPassword && this.state.password !== this.state.confirmPassword){
            this.setState({errorState: {...this.state.errorState, confirmPassword: true},
                errorText: {...this.state.errorText, confirmPassword: textsPolish.register.notSamePasswords}})
        }
        else{
            this.setState({errorState: {...this.state.errorState, confirmPassword: undefined},
                errorText: {...this.state.errorText, confirmPassword: undefined}})
        }
    }

    canChangePassword = () => {
        return this.props.match.params.username && this.props.match.params.hashcode && !this.state.errorState.confirmPassword && this.state.password &&
            this.state.confirmPassword && !this.state.errorState.password
    }

    changePassword = () => {

        if(this.state.password === this.state.confirmPassword){
            loginService.changePassword(this.props.match.params.username, this.props.match.params.hashcode, this.state.password)
                .then(r => this.setState({success: true, error: false}))
                .catch(e => this.setState({success: false, error: true}))
        }
    }

    render(){

        if(this.state.success)
            return (<div className="plainPage">
                Zmieniłeś hasło. Możesz teraz się <Link to={fronendUrls.loginPage}>zalogować.</Link>
            </div>)

        if(this.state.error)
            return (<div classname="plainPage">Coś poszło nie tak! Jeśli nie możesz zmienić swojego hasła, skontaktuj się z administracją.</div>)



        return (
            <div className = "plainPage">

                <div className="block-component">

                    <PasswordField
                        error={this.state.errorState.password}
                        label={textsPolish.register.password}
                        valueName={"password"}
                        value={this.state.password}
                        errorText={this.state.errorText.password}
                        handleChangePassword={this.onChangeFunction}
                        setStateFunction={ element => this.setState({password: element})}
                        validationFunc={validationPassword}
                    />
                </div>

                <div className="block-component">
                    <PasswordField
                        error={this.state.errorState.confirmPassword}
                        label={textsPolish.register.confirmPassword}
                        valueName={"confirmPassword"}
                        value={this.state.confirmPassword}
                        errorText={this.state.errorText.confirmPassword}
                        handleChangePassword={this.onChangeFunction}
                        setStateFunction={ element => this.setState({ confirmPassword: element})}
                    />
                </div>

                <button className = "zaloguj-button" disabled={!this.canChangePassword()} onClick={() => this.changePassword()}>Zmień hasło</button>


            </div>
        )
    }

}

export default NewPasswordPage;