import "./App.css";
import React, {useEffect, useState} from "react";
import {
  AppBar,
  Button,
  Container,
  Grid,
  List,
  Paper,
  Toolbar,
  Typography,
} from "@mui/material";
import {call, signout} from "./service/ApiService";
import Keyword from "./Keyword";
import AddKeyword from "./AddKeyword";

function App() {
  const [items, setItems] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    call("/keywords?memberId=1", "GET", null)
    .then((response) => {
      setItems(response ? response : null);
      setLoading(false);
    });
  }, []);

  const addItem = (item) => {
    call("/keywords", "POST", item)
    .then((response) => setItems([...items, response]));
  };

  const editItem = (item) => {
    call("/keywords/" + item.id, "PATCH", item)
    .then((response) => setItems([...items]));
  };

  const deleteItem = (item) => {
    call("/keywords/" + item.id, "DELETE", item)
    .then((response) => {
      const newItems = items.filter(e => e.id !== item.id);
      setItems([...newItems]);
    });
  };

  let keywordItems = items.length >= 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {items.map((item) => (
              <Keyword
                  item={item}
                  key={item.id}
                  editItem={editItem}
                  deleteItem={deleteItem}
              />
          ))}
        </List>
      </Paper>
  );

  // navigationBar 추가
  let navigationBar = (
      <AppBar position="static">
        <Toolbar>
          <Grid justifyContent="space-between" container>
            <Grid item>
              <Typography variant="h6">내 알림 키워드</Typography>
            </Grid>
            <Grid item>
              <Button color="inherit" raised onClick={signout}>
                로그아웃
              </Button>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
  );

  /* 로딩중이 아닐 때 렌더링 할 부분 */
  let keywordListPage = (
      <div>
        {navigationBar} {/* 네비게이션 바 렌더링 */}
        <Container maxWidth="md">
          <AddKeyword addItem={addItem} />
          <div className="KeywordList">{keywordItems}</div>
        </Container>
      </div>
  );

  /* 로딩중일 때 렌더링 할 부분 */
  let content = <h1> 로딩중.. </h1>;

  if (!loading) {
    /* 로딩중이 아니면 keywordListPage를 선택*/
    content = keywordListPage;
  }

  /* 선택한 content 렌더링 */
  return <div className="App">{content}</div>;
}

export default App;
