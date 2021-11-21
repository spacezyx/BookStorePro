import React from 'react';
import { Card } from 'antd';

import {Link} from 'react-router-dom'
import {getBookImage, getBooks} from "../services/bookService";

const { Meta } = Card;

export class Book extends React.Component{
    constructor(props) {
        super(props);
        this.state = {bookImage:"",bookId:0};
    }

    setImage = (bookId)=>{
        console.log(bookId)
        const callback =  (data) => {
            this.setState({bookImage:data.imageFile
            });
        };
        getBookImage(bookId, callback);
    }


    componentDidMount(){
        const {info} = this.props;
        this.setImage(info.bookId);
        this.setState({bookId:info.bookId});
    }

    render() {

        const {info} = this.props;
        if(this.state.bookId!==info.bookId){
            this.setImage(info.bookId);
            this.setState({bookId:info.bookId});
        }

        return (
            <Link to={{
                pathname: '/bookDetails',
                search: '?id=' + info.bookId}}
            >
            <Card
                hoverable
                style={{width: 181}}
                cover={<img alt="image" src={this.state.bookImage} className={"bookImg"}/>}
            >
                <Meta title={info.name} description={'¥' + info.price}/>
            </Card>
            </Link>
        );
    }

}

