import React from 'react';
import {Layout, Carousel} from 'antd'
import {HeaderInfo} from "../components/HeaderInfo";
import {SideBar} from "../components/SideBar";
import '../css/home.css'
import {withRouter, Switch} from "react-router-dom";
import {Route} from "react-router";
import HomeView from "./HomeView";
import BookView from "./BookView";
import AdminView from "./AdminView";
import CartView from "./CartView";
import ChatView from "./ChatView";
import OrderView from "./OrderView";


const { Header, Content, Footer } = Layout;

class HomeLayout extends React.Component{

    constructor(props) {
        super(props);
    }

    render(){
        return(
            <Layout className="layout">
                <Header>
                    <HeaderInfo />
                </Header>
                <Layout>
                    <SideBar />
                    <Content style={{ padding: '0 50px' }}>
                        <Switch>
                            <Route path="/home" component={HomeView}/>
                            <Route path="/bookDetails" component={BookView}/>
                            <Route path="/admin" component={AdminView}/>
                            <Route path="/CartView" component={CartView}/>
                            <Route path="/ChatView" component={ChatView}/>
                            <Route path="/OrderView" component={OrderView}/>
                        </Switch>
                    </Content>
                </Layout>
            </Layout>
        );
    }
}

export default withRouter(HomeLayout);