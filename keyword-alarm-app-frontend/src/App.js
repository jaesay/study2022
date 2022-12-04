import './App.css';
import {useEffect, useState} from "react";
import {Container, List, Paper} from "@mui/material";
import Keyword from "./Keyword";
import AddKeyword from "./AddKeyword";
import {call} from "./service/ApiService";

function App() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    call("/keywords?memberId=1", "GET", null)
    .then((response) => setItems(response));
  }, []);

  const addItem = (item) => {
    call("/keywords", "POST", item)
    .then((response) => setItems([...items, response]));
  }

  const deleteItem = (item) => {
    call("/keywords/" + item.id, "DELETE", item)
    .then((response) => {
      const newItems = items.filter(e => e.id !== item.id);
      setItems([...newItems]);
    });
  }

  const editItem = (item) => {
    call("/keywords/" + item.id, "PATCH", item)
    .then((response) => setItems([...items]));
  }

  let keywordItems = items.length >= 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {items.map((item) => (
              <Keyword
                  item={item}
                  key={item.id.toString()}
                  deleteItem={deleteItem}
                  editItem={editItem}
              />
          ))}
        </List>
      </Paper>
  )

  return (
      <div className="App">
        <Container maxWidth="md">
          <AddKeyword addItem={addItem} />
          <div className="KeywordList">{keywordItems}</div>
        </Container>
      </div>
  );
}

export default App;
