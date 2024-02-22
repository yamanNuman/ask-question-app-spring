import React, {useState} from 'react';
import {Button, FormControl, FormHelperText, Input, InputLabel} from "@material-ui/core";
import {Link} from "react-router-dom";


function Login() {
    const[username,setUsername] = useState("");
    const[password,setPassword] = useState("");

    const sendRequest = (path) => {
        fetch(`http://localhost:8080/${path}`,{
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            }),
        })
            .then((res) => res.json())
            .then((result) => {
                localStorage.setItem("token", result.message);
                localStorage.setItem("currentUser",result.userId);
                localStorage.setItem("currentUsername",result.username);
            })
            .catch((err) => console.log(err))
    }
    const handleUsername = (value) => {
        setUsername(value);
    }
    const handlePassword = (value) => {
        setPassword(value);
    }
    const handleRegister = () => {
        sendRequest("api/v1/auth/register")
        setUsername("");
        setPassword("");

    }
    const handleLogin = () => {
        sendRequest("api/v1/auth/login");
        setUsername("");
        setPassword("");

    }
    return (
        <FormControl style={{top:20}}>
            <InputLabel>Username</InputLabel>
            <Input onChange={(i) => handleUsername(i.target.value)}/>
            <InputLabel style={{top:80}}>Password</InputLabel>
            <Input onChange={(i) => handlePassword(i.target.value)}
                   style={{top:40}}/>
            <Button onClick={handleLogin} variant="contained"
                             style={{marginTop:60,
                                 background:"linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)",
                                 color:'white'}}
            >Login</Button>
           <FormHelperText style={{margin:20}}>If you are not registered, click <Link to="/auth">Register</Link></FormHelperText>

        </FormControl>
    );
}

export default Login;