import React from 'react';
import {Layout, Carousel} from 'antd'
import '../css/home.css'
import {withRouter} from "react-router-dom";
import {CartTable} from "../components/CartTable";
import {getUserId, getUserName} from "../services/userService";


// 新增购物车页面 用于下订单等操作
class CartView extends React.Component{

    constructor(props) {
        super(props);
        this.state = {user_id:0};
    }

    componentDidMount(){
            var username = getUserName();
            console.log(username)
            const callback =  (data) => {
                this.setState({user_id:data
                });
            };
            getUserId(username,callback);

            console.log("wwwwww"+username);

            console.log(this.state.user_id);

    }

    render(){
        console.log("user_id from view"+this.state.user_id);
        localStorage.setItem("user_id",this.state.user_id);
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