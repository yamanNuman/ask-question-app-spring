import React, {useState} from 'react';
import {makeStyles, OutlinedInput, CardContent, InputAdornment, Button} from "@material-ui/core";
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
function CommentForm(props) {
    const {userId,postId} = props;
    const classes = useStyles();
    const [text,setText] = useState("");
    const saveComment = () => {
        fetch(`http://localhost:8080/api/v1/comment/${postId}/1`,{
            method: "POST",
            headers: {
                "Content-Type" : "application/json",
                "Authorization" : localStorage.getItem("token")
            },
            body: JSON.stringify({
                text: text
            }),
        })
            .then((res)=>res.json())
            .catch((err) => console.log(err))
    }
    const handleSubmit = () => {
        saveComment();
        setText("");
        window.location.reload();
    }
    const handleChange = (value) => {
        setText(value);
    }
    return (
        <CardContent>
            <OutlinedInput
                id="outlined-adornment-amount"
                multiline
                inputProps={{maxLength : 225}}
                fullWidth
                onChange = {(i) => handleChange(i.target.value)}
                startAdornment = {
                    <InputAdornment position="start">
                        <Link className={classes.link} to={`/users/${userId}`}>
                            <Avatar className={classes.avatar} aria-label="recipe">
                                {/*{username.charAt(0).toUpperCase()}*/}
                            </Avatar>
                        </Link>
                    </InputAdornment>
                }
                endAdornment={
                    <InputAdornment position="end">
                        <Button
                            variant="contained"
                            style={{background : "linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)",color:'white'}}
                            onClick={handleSubmit}
                        >Send</Button>
                    </InputAdornment>
                }
                value = {text}
                style = {{color : "black",backgroundColor: 'white'}}
            ></OutlinedInput>
        </CardContent>
    );
}

export default CommentForm;