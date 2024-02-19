import React from 'react';
import {useParams} from "react-router-dom";

function User(props) {
    const{userId} = useParams();
    return (
        <div>USER - {userId}</div>
    );
}
export default User;