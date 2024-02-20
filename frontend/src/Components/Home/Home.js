import React, {useEffect, useState} from 'react';
import Post from "../Post/Post";
import {makeStyles} from "@material-ui/core";
import PostForm from "../Post/PostForm";

const useStyles = makeStyles((theme) => ({
    container: {
        display: "flex",
        flexWrap: "wrap",
        justifyContent : "center",
        alignItems : "center",
        backgroundColor: '#d9d9d9',
    }
}));
function Home() {
    const classes = useStyles();
    const [error,setError] = useState(null);
    const [post,setPost] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    const refreshPost = () => {
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
    }

    useEffect(() => {
       refreshPost()
    },[]);

    if(error) {
        return <div>Error</div>
    } else if(!isLoaded) {
        return <div>Loading...</div>
    } else {
        return (
            <div className={classes.container}>
                <PostForm refreshPost={refreshPost}/>
                {post.map(item => (
                    <Post likes={item.postLikes} postId={item.id} username={item.username} userId={item.userId} title={item.title} text={item.text}></Post>
                ))}
            </div>
        );
    }
}

export default Home;