import * as React from 'react';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import {Button, InputAdornment, makeStyles, OutlinedInput} from "@material-ui/core";
import {Link} from "react-router-dom";
import {useState} from "react";

const useStyles = makeStyles((theme) => ({
    root: {
        width: 1000,
        textAlign : "left",
        margin : 20
    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    },
    expand: {
        transform: 'rotate(0deg)',
        marginLeft: 'auto',
        transition: theme.transitions.create('transform', {
            duration: theme.transitions.duration.shortest,
        }),
    },
    avatar: {
        background: 'linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)',
    },
    link: {
        textDecoration : "none",
        boxShadow : "none",
        color : "white"
    }
}));

export default function PostForm(props) {
    const classes = useStyles();
    const{userId,username} = props;
    const[text,setText] = useState('');
    const[title,setTitle] = useState('');

    const savePost = () => {
        fetch(`http://localhost:8080/api/v1/post/1`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    title: title,
                    text: text,
                })
            })
            .then((res) => res.json())
            .catch((err) => console.log(err))
    }

    const handleSubmit = () => {
        savePost();
        window.location.reload(false);
    }
    const handleTitle = (value) => {
        setTitle(value);
    }
    const handleText = (value) => {
        setText(value);
    }

    return (
       <div>
           <Card className={classes.root}>
            <CardHeader
                avatar={
                    <Link to={`/users/${userId}`} className={classes.link}>
                        <Avatar className={classes.avatar} aria-label="recipe">
                            {username?.charAt(0).toUpperCase()}
                        </Avatar>
                    </Link>

                }
                title={<OutlinedInput
                id="outlined-adornment-amount"
                multiline
                placeholder="Title"
                inputProps={{maxLength : 25}}
                fullWidth
                onChange = {(i) => handleTitle(i.target.value)}
                >
                </OutlinedInput>}
            />
            <CardContent>
                <Typography variant="body2" color="text.secondary">
                    <OutlinedInput
                        id="outlined-adornment-amount"
                        multiline
                        placeholder="Text"
                        inputProps={{maxLength : 225}}
                        fullWidth
                        onChange = {(i) => handleText(i.target.value)}
                        endAdornment={
                            <InputAdornment position="end">
                                <Button
                                    variant="contained"
                                    style={{background : "linear-gradient(45deg, #3c6e71 30%, #21CBF3 90%)",color:'white'}}
                                    onClick={handleSubmit}
                                >Send</Button>
                            </InputAdornment>
                        }
                    >
                    </OutlinedInput>
                </Typography>
            </CardContent>
            <CardActions disableSpacing>
            </CardActions>
        </Card>
       </div>
    );
}