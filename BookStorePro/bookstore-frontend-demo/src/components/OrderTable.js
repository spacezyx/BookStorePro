import React from 'react';
import {Table, Tooltip} from 'antd';
import {getOrder} from "../services/OrderService";
import {withRouter} from "react-router-dom";


export class OrderTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {books: []};
    }


    getOrder = () => {
        console.log("进了这个函数吗？")
        const callback = (data) => {
            console.log("this is data"+data)
            this.setState({
                books: data
            });
        };
        console.log(this.state.books)

        getOrder({},callback);
    }

    componentDidMount() {

        this.getOrder();
        console.log("进了这个函数吗？")
                const callback = (data) => {
                    console.log("this is data"+data)
                    this.setState({
                        books: data
                    });
                };
                console.log(this.state.books)

                // getOrder({},callback);
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
            </div>

        );
    }

}