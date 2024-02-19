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
import {makeStyles} from "@material-ui/core";
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
    const{userId,username,title,text} = props;
    const [expanded, setExpanded] = React.useState(false);
    const [like,setLike] = useState(false);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };
    const handleLike = () => {
        setLike(!like)
    }

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
                <IconButton onClick={handleLike} aria-label="add to favorites">
                    <FavoriteIcon style={like ? {color : "red"} : null}/>
                </IconButton>
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
                <CardContent>

                </CardContent>
            </Collapse>
        </Card>
    );
}