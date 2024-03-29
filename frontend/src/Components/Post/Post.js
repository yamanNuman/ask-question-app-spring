import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import {Container, makeStyles} from "@material-ui/core";
import {Link} from "react-router-dom";
import {useEffect, useRef, useState} from "react";
import Comment from "../Comment/Comment";
import CommentForm from "../Comment/CommentForm";

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

const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
})(({ theme, expand }) => ({
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}));

export default function Post(props) {
    const classes = useStyles();
    const{userId,username,title,text,postId,likes} = props;
    const [expanded, setExpanded] = React.useState(false);
    const [like,setLike] = useState(false);
    const [comment,setComment] = useState([]);
    const [isLoaded,setIsLoaded] = useState(false);
    const [error,setError] = useState(null);
    const isInitialMount = useRef(true);
    const[likeCount,setLikeCount] = useState(likes.length);
    const[likeId,setLikeId] = useState(null);
    let disabled = localStorage.getItem("currentUser") === null ? true : false;

    const handleExpandClick = () => {
        setExpanded(!expanded);
        console.log(comment);
    };
    const handleLike = () => {
        setLike(!like)
        if(!like) {
            saveLike();
            setLikeCount(likeCount + 1)
        } else {
            deleteLike();
            setLikeCount(likeCount - 1)
        }
    }
    const refreshComment = () => {
        fetch(`http://localhost:8080/api/v1/comments/${postId}`)
            .then((res) => res.json())
            .then((result) => {
                setIsLoaded(true);
                setComment(result);
            },
                (error) => {
                console.log(error);
                setError(error);
                setIsLoaded(true);
                })
    }

    useEffect(() => {
        if(isInitialMount.current){
            isInitialMount.current = false;
        } else {
            refreshComment();
        }
    },[])

    const saveLike = () => {
        fetch(`http://localhost:8080/api/v1/like/${postId}/${userId}`,{
            method: "POST",
            headers: {
                "Content-Type":"application/json",
                "Authorization" : localStorage.getItem("token")
            },
        })
            .then((res) => res.json())
            .catch((err) => console.log(err));
    }
    const deleteLike = () => {
        fetch(`http://localhost:8080/api/v1/like/${userId}`,{
            method: "DELETE",
            headers: {
                "Authorization" : localStorage.getItem("token")
            }
        })
            .then((res) => res.json())
            .catch((err) => console.log(err))
    }
    const checkLikes = () => {
        let likeControl = likes.find((like => like.userId === userId));
        if(likeControl != null) {
            setLikeId((likeControl.id));
            console.log(likeId)
            setLike(true);
        }
    }

    useEffect(() => {
        checkLikes()
    },[])
    return (
        <Card className={classes.root}>
            <CardHeader
                avatar={
                    <Link to={`/users/${userId}`} className={classes.link}>
                        <Avatar className={classes.avatar} aria-label="recipe">
                            {username.charAt(0).toUpperCase()}
                        </Avatar>
                    </Link>

                }
                title={title}
            />
            <CardContent>
                <Typography variant="body2" color="text.secondary">
                    {text}
                </Typography>
            </CardContent>
            <CardActions disableSpacing>
                {disabled ? "" : <IconButton
                    onClick={handleLike}
                    aria-label="add to favorites">
                    <FavoriteIcon style={like ? {color : "red"} : null}/>
                    {likeCount}
                </IconButton>}

                <ExpandMore
                    expand={expanded}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more"
                >
                    <CommentIcon />
                </ExpandMore>
            </CardActions>
            <Collapse in={expanded} timeout="auto" unmountOnExit>
                <Container fixed className={classes.container}>
                    {error? "error" :
                    isLoaded? comment.map(item => (
                        <Comment userId={item.userId} username={item.username} text={item.text}></Comment>
                    )):"Loading"}
                    {disabled ? "" : <CommentForm postId={postId} username={username} userId={userId}></CommentForm>}
                </Container>
            </Collapse>
        </Card>
    );
}