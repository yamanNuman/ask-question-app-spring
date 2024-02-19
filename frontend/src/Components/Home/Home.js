import React, {useEffect, useState} from 'react';
import Post from "../Post/Post";
import {Container, makeStyles} from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
    container: {
        display: "flex",
        flexWrap: "wrap",
        justifyContent : "center",
        alignItems : "center",
        backgroundColor: '#e5e5e5',
    }
}));
function Home() {
    const classes = useStyles();
    const [error,setError] = useState(null);
    const [post,setPost] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        fetch("http://localhost:8080/api/v1/posts")
            .then((response) => response.json())
            .then((result) => {
                setIsLoaded(true)
                setPost(result)
                console.log(result)
            }, (error) => {
                console.log(error)
                setIsLoaded(true);
                setError(error);
            })
    },[]);

    if(error) {
        return <div>Error</div>
    } else if(!isLoaded) {
        return <div>Loading...</div>
    } else {
        return (
            <Container fixed className={classes.container}>
                {post.map(item => (
                    <Post username={item.username} userId={item.userId} title={item.title} text={item.text}></Post>
                ))}
            </Container>
        );
    }
}

export default Home;