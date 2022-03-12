import {Component} from "react";

class Subject extends Component {
    render() {
        return (
            // render 안에서는 한개의 최상위 태그만 써야 한다.
            <header>
                <h1>{this.props.title}</h1>
                {this.props.sub}
            </header>
        );
    }
}

export default Subject;