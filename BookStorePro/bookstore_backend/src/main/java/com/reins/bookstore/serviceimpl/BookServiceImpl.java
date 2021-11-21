package com.reins.bookstore.serviceimpl;

import com.reins.bookstore.Lucene.MyIKAnalyzer;
import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Descriptions;
import com.reins.bookstore.entity.Image;
import com.reins.bookstore.entity.Tags;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.entity.vo.BookStatistic;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Image findImageByBookId(Integer id) {
        return bookDao.getBookImage(id);
    }

    @Override
    public BookStatistic getBookStatistic() {
        BookStatistic statistic = new BookStatistic();
        statistic.setBookNumber(bookDao.getBookNumber());
        statistic.setBookInventorySum(bookDao.getBookInventorySum());
        return  statistic;
    }

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void decreaseInventory(Integer num,Integer book_id) throws Exception{
        Integer inventory = bookDao.getBookInventory(book_id);
        System.out.println(inventory);
        if(num > inventory){
            throw new Exception("No Enough Books");
        }
        else if(bookDao.findOne(book_id)==null){
            throw new Exception("The Book Not Exists");
        }
        else{
            bookDao.decreaseInventory(num,book_id);
        }
    }

    @Override
    public List<Book> searchDescriptions(String text) throws IOException, org.apache.lucene.queryparser.classic.ParseException{
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
            Book tmp = bookDao.findOne(list.get(i));
            result.add(tmp);
        }
        return result;
    }

    //建索引
    @PostConstruct
    @Override
    public void createIndex() throws IOException{
        List<Descriptions> list1 = new ArrayList<>();
        List<Book> books = bookDao.getBooks();

        int size = books.size();
        for(int i = 0; i < size; i++){
            list1.add(new Descriptions(books.get(i).getBookId(), books.get(i).getDescription().getData()));
        }

        // 创建文档的集合
        Collection<Document> docs = new ArrayList<>();
        for(int i=0;i<list1.size();i++){
            //contentMapper.insertSelective(list1.get(i));
            // 创建文档对象
            Document document1 = new Document();
            //StringField会创建索引，但是不会被分词，TextField，即创建索引又会被分词。
            document1.add(new StringField("id", list1.get(i).getId() +"", Field.Store.YES));
            document1.add(new TextField("descriptions", list1.get(i).getData(), Field.Store.YES));
            docs.add(document1);
        }

        // 索引目录类,指定索引在硬盘中的位置
        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("c:\\NewBookStore\\BookStorePro\\bookstore_backend\\docs"));
        // 引入IK分词器
        Analyzer analyzer = new MyIKAnalyzer();
        // 索引写出工具的配置对象，这个地方就是最上面报错的问题解决方案
        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        // 创建索引的写出工具类。参数：索引的目录和配置信息
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        // 把文档集合交给IndexWriter
        indexWriter.addDocuments(docs);
        // 提交
        indexWriter.commit();
        // 关闭
        indexWriter.close();
    }

    @Override
    public List<Book> getByTags(String Tag){
        return bookDao.getByTags(Tag);
    }
}
