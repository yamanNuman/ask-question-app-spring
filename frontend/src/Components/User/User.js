import React from 'react';
import {useParams} from "react-router-dom";
import Avatar from "../Avatar/Avatar";

function User(props) {
    const{userId} = useParams();
    return (
        <div>
        <Avatar userId={userId} avatarId={0}/>
        </div>
    );
}
export default User;