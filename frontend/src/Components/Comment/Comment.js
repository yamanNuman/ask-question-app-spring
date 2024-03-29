import React from 'react';
import {makeStyles, OutlinedInput, CardContent, InputAdornment} from "@material-ui/core";
import {Link} from "react-router-dom";
import Avatar from "@mui/material/Avatar";

const useStyles = makeStyles((theme) => ({
    comment: {
        display:"flex",
        flexWrap:"wrap",
        justifyContent:"flex-start",
        alignItems:"center",
    },
    small:{
        width: theme.spacing(4),
        height: theme.spacing(4),
    },
    link: {
        textDecoration: "none",
        boxShadow: "none",
        color: "white"
    }
}));
function Comment(props) {
    const {text,userId,username} = props;
    const classes = useStyles();
    return (
        <CardContent>
            <OutlinedInput
                disabled
                id="outlined-adornment-amount"
                multiline
                inputProps={{maxLength : 225}}
                fullWidth
                value={text}
                startAdornment = {
                    <InputAdornment position="start">
                        <Link className={classes.link} to={`/users/${localStorage.getItem("currentUser")}`}>
                            <Avatar className={classes.avatar} aria-label="recipe">
                                {username.charAt(0).toUpperCase()}
                            </Avatar>
                        </Link>
                    </InputAdornment>
                }
                style = {{color : "black",backgroundColor: 'white'}}
            ></OutlinedInput>
        </CardContent>
    );
}

export default Comment;