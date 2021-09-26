import React from 'react';
import {Button, message} from 'antd'
import {getCarts} from "../services/cartService";
import {Table, Tooltip} from 'antd';
import {addOrder} from "../services/OrderService";
import {getBooks} from "../services/bookService";


// 新增购物车表格，用于展示购物车数据
export class CartTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {books: []};
    }


    getCarts = () => {
        console.log("进了这个函数吗？")
        const callback = (data) => {
            console.log("this is data"+data)
            this.setState({
                books: data
            });
        };
        console.log(this.state.books)

        getCarts({},callback);
    }

    addOrder = (state, callback) => {
        const callback1 = (data) => {
            this.setState({
                books: data
            });
        };
        console.log(this.state.books)
        this.forceUpdate();
        message.success('订单已接收');

        addOrder(this.state.books, callback1);
    }


    componentDidMount() {
        this.getCarts();
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
                title: 'Num',
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