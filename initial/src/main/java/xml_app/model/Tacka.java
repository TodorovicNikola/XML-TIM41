
package xml_app.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="Sadrzaj" type="{http://www.xmlProjekat.com/akt}SadrzajTip"/>
 *         &lt;element ref="{http://www.xmlProjekat.com/akt}Podtacka"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.xmlProjekat.com/akt}OsnovniPodaci"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sadrzajOrPodtacka"
})
@XmlRootElement(name = "Tacka", namespace = "http://www.xmlProjekat.com/akt")
public class Tacka {

    @XmlElements({
        @XmlElement(name = "Sadrzaj", namespace = "http://www.xmlProjekat.com/akt", type = SadrzajTip.class),
        @XmlElement(name = "Podtacka", namespace = "http://www.xmlProjekat.com/akt", type = Podtacka.class)
    })
    protected List<Object> sadrzajOrPodtacka;
    @XmlAttribute(name = "Id", required = true)
    protected int id;
    @XmlAttribute(name = "Naslov")
    protected String naslov;
    @XmlAttribute(name = "RedniBroj")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger redniBroj;

    /**
     * Gets the value of the sadrzajOrPodtacka property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sadrzajOrPodtacka property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSadrzajOrPodtacka().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SadrzajTip }
     * {@link Podtacka }
     * 
     * 
     */
    public List<Object> getSadrzajOrPodtacka() {
        if (sadrzajOrPodtacka == null) {
            sadrzajOrPodtacka = new ArrayList<Object>();
        }
        return this.sadrzajOrPodtacka;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the redniBroj property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRedniBroj() {
        return redniBroj;
    }

    /**
     * Sets the value of the redniBroj property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRedniBroj(BigInteger value) {
        this.redniBroj = value;
    }

}
