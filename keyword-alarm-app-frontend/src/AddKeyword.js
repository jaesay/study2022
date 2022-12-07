import {useState} from "react";
import {Button, Grid, TextField} from "@mui/material";

const AddKeyword = (props) => {
  // 사용자가 입력을 지정할 오브젝트
  const [item, setItem] = useState({title: ""})
  const addItem = props.addItem;

  // 버튼 클릭 시
  const onButtonClick = () => {
    addItem(item);
    setItem({title: ""});
  }

  // 엔터 누를 시
  const enterKeyEventHandler = (e) => {
    if (e.key === 'Enter') {
      onButtonClick();
    }
  }

  // 사용자가 입력할 때마다 인풋필드에 담긴 문자열을 오브젝트에 저장
  const onInputChange = (e) => {
    setItem({title: e.target.value});
  }

  return (
      <Grid container style={{marginTop: 20}}>
        <Grid xs={10.5} md={10.5} item style={{paddingRight: 16}}>
          <TextField
              placeholder="키워드 입력"
              fullWidth
              onChange={onInputChange}
              onKeyPress={enterKeyEventHandler}
              value={item.title} />
        </Grid>
        <Grid xs={1.5} md={1.5} item >
          <Button fullWidth style={{height: '100%'}} color="secondary" variant="outlined" onClick={onButtonClick}>추가</Button>
        </Grid>
      </Grid>
  )
}

export default AddKeyword