import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import {Link} from "react-router-dom";
import {makeStyles} from "@material-ui/core";
import {LockOpen} from "@material-ui/icons";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
        textAlign : "left"
    },
    link: {
        textDecoration : "none",
        boxShadow : "none",
        color : "white"
    }
}));
function Navbar(props) {
    const logout = () => {
        localStorage.removeItem("currentUser");
        localStorage.removeItem("currentUsername");
        localStorage.removeItem("token");
    }
    let userId = localStorage.getItem("currentUser");
    const classes = useStyles();
    return (
        <div>
            <AppBar style={{background : "#3c6e71"}} position="static">
                <Toolbar>
                    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                        <MenuIcon />
                    </IconButton>
                    <Typography variant="h6" className={classes.title}>
                        <Link className={classes.link} to="/home">Home</Link>
                    </Typography>
                    <Typography variant="h6">
                        {localStorage.getItem("currentUser") == null ? <Link className={classes.link} to="/"></Link>  :
                            <div>
                                <IconButton onClick={logout}><LockOpen></LockOpen></IconButton>
                            <Link className={classes.link} to={"/users/" + userId}>Profile</Link>
                            </div>
                        }
                    </Typography>
                </Toolbar>
            </AppBar>
        </div>
    );
}
export default Navbar;