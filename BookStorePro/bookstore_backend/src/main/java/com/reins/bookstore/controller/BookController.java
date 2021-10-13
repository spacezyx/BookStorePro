package com.reins.bookstore.controller;
import com.reins.bookstore.Lucene.MyIKAnalyzer;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Descriptions;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.entity.vo.BookStatistic;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author thunderBoy
 * @Date 2019/11/6 16:07
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks(@RequestBody Map<String, String> params) {
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        return bookService.findBookById(id);
    }

    @RequestMapping("/getBookImage")
    public Image getBookImage(@RequestParam("id") Integer id){
        return bookService.findImageByBookId(id);
    }

    @RequestMapping("/getBookStatistic")
    @PreAuthorize("hasRole('ADMIN')")
    public BookStatistic getBookStatistic(@RequestBody Map<String, String> params) {return bookService.getBookStatistic();}

    @RequestMapping("/searchDescriptions")
    public List<Book> searchDescriptions(@RequestParam("text") String text) throws IOException, org.apache.lucene.queryparser.classic.ParseException {
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("c:\\NewBookStore\\BookStorePro\\bookstore_backend\\docs"));
        // 索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        // 索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);
        //标准分词器，会自动去掉空格啊，is a the 等单词
        Analyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("descriptions", analyzer);
        // 创建查询对象
        Query query = parser.parse(text);
        // 获取前5条记录
        TopDocs topDocs = searcher.search(query, 5);
        // 获取总条数
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        // 获取得分文档对象（ScoreDoc）数组.SocreDoc中包含：文档的编号、文档的得分
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //取出每条查询结果
        List<Integer> list = new ArrayList<>();
        for(ScoreDoc scoreDoc : scoreDocs) {
            //scoreDoc.doc 相当于 docID，根据这个 docID 来获取文档
            Document doc = searcher.doc(scoreDoc.doc);
            String desc = doc.get("descriptions");
            System.out.println(desc);
            String id = doc.get("id");
            Integer t = Integer.parseInt(id);
            System.out.println(t);
            list.add(Integer.valueOf(t));
        }
        List<Book> result = new ArrayList<>();
        int size = list.size();
        for(int i = 0; i<size; i++){
            Book tmp = bookService.findBookById(list.get(i));
            result.add(tmp);
        }
        return result;
    }
}
