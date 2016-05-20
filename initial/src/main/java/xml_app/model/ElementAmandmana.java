
package xml_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
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
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "ElementAmandmana", namespace = "http://www.xmlProjekat.com/amandman")
public class ElementAmandmana {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "Akcija")
    protected String akcija;
    @XmlAttribute(name = "Referencira")
    @XmlSchemaType(name = "anySimpleType")
    protected String referencira;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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
