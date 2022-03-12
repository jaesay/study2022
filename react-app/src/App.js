import './App.css';
import {Component} from "react";
import Toc from "./components/Toc";
import Content from "./components/Content";
import Subject from "./components/Subject";


class App extends Component {
    render() {
        return (
            <div className="App">
                <Subject title="WEB" sub="world wide web!"></Subject>
                <Toc></Toc>
                <Content title="HTML" desc="HTML is HyperText Markup Language."></Content>
            </div>
        )
    }
}

export default App;
