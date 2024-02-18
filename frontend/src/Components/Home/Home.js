import React, {useEffect, useState} from 'react';
import Post from "../Post/Post";
import './Home.scss'

function Home() {
    let userId = 1;
    const [error,setError] = useState(null);
    const [post,setPost] = useState([]);
    const [isLoaded, setIsLoaded] = useState(false);

    useEffect(() => {
        fetch("http://localhost:8080/api/v1/posts/"+ userId)
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
            <div className="container">
                {post.map(item => (
                    <Post title={item.title} text={item.text}></Post>
                ))}
            </div>
        );
    }
}

export default Home;