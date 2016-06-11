
package xml_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{http://www.xmlProjekat.com/akt}Clan"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Akcija">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Dodaj"/>
 *             &lt;enumeration value="Izmeni"/>
 *             &lt;enumeration value="Obrisi"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="Referencira" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "clan"
})
@XmlRootElement(name = "ElementAmandmana", namespace = "http://www.xmlProjekat.com/amandman")
public class ElementAmandmana {

    @XmlElement(name = "Clan", namespace = "http://www.xmlProjekat.com/akt", required = true)
    protected Clan clan;
    @XmlAttribute(name = "Akcija")
    protected String akcija;
    @XmlAttribute(name = "Referencira")
    @XmlSchemaType(name = "anySimpleType")
    protected String referencira;

    /**
     * Gets the value of the clan property.
     * 
     * @return
     *     possible object is
     *     {@link Clan }
     *     
     */
    public Clan getClan() {
        return clan;
    }

    /**
     * Sets the value of the clan property.
     * 
     * @param value
     *     allowed object is
     *     {@link Clan }
     *     
     */
    public void setClan(Clan value) {
        this.clan = value;
    }

    /**
     * Gets the value of the akcija property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAkcija() {
        return akcija;
    }

    /**
     * Sets the value of the akcija property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAkcija(String value) {
        this.akcija = value;
    }

    /**
     * Gets the value of the referencira property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferencira() {
        return referencira;
    }

    /**
     * Sets the value of the referencira property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferencira(String value) {
        this.referencira = value;
    }

}
