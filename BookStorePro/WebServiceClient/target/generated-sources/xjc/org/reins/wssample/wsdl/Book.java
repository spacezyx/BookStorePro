//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.3.0 生成的
// 请访问 <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2021.10.14 时间 11:12:32 PM CST 
//


package org.reins.wssample.wsdl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Book complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Book"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence maxOccurs="unbounded"&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="inventory" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="Image" type="{http://spring.io/guides/gs-producing-web-service}Image"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Book", propOrder = {
    "idAndIsbnAndName"
})
public class Book {

    @XmlElementRefs({
        @XmlElementRef(name = "id", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "isbn", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "name", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "type", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "author", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "price", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "description", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "inventory", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class),
        @XmlElementRef(name = "Image", namespace = "http://spring.io/guides/gs-producing-web-service", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> idAndIsbnAndName;

    /**
     * Gets the value of the idAndIsbnAndName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the idAndIsbnAndName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdAndIsbnAndName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}
     * {@link JAXBElement }{@code <}{@link Image }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getIdAndIsbnAndName() {
        if (idAndIsbnAndName == null) {
            idAndIsbnAndName = new ArrayList<JAXBElement<?>>();
        }
        return this.idAndIsbnAndName;
    }

}
