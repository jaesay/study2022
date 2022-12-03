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
  const [readOnly, setReadOnly] = useState(true);
  const editItem = props.editItem;
  const deleteItem = props.deleteItem;

  const turnOffReadOnly = () => {
    setReadOnly(false);
  }

  const turnOnReadOnly = (e) => {
    if (e.key === 'Enter') {
      setReadOnly(true);
    }
  }

  const editEventHandler = (e) => {
    item.title = e.target.value;
    editItem();
  }

  const deleteEventHandler = () => {
    deleteItem(item);
  };

  return (
      <ListItem>
        <ListItemText>
          <InputBase
              inputProps={{ "aria-label": "naked", "readOnly": readOnly }}
              onClick={turnOffReadOnly}
              onKeyDown={turnOnReadOnly}
              onChange={editEventHandler}
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