//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.3.0 生成的
// 请访问 <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2021.10.14 时间 11:12:32 PM CST 
//


package org.reins.wssample.wsdl;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.reins.wssample.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookId_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "id");
    private final static QName _BookIsbn_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "isbn");
    private final static QName _BookName_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "name");
    private final static QName _BookType_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "type");
    private final static QName _BookAuthor_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "author");
    private final static QName _BookPrice_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "price");
    private final static QName _BookDescription_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "description");
    private final static QName _BookInventory_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "inventory");
    private final static QName _BookImage_QNAME = new QName("http://spring.io/guides/gs-producing-web-service", "Image");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.reins.wssample.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookRequest }
     * 
     */
    public GetBookRequest createGetBookRequest() {
        return new GetBookRequest();
    }

    /**
     * Create an instance of {@link GetBookResponse }
     * 
     */
    public GetBookResponse createGetBookResponse() {
        return new GetBookResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "id", scope = Book.class)
    public JAXBElement<Integer> createBookId(Integer value) {
        return new JAXBElement<Integer>(_BookId_QNAME, Integer.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "isbn", scope = Book.class)
    public JAXBElement<String> createBookIsbn(String value) {
        return new JAXBElement<String>(_BookIsbn_QNAME, String.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "name", scope = Book.class)
    public JAXBElement<String> createBookName(String value) {
        return new JAXBElement<String>(_BookName_QNAME, String.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "type", scope = Book.class)
    public JAXBElement<String> createBookType(String value) {
        return new JAXBElement<String>(_BookType_QNAME, String.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "author", scope = Book.class)
    public JAXBElement<String> createBookAuthor(String value) {
        return new JAXBElement<String>(_BookAuthor_QNAME, String.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "price", scope = Book.class)
    public JAXBElement<BigDecimal> createBookPrice(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_BookPrice_QNAME, BigDecimal.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "description", scope = Book.class)
    public JAXBElement<String> createBookDescription(String value) {
        return new JAXBElement<String>(_BookDescription_QNAME, String.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "inventory", scope = Book.class)
    public JAXBElement<Integer> createBookInventory(Integer value) {
        return new JAXBElement<Integer>(_BookInventory_QNAME, Integer.class, Book.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Image }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Image }{@code >}
     */
    @XmlElementDecl(namespace = "http://spring.io/guides/gs-producing-web-service", name = "Image", scope = Book.class)
    public JAXBElement<Image> createBookImage(Image value) {
        return new JAXBElement<Image>(_BookImage_QNAME, Image.class, Book.class, value);
    }

}
