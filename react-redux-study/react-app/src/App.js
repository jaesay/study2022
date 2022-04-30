import './App.css';
import {Component} from "react";
import Toc from "./components/Toc";
import Content from "./components/Content";
import Subject from "./components/Subject";


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            mode: 'read',
            selected_content_id: 2,
            subject: {title: 'WEB', sub: 'World Wide Web!'},
            welcome: {title: 'Welcome', desc: "Hello, React!"},
            contents: [
                {id: 1, title: 'HTML', desc: 'HTML is ...'},
                {id: 2, title: 'CSS', desc: 'CSS is ...'},
                {id: 3, title: 'JavaScript', desc: 'JavaScript is ...'}
            ]
        }
    }
    // 리액트에서는 state나 props가 바뀌면 해당 component의 render() 함수가 다시 실행된다. 하위 컨포넌트의 랜더함수도 다시 호출된다.
    render() {
        console.log('App Render');
        var _title, _desc = null;
        if (this.state.mode === 'welcome') {
            _title = this.state.welcome.title;
            _desc = this.state.welcome.desc;
        } else if (this.state.mode === 'read') {
            var i = 0;
            while (i < this.state.contents.length) {
                var data = this.state.contents[i];
                if (data.id === this.state.selected_content_id) {
                    _title = data.title;
                    _desc = data.desc;
                    break;
                }
                i = i + 1;
            }
        }
        console.log('render', this); // App component
        return (
            <div className="App">
                <Subject
                    title={this.state.subject.title}
                    sub={this.state.subject.sub}
                    onChangePage={function () {
                        // 동적으로 state를 바꿀때는 setState
                        this.setState({mode:'welcome'})
                    }.bind(this)} // bind(): 상위 this를 주입해서 해당 함수 내에서 this를 대체
                >
                </Subject>
                <Toc
                    data={this.state.contents}
                    onChangePage={function (id) {
                        this.setState({
                            mode:'read',
                            selected_content_id: Number(id)
                        })
                    }.bind(this)}
                ></Toc>
                <Content title={_title} desc={_desc}></Content>
            </div>
        )
    }
}

export default App;
