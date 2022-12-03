import React, {useState} from "react"
import {
  IconButton,
  InputBase,
  ListItem,
  ListItemSecondaryAction,
  ListItemText
} from "@mui/material";
import {DeleteOutlined} from "@mui/icons-material";

const Keyword = (props) => {
  const [item, setItem] = useState(props.item);
  const deleteItem = props.deleteItem;

  const deleteEventHandler = () => {
    deleteItem(item);
  };

  return (
      <ListItem>
        <ListItemText>
          <InputBase
              inputProps={{ "aria-label": "naked" }}
              type="text"
              id={item.id}
              name={item.id}
              value={item.title}
              multiline={true}
              fullWidth={true}
          />
        </ListItemText>
        <ListItemSecondaryAction>
          <IconButton
              aria-label="Delete Keyword"
              onClick={deleteEventHandler}
          ><DeleteOutlined />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
  );
};

export default Keyword;