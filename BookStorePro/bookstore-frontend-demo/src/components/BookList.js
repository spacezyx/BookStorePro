import React from 'react';
import {AutoComplete, Button, Icon, Input, List} from 'antd'
import {Book} from './Book'
import {findAuthor, getBooks, searchDescriptions} from "../services/bookService";

export class BookList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {books:[],inputValue:''
        };
    }

    componentDidMount() {

        const callback =  (data) => {
           this.setState({books:data});
        };

        getBooks({"search":null}, callback);

    }

    handleSearch = value => {
        if(value){
            const callback = (data) => {
                this.setState({books: data});
            };
            searchDescriptions(value, callback);
            console.log('this.state.books');
        }
        else {
            const callback =  (data) => {
                this.setState({books:data});
            };

            getBooks({"search":null}, callback);
        }
    };


    InputOnChange = (e) => {
        this.setState({
            inputValue: e.target.value
        });
        console.log("value is "+e.target.value);
    };

    findAuthor = value => {
        if(value){
            const callback = (data) => {
                this.setState({inputValue: data});
            };
            var da = value.target.value;
            findAuthor(da, callback);
        }
    }


    render() {
        const { dataSource } = this.state.books;
        return (

            <div>
            <div className="global-search-wrapper" style={{ width: 300 }}>
                <AutoComplete
                    className="global-search"
                    size="large"
                    style={{ width: '100%' }}
                    // dataSource={books.map(renderOption)}
                    dataSource={dataSource}
                    onSearch={this.handleSearch}
                    placeholder="input here"
                    optionLabelProp="text"
                >
                    <Input
                        suffix={
                            <Button
                                className="search-btn"
                                style={{ marginRight: -12 }}
                                size="large"
                                type="primary"
                            >
                                <Icon type="search" />
                            </Button>
                        }
                    />
                </AutoComplete>
            </div>

                {/*<Input cols={200} rows={1}  disabled={this.state.inputDisable} onPressEnter={this.findAuthor} onChange={this.InputOnChange} value={this.state.inputValue} />*/}

            <List
                grid={{gutter: 10, column: 4}}
                dataSource={this.state.books}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book info={item} />
                    </List.Item>
                )}
            />
            </div>
        );
    }


}