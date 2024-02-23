import {Button, CardContent, CardMedia, List,ListItem,Card,Typography,CardActions, ListItemSecondaryAction, makeStyles, Modal, Radio} from "@material-ui/core";
import {useState} from "react";

const useStyles = makeStyles({
    root: {
        maxWidth: 345,
        margin: 50
    },
    modal: {
        display: "flex",
        maxWidth: 200,
    },
});
export default function Avatar(props) {
    const{userId,avatarId} = props;
    const [selectedValue, setSelectedValue] = useState(avatarId);
    const [open, setOpen] = useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const classes = useStyles();
    const handleChange = (event) => {
        setSelectedValue(event.target.value);
    };
    return (
        <div>
            <Card className={classes.root}>
                <CardMedia
                    component="img"
                    alt="User Avatar"
                    image={`/avatars/avatar${selectedValue}.png`}
                    title="User Avatar"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2">
                        {localStorage.getItem("currentUsername")}
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                        User info
                    </Typography>
                </CardContent>
                <CardActions>
                    {localStorage.getItem("currentUser") == userId ? <Button size="small" color="primary"  onClick={handleOpen}>
                        Change Avatar
                    </Button> : ""}
                </CardActions>
            </Card>
            <Modal
                className={classes.modal}
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <List dense>
                    {[1, 2, 3, 4, 5, 6].map((key) => {
                        const labelId = `checkbox-list-secondary-label-${key}`;
                        return (
                            <ListItem key={key} button>
                                <CardMedia
                                    style = {{maxWidth: 100}}
                                    component="img"
                                    alt={`Avatar n°${key}`}
                                    image={`/avatars/avatar${key}.png`}
                                    title="User Avatar"
                                />
                                <ListItemSecondaryAction>
                                    <Radio
                                        edge="end"
                                        value= {key}
                                        onChange={handleChange}
                                        checked={""+selectedValue === ""+key}
                                        inputProps={{ 'aria-labelledby': labelId }}
                                    />
                                </ListItemSecondaryAction>
                            </ListItem>
                        );
                    })}
                </List>
            </Modal>
        </div>
    );
}