import './Welcome.css'
import {useNavigate} from "react-router-dom";
import {Button} from "@material-ui/core";
import React from "react";

const Welcome = () => {

    let navigate = useNavigate();
    const navigateLogin = ()=> {
        navigate('/login');
    }
    const navigateSignUp = () => {
        navigate('/signup')
    }
    return (
        <div className="w-container">
            <div className='welcome-container'>
                <div className='welcome-header'>
                    <div className='welcome-text'>Ask Question App</div>
                    <div className="welcome-underline"></div>
                </div>
                <div className="welcome-submit-container">
                    <Button onClick={navigateLogin} variant="contained"
                            style={{width: "200px",
                                background:"linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)",
                                color:'white'}}
                    >Login</Button>
                    <Button onClick={navigateSignUp} variant="contained"
                            style={{width: "200px",
                                background:"linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)",
                                color:'white'}}
                    >Register</Button>
                </div>
            </div>
        </div>
    );
};

export default Welcome;