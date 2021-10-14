import React from 'react';
import {Layout, Carousel} from 'antd'
import {HeaderInfo} from "../components/HeaderInfo";
import {SideBar} from "../components/SideBar";
import '../css/home.css'
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/BookCarousel";
import {SearchBar} from "../components/SearchBar";
import {BookList} from "../components/BookList";
import {visitTimes} from "../services/bookService";

const { Header, Content, Footer } = Layout;

class HomeView extends React.Component{

    componentDidMount(){
        const callback =  (data) => {
            this.setState({books:data});
        };

        visitTimes({"search":null}, callback);

    }

    render(){
        return(
            <div className="home-content">
                {/*<SearchBar />*/}

                <BookCarousel />
                <BookList />
                <div className={"foot-wrapper"}>
                </div>
            </div>
        );
    }
}

export default withRouter(HomeView);