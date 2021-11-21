import React from 'react';
import { Descriptions, Button } from 'antd';
import {getBookImage} from "../services/bookService";
import { InputNumber } from 'antd';
import {getUserName} from "../services/userService";
import {getUserId} from "../services/userService";
import {addCart} from "../services/cartService";


export class BookDetail extends React.Component{
    constructor(props) {
        super(props);
        this.state = {bookImage:null,num:1,user_id:0};
    }

    componentDidMount() {
        if(this.props.info && this.props.info.bookId){
            this.setImage(this.props.info.bookId)
        }
        var username = getUserName();
        console.log(username)
        const callback =  (data) => {
            this.setState({user_id:data
            });
        };
        getUserId(username,callback);
    }

    setImage = (bookId)=>{
        console.log(bookId)
        const callback =  (data) => {
           this.setState({bookImage:data.imageFile
           });
        };
        if(bookId && !this.state.bookImage){
            getBookImage(bookId, callback);
        }
    }

    onChange = value => {
        this.setState({
            num: value,
        });
    };

    //在输入框发生变化的时候修改状态的值
    handleMaxRestoreUp= (event)=>{
        if(event && event.target && event.target.value){
            let value = event.target.value;
            console.log("value"+value);
            this.setState(()=>({num:value }))
        }
    }

    handleAdd = ()=>{
        var data = {
            "book_id":this.props.info.bookId,
            "num":this.state.num
        }
        console.log(data);
        addCart(data,()=>{});
    }

    render() {

        const {info} = this.props;

        if(info == null){
            return null;
        }

        return (
            <div className={"content"}>
                <div className={"book-detail"}>
                    <div className={"book-image"}><img alt="image" src={this.state.bookImage} style={{width:"350px", height:"350px"}}/></div>
                    <div className={"descriptions"}>
                        <Descriptions>
                            <Descriptions.Item className={"title"} span={3}>{info.name}</Descriptions.Item>
                            <Descriptions.Item label={"作者"} span={3}>{info.author}</Descriptions.Item>
                            <Descriptions.Item label={"分类"} span={3}>{info.tag}</Descriptions.Item>
                            <Descriptions.Item label={"定价"} span={3}>{<span className={"price"}>{'¥' + info.price}</span>}</Descriptions.Item>
                            <Descriptions.Item label={"状态 "} span={3}>{info.inventory !== 0? <span>有货 <span className={"inventory"}>库存{info.inventory}件</span></span> : <span className={"status"}>无货</span>}</Descriptions.Item>
                            <Descriptions.Item label={"作品简介"} span={3}>{info.description.data}</Descriptions.Item>
                        </Descriptions>
                    </div>
                </div>

                <div className={"button-groups"}>

                    {/*选择加购数量*/}
                    <InputNumber min={1} max={info.inventory} defaultValue={1} onChange ={this.onChange}/>

                    <Button type="danger" icon="shopping-cart" size={"large"} onClick={this.handleAdd}>
                        加入购物车
                    </Button>

                    <Button type="danger" icon="pay-circle" size={"large"} style={{marginLeft:"15%"}}ghost>
                        立即购买
                    </Button>
                </div>
            </div>


        )

    }

}
