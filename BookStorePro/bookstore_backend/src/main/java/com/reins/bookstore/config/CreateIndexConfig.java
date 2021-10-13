//package com.reins.bookstore.config;
//
//import com.reins.bookstore.Lucene.MyIKAnalyzer;
//import com.reins.bookstore.entity.Descriptions;
//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.document.TextField;
//import org.apache.lucene.index.IndexWriter;
//import org.apache.lucene.index.IndexWriterConfig;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Component
//@Configuration
//public class CreateIndexConfig {
//    @Bean
//    public String createIndex() throws IOException {
//        List<Descriptions> list1 = new ArrayList<>();
//        list1.add(new Descriptions(1,"“War of the Worlds for the 21st century.” – Wall Street Journal\n" +
//                "\n" +
//                "The Three-Body Problem is the first chance for English-speaking readers to experience the Hugo Award-winning phenomenon from China's most beloved science fiction author, Liu Cixin.\n" +
//                "\n" +
//                "Set against the backdrop of China's Cultural Revolution, a secret military project sends signals into space to establish contact with aliens. An alien civilization on the brink of destruction captures the signal and plans to invade Earth. Meanwhile, on Earth, different camps start forming, planning to either welcome the superior beings and help them take over a world seen as corrupt, or to fight against the invasion. The result is a science fiction masterpiece of enormous scope and vision."));
//        list1.add(new Descriptions(2,"The #1 New York Times bestselling novel that introduced Khaled Hosseini to millions of readers the world over.\n" +
//                "\n" +
//                "“A vivid and engaging story that reminds us how long his people [of Afghanistan] have been struggling to triumph over the forces of violence—forces that continue to threaten them even today.\" –New York Times Book Review\n" +
//                " \n" +
//                "The unforgettable, heartbreaking story of the unlikely friendship between a wealthy boy and the son of his father’s servant, caught in the tragic sweep of history, The Kite Runner transports readers to Afghanistan at a tense and crucial moment of change and destruction. A powerful story of friendship, it is also about the power of reading, the price of betrayal, and the possibility of redemption; and an exploration of the power of fathers over sons—their love, their sacrifices, their lies.\n" +
//                " \n" +
//                "Since its publication in 2003 Kite Runner has become a beloved, one-of-a-kind classic of contemporary literature, touching millions of readers, and launching the career of one of America's most treasured writers."));
//        list1.add(new Descriptions(3,"The Story of the Stone (c. 1760), also known by the title of The Dream of the Red Chamber, is the great novel of manners in Chinese literature.\n" +
//                "\n" +
//                "Divided into five volumes, The Story of the Stone charts the glory and decline of the illustrious Jia family. This novel re-creates the ritualized hurly-burly of Chinese family life that would otherwise be lost and infuses it with affirming Buddhist belief.\n" +
//                "\n" +
//                "For more than seventy years, Penguin has been the leading publisher of classic literature in the English-speaking world. With more than 1,700 titles, Penguin Classics represents a global bookshelf of the best works throughout history and across genres and disciplines. Readers trust the series to provide authoritative texts enhanced by introductions and notes by distinguished scholars and contemporary authors, as well as up-to-date translations by award-winning translators."));
//        list1.add(new Descriptions(4,"A PBS Great American Read Top 100 Pick\n" +
//                "\n" +
//                "Few stories are as widely read and as universally cherished by children and adults alike as The Little Prince. Richard Howard's translation of the beloved classic beautifully reflects Saint-Exupéry's unique and gifted style. Howard, an acclaimed poet and one of the preeminent translators of our time, has excelled in bringing the English text as close as possible to the French, in language, style, and most important, spirit. The artwork in this edition has been restored to match in detail and in color Saint-Exupéry's original artwork. Combining Richard Howard's translation with restored original art, this definitive English-language edition of The Little Prince will capture the hearts of readers of all ages.\n" +
//                "\n" +
//                "This title has been selected as a Common Core Text Exemplar (Grades 4-5, Stories)."));
//        list1.add(new Descriptions(5,"Qiao Feng, leader of the Beggars Association, possessed strong leadership qualities and exceptional prowess in martial arts. After he was being accused as the descendant of Qidan, Qiao Feng was distained and rejected, finally became an outcast of the Wulin.\n" +
//                "\n" +
//                "During his investigation of his real origin, Qiao Feng, framed up by evil people again and again for murders of his adoptive father Qiao Sanhuai and master Xuan Ku, later be fooled to think that Duan Zhengchun was responsible for the murders and his parents' deaths and challenged him to a one-on-one fight. However, it turned into a tragedy when his beloved girl A zhu had disguised herself as Duan Zhengchun and allowed Xiao Feng to kill her. During the period of time, Qiao Feng met Duan Yu, a young and native prince of the Dali Kingdom and Xu Zhu, a Monk from the Shaolin Sect who broke the Buddhist code."));
//        list1.add(new Descriptions(6,"A dazzling new edition of J.K. Rowling's Harry Potter and the Sorcerer's Stone, fully illustrated in brilliant color and featuring exclusive interactive paper craft elements, including a foldout Hogwarts letter and more!\n" +
//                "In this stunning new edition of Harry Potter and the Sorcerer's Stone, experience the story as never before. J.K. Rowling's complete and unabridged text is accompanied by full-color illustrations on nearly every page and eight exclusive, interactive paper craft elements: Readers will open Harry's Hogwarts letter, reveal the magical entryway to Diagon Alley, make a sumptuous feast appear in the Great Hall, and more.Designed and illustrated by award-winning design studio MinaLima -- best known for establishing the visual graphic style of the Harry Potter and Fantastic Beasts films -- this edition is sure to be a keepsake for Harry Potter fans, a beautiful addition to any collector's bookshelf, and an enchanting way to introduce the first book in this beloved series to a new generation of readers.”                                ——阿不思·邓布利多"));
//
//        // 创建文档的集合
//        Collection<Document> docs = new ArrayList<>();
//        for(int i=0;i<list1.size();i++){
//            //contentMapper.insertSelective(list1.get(i));
//            // 创建文档对象
//            Document document1 = new Document();
//            //StringField会创建索引，但是不会被分词，TextField，即创建索引又会被分词。
//            document1.add(new StringField("id", list1.get(i).getId() +"", Field.Store.YES));
//            document1.add(new TextField("descriptions", list1.get(i).getDescriptions(), Field.Store.YES));
//            docs.add(document1);
//        }
//
//        // 索引目录类,指定索引在硬盘中的位置
//        Directory directory = FSDirectory.open(FileSystems.getDefault().getPath("c:\\NewBookStore\\BookStorePro\\bookstore_backend\\docs"));
//        // 引入IK分词器
//        Analyzer analyzer = new MyIKAnalyzer();
//        // 索引写出工具的配置对象，这个地方就是最上面报错的问题解决方案
//        IndexWriterConfig conf = new IndexWriterConfig(analyzer);
//        // 设置打开方式：OpenMode.APPEND 会在索引库的基础上追加新索引。OpenMode.CREATE会先清空原来数据，再提交新的索引
//        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
//        // 创建索引的写出工具类。参数：索引的目录和配置信息
//        IndexWriter indexWriter = new IndexWriter(directory, conf);
//        // 把文档集合交给IndexWriter
//        indexWriter.addDocuments(docs);
//        // 提交
//        indexWriter.commit();
//        // 关闭
//        indexWriter.close();
//        return"success";
//    }
//
//}
