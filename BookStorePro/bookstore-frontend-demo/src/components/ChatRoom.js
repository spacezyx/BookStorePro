import React from "react";
import {Button, Card, Input, Row, Col, Divider} from "antd";



export class ChatRoom extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name:null,
            textarea:null,
            userlist:null,
            inputValue:'',
            userName:'',
            wsocket:'',
            buttonDisable:false,
            nameDisable:false,
            inputDisable:true
        };
    }

    //Done
    componentDidMount() {
        let tmp = new WebSocket("ws://localhost:8080/websocketbot");
        tmp.onmessage = this.onMessage;
        this.setState({wsocket: tmp});
    }

    //Done
    onMessage = event =>{
        console.log("onmessage"+event);
        console.log("onmessage"+event.value);
        console.log("onmessage"+event.target.value);
        let line = "";
        let tmpt = this.state.textarea;
        let tmpu = this.state.userlist;
        let msg = JSON.parse(event.data);
        if (msg.type === "chat") {
            line = msg.name + ": ";
            if (msg.target.length > 0)
                line += "@" + msg.target + " ";
            line += msg.message + "\n";
            tmpt += "" + line;
            this.setState({textarea: tmpt});
        } else if (msg.type === "info") {
            line = "[--" + msg.info + "--]\n";
            tmpt += "" + line;
            this.setState({textarea: tmpt});
        } else if (msg.type === "users") {
            line = "Users:\n";
            for (let i=0; i < msg.userlist.length; i++)
                line += "-" + msg.userlist[i] + "\n";
            tmpu = line;
            this.setState({userlist: tmpu});
        }
        console.log(this.state.userlist);
        console.log(this.state.textarea);
    }

    //Done
    sendJoin = () => {
        if(this.state.name){
            if (this.state.name.length > 0) {
                console.log("sendJoin");
                let joinMsg = {};
                joinMsg.type = "join";
                joinMsg.name = this.state.name;
                console.log(joinMsg);
                let jsons = JSON.stringify(joinMsg);
                console.log(jsons);
                this.state.wsocket.send(jsons);
                this.state.userName = this.state.name;
                this.setState({
                    nameDisable: true,
                    buttonDisable:true,
                    inputDisable:false
                });
            }
        }
    }

    //Done
    getTarget = str => {
        const arr = str.split(" ");
        let target = "";
        for (let i=0; i < arr.length; i++) {
            if (arr[i].charAt(0) === '@') {
                target = arr[i].substring(1,arr[i].length);
                target = target.replace(/(\r\n|\n|\r)/gm,"");
            }
        }
        return target;
    }

    //Done
    cleanTarget = str => {
        const arr = str.split(" ");
        let cleanstr = "";
        for (let i=0; i < arr.length; i++) {
            if (arr[i].charAt(0) !== '@')
                cleanstr += arr[i] + " ";
        }
        return cleanstr.substring(0,cleanstr.length-1);
    }

    //Done
    onChange = (e) => {
        this.setState({
            name: e.target.value
        });
        console.log("value is "+e.target.value);
    };

    InputOnChange = (e) => {
        this.setState({
            inputValue: e.target.value
        });
        console.log("value is "+e.target.value);
    };

    //Done
    checkJoin = (e) => {
        this.setState({
            name: e.target.value
        });
        console.log("value is "+e.target.value);
        if (e.keyCode === 13 && this.state.name.length > 0) {
            this.sendJoin();
        }
    };

    //Done
    sendMessage = evt => {
        let input = evt.target.value;
        console.log(input);
        if (evt.keyCode === 13 && input.length > 0) {
            /* Create a chat message as a JavaScript object */
            const chatMsg = {};
            chatMsg.type = "chat";
            chatMsg.name = this.state.userName;
            let msgstr = input;
            chatMsg.target = this.getTarget(msgstr.replace(/,/g, ""));
            chatMsg.message = this.cleanTarget(msgstr);
            chatMsg.message = chatMsg.message.replace(/(\r\n|\n|\r)/gm,"");
            let jsonstr = JSON.stringify(chatMsg);
            this.state.wsocket.send(jsonstr);
            this.setState({
                inputValue:''
            });
        }
    }

    render() {
        return (
            <div>

                <Divider orientation="left">Welcome to the ChatRoom</Divider>

                <Divider>Begin With New Name</Divider>

                <Row gutter={20}>
                    <Col span={8} style={{width:750}}>
                        <Input placeholder="请输入你的名字"  disabled={this.state.nameDisable} onChange={this.onChange} onPressEnter={this.checkJoin}/>
                    </Col>
                    <Col span={8} style={{width:10}} >
                        <Button type="primary" shape="circle" disabled={this.state.buttonDisable} size={"default"} style={{marginLeft: "15%"}}
                                onClick={this.sendJoin}>
                            √
                        </Button>
                    </Col>
                </Row>

                <Divider orientation="right">Talking Now</Divider>

                <Input cols={200} rows={1}  disabled={this.state.inputDisable} onPressEnter={this.sendMessage} onChange={this.InputOnChange} value={this.state.inputValue} /><br />


                <Row gutter={16}>
                    <Col span={8} style={{width:500}}>
                        <Card title="ChatRoom" readOnly="true"  bordered={false}  style={{'white-space': 'pre-wrap'}} >
                            {this.state.textarea}
                        </Card>
                    </Col>
                    <Col span={8} style={{width:230}} style={{'white-space': 'pre-wrap'}} >
                        <Card title="UserList" readOnly="true"  bordered={false}>
                            {this.state.userlist}
                        </Card>
                    </Col>
                </Row>
            </div>
        );
    }
}