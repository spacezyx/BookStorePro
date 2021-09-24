import React from 'react';
import {Layout, Carousel} from 'antd'
import '../css/home.css'
import {withRouter} from "react-router-dom";
import {CartTable} from "../components/CartTable";
import {getUserId, getUserName} from "../services/userService";
import {OrderTable} from "../components/OrderTable";


// 新增购物车页面 用于下订单等操作
class CartView extends React.Component{

    constructor(props) {
        super(props);
    }

    componentDidMount() {
    }



    render(){
        return(
            <div className="home-content">
                <div className={"foot-wrapper"}>
                    <CartTable/>
                </div>
            </div>
        );
    }
}

export default withRouter(CartView);