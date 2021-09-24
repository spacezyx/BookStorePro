import React from 'react';
import {Layout, Carousel} from 'antd'
import {HeaderInfo} from "../components/HeaderInfo";
import {SideBar} from "../components/SideBar";
import '../css/home.css'
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/BookCarousel";
import {SearchBar} from "../components/SearchBar";
import {BookList} from "../components/BookList";
import {OrderTable} from "../components/OrderTable";
import {BookDetail} from "../components/BookDetail";

const { Header, Content, Footer } = Layout;

class OHome extends React.Component{

    componentDidMount(){
    }

    render(){
        return(
            <div className="home-content">
               <OrderTable/>
                <div className={"foot-wrapper"}>
                </div>
            </div>
        );
    }
}

export default withRouter(OHome);