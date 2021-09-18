import React from 'react';
import {Button} from 'antd'
import {getCarts} from "../services/cartService";
import {Table, Tooltip} from 'antd';
import {addOrder} from "../services/OrderService";


// 新增购物车表格，用于展示购物车数据
export class CartTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {books: []};
    }


    getCarts = (user_id) => {
        const callback1 = (data) => {
            this.setState({
                books: data
            });
        };
        console.log(this.state.books)

        let data = {
            "user_id": 1
            // "user_id":user_id
        }
        getCarts(data, callback1);
    }

    addOrder = (user_id) => {
        const callback1 = (data) => {
            this.setState({
                books: data
            });
        };
        console.log(this.state.books)

        let data = {
            "user_id": 1
            // "user_id":user_id
        }
        addOrder(data, callback1);
    }


    componentDidMount() {
        var user_id = localStorage.getItem("user_id");

        this.setState({user_id: user_id});
        console.log("www" + user_id);

        this.getCarts(user_id);
    }

    render() {
        const columns = [
            {
                title: 'Name',
                dataIndex: 'book.name',
                key: 'book.name',
                render: text => <a>{text}</a>,
                width: 150,
            },
            {
                title: 'Price',
                dataIndex: 'book.price',
                key: 'book.price',
                width: 80,
            },
            {
                title: 'Type',
                dataIndex: 'book.type',
                key: 'book.type',
                width: 120,
            },
            {
                title: 'Description',
                dataIndex: 'book.description',
                key: 'book.description',
                ellipsis: {
                    showTitle: false,
                },
                render: address => (
                    <Tooltip placement="topLeft" title={address}>
                        {address}
                    </Tooltip>
                ),
            },
            {
                title: 'num',
                dataIndex: 'num',
                key: 'num',
                width: 80,
            },
        ];

        return (
            <div>
                <Table columns={columns} dataSource={this.state.books}/>
                <div className={"button-groups"}>

                    <Button type="danger" icon="pay-circle" size={"large"} style={{marginLeft: "15%"}} ghost
                            onClick={this.addOrder}>
                        现在下单
                    </Button>
                </div>
            </div>

        );
    }

}