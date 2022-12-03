import './App.css';
import {useState} from "react";
import {Container, List, Paper} from "@mui/material";
import Keyword from "./Keyword";
import AddKeyword from "./AddKeyword";

function App() {
  const [items, setItems] = useState([]);

  const addItem = (item) => {
    item.id = "ID-" + items.length;
    item.done = false;
    setItems([...items, item])
    console.log("items : ", items);
  }

  let keywordItems = items.length > 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {items.map((item) => (
              <Keyword
                  item={item}
                  key={item.id}
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
