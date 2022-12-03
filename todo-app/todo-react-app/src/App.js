import './App.css';
import Todo from "./Todo";
import {useState} from "react";
import {Container, List, Paper} from "@mui/material";
import AddTodo from "./AddTodo";

function App() {
  const [items, setItems] = useState([
      {
        id: "0",
        title: "Hello World 1",
        done: true,
      },
      {
        id: "1",
        title: "Hello World 2",
        done: false,
      },
  ]);

  const addItem = (item) => {
    item.id = "ID-" + items.length; // key를 위한 id
    item.done = false;
    setItems([...items, item]) // 새배열 생성(immutable), 리액트는 레퍼런스를 기준으로 재렌더링하기 때문에 새배열을 만들어줘야 한다.
    console.log("items : ", items);
  }

  let todoItems = items.length > 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {items.map((item) => (
              <Todo item={item} key={item.id} />
          ))}
        </List>
      </Paper>
  )

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo addItem={addItem} />
        <div className="TodoList">{todoItems}</div>
      </Container>
    </div>
  );
}

export default App;
