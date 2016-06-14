
package xml_app.model;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element name="Podnosilac" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element ref="{http://www.xmlProjekat.com/amandman}ElementAmandmana" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="DatumIVremePodnosenja" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilac",
    "elementAmandmana"
})
@XmlRootElement(name = "Amandman", namespace = "http://www.xmlProjekat.com/amandman")
public class Amandman {

    @XmlElement(name = "Podnosilac", namespace = "http://www.xmlProjekat.com/amandman", required = true)
    protected List<String> podnosilac;
    @XmlElement(name = "ElementAmandmana", namespace = "http://www.xmlProjekat.com/amandman", required = true)
    protected List<ElementAmandmana> elementAmandmana;
    @XmlAttribute(name = "DatumIVremePodnosenja")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar datumIVremePodnosenja;
    @XmlAttribute(name = "Id", required = true)
    protected String id;
    @XmlAttribute(name = "IdAkta", required = true)
    protected String IdAkta;
    @XmlAttribute(name = "userId")
    protected String userId;

    /**
     * Gets the value of the podnosilac property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the podnosilac property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPodnosilac().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPodnosilac() {
        if (podnosilac == null) {
            podnosilac = new ArrayList<String>();
        }
        return this.podnosilac;
    }

    /**
     * Gets the value of the elementAmandmana property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the elementAmandmana property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getElementAmandmana().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ElementAmandmana }
     * 
     * 
     */
    public List<ElementAmandmana> getElementAmandmana() {
        if (elementAmandmana == null) {
            elementAmandmana = new ArrayList<ElementAmandmana>();
        }
        return this.elementAmandmana;
    }

    /**
     * Gets the value of the datumIVremePodnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumIVremePodnosenja() {
        return datumIVremePodnosenja;
    }

    /**
     * Sets the value of the datumIVremePodnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumIVremePodnosenja(XMLGregorianCalendar value) {
        this.datumIVremePodnosenja = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(String value) {
        this.id = value;
    }

    public String getIdAkta() {
        return IdAkta;
    }

    public void setIdAkta(String idAkta) {
        IdAkta = idAkta;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
