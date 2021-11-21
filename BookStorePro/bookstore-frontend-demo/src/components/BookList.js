import React from 'react';
import {AutoComplete, Button, Icon, Input, List} from 'antd'
import {Book} from './Book'
import {findAuthor, getBooks, searchDescriptions, getByTags} from "../services/bookService";
import { Tag } from 'antd';

export class BookList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {books:[],inputValue:'',selectedTags:['任意'],
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

    handleTagChange(tag, checked) {
        const nextSelectedTags = checked ? [tag] : ['任意'];
        this.setState({ selectedTags: nextSelectedTags });
        console.log('You are interested in: ', nextSelectedTags);
        if(!checked){
            const callback =  (data) => {
                this.setState({books:data});
            };

            getBooks({"search":null}, callback);
        }
        else if(nextSelectedTags[0] === '任意'){
            const callback =  (data) => {
                this.setState({books:data});
            };

            getBooks({"search":null}, callback);
        }
        else {
            getByTags(nextSelectedTags, (data) => {this.setState({books: data})})
            console.log('it is : ', this.state.selectedTags);
        }
    }



    render() {
        const { dataSource } = this.state.books;
        const { CheckableTag } = Tag;
        const tagsData = ['任意','古典小说', '世界名著', '社会', '中英双语','儿童文学', '想象', '武侠小说', '科幻', '物理', '魔幻小说'];
        const { selectedTags } = this.state.selectedTags;

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

                {/*标签选择框*/}
                <div>
                    <span style={{ marginRight: 8 }}>类别</span>
                    {tagsData.map(tag => (
                        <CheckableTag
                            key={tag}
                            checked={this.state.selectedTags.indexOf(tag) > -1}
                            onChange={checked => this.handleTagChange(tag, checked)}
                        >
                            {tag}
                        </CheckableTag>
                    ))}
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