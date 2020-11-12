import React from "react";
import {Link} from "react-router-dom";
import App from './../App';

const inputs = [{
  name: "username",
  placeholder: "username",
  type: "text"
},{
  name: "password",
  placeholder: "password",
  type: "password"
},{
  type: "submit",
  value: "Submit",
  className: "btn"
}]

const props = {
  name: 'loginForm',
  method: 'POST',
  action: '/perform_login',
  inputs: inputs
}

const params = new URLSearchParams(window.location.search)

class LoginPage extends React.Component {



    render(){
        return (<div>    <App {...props} error={params.get('error')} />,</div>
        )
    }

}

export default LoginPage;



