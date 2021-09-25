import React from 'react';
import {Layout, Carousel} from 'antd'
import {HeaderInfo} from "../components/HeaderInfo";
import {SideBar} from "../components/SideBar";
import '../css/home.css'
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/BookCarousel";
import {SearchBar} from "../components/SearchBar";
import {BookList} from "../components/BookList";
import {ChatRoom} from "../components/ChatRoom";

const { Header, Content, Footer } = Layout;

class ChatView extends React.Component{

    componentDidMount(){
    }

    render(){
        return(
            <div className="home-content">
                <div className={"foot-wrapper"}>
                    <ChatRoom/>
                </div>
            </div>
        );
    }
}

export default withRouter(ChatView);