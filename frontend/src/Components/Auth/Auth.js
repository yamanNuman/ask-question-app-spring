import React, {useState} from 'react';
import {Button, FormControl, FormHelperText, Input, InputLabel} from "@material-ui/core";
import {Link} from "react-router-dom";
import Swal from "sweetalert2";


function Auth() {
    const[username,setUsername] = useState("");
    const[password,setPassword] = useState("");

    const alert = () => {
        Swal.fire({
            position: "center",
            icon: "success",
            title: "Successfully registered.",
            showConfirmButton: true,
            timer: 1500
        })
            .then(
                function () {
                    window.location.href ="/login";
                }
            )
    }
    const errorAlert = () => {
        Swal.fire({
            position: "center",
            icon: "error",
            title: "Username available",
            showConfirmButton: true,
            timer: 1500
        })
            .then(
                function () {
                    window.location.href ="/auth";
                }
            )
    }
    const sendRequest = (path) => {
        fetch(`http://localhost:8080/api/v1/auth/register`,{
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
                if(result.message === "Username already in use") {
                    errorAlert();
                }else {
                    alert();
                }
            })
            .catch((err) => {
                console.log(err)
            })
    }
    const handleUsername = (value) => {
        setUsername(value);
    }
    const handlePassword = (value) => {
        setPassword(value);
    }

    return (
        <FormControl style={{top:20}}>
            <InputLabel>Username</InputLabel>
            <Input onChange={(i) => handleUsername(i.target.value)}/>
            <InputLabel style={{top:80}}>Password</InputLabel>
            <Input onChange={(i) => handlePassword(i.target.value)}
                style={{top:40}}/>
          <Button onClick={sendRequest} variant="contained"
                                       style={{marginTop:60,
                                           background:"linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)",
                                           color:'white'}}
            >Register</Button>
                    <FormHelperText style={{margin:20}}>Are your already registered ? Click on the <Link to="/login">Login</Link></FormHelperText>
        </FormControl>
    );
}

export default Auth;