import React, {useState} from "react"
import {InputBase, ListItem, ListItemText} from "@mui/material";

const Keyword = (props) => {
  const [item, setItem] = useState(props.item);

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
      </ListItem>
  );
};

export default Keyword;