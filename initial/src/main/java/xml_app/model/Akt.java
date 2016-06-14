
package xml_app.model;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
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
 *         &lt;element name="Podnosilac" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.xmlProjekat.com/akt}Deo" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.xmlProjekat.com/akt}OsnovniPodaci"/>
 *       &lt;attribute name="DatumPodnosenja" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="Tip">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value=""/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="SluzbeniGlasnik" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Status" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Usvojen"/>
 *             &lt;enumeration value="U proceduri"/>
 *             &lt;enumeration value="U nacelu"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="UNaceluZa" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="UNaceluProtiv" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="UNaceluUzdrzano" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="UCelostiZa" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="UCelostiProtiv" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="UCelostiUzdrzano" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" />
 *       &lt;attribute name="DatumGlasanja" type="{http://www.w3.org/2001/XMLSchema}date" />
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
    "deo"
})
@XmlRootElement(name = "Akt", namespace = "http://www.xmlProjekat.com/akt")
public class Akt {

    @XmlElement(name = "Podnosilac", namespace = "http://www.xmlProjekat.com/akt", required = true)
    protected List<String> podnosilac;
    @XmlElement(name = "Deo", namespace = "http://www.xmlProjekat.com/akt", required = true)
    protected List<Deo> deo;
    @XmlAttribute(name = "DatumPodnosenja")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenja;
    @XmlAttribute(name = "Tip")
    protected String tip;
    @XmlAttribute(name = "SluzbeniGlasnik")
    protected String sluzbeniGlasnik;
    @XmlAttribute(name = "userId")
    protected String userId;
    @XmlAttribute(name = "Status", required = true)
    protected String status;
    @XmlAttribute(name = "UNaceluZa")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger uNaceluZa;
    @XmlAttribute(name = "UNaceluProtiv")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger uNaceluProtiv;
    @XmlAttribute(name = "UNaceluUzdrzano")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger uNaceluUzdrzano;
    @XmlAttribute(name = "UCelostiZa")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger uCelostiZa;
    @XmlAttribute(name = "UCelostiProtiv")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger uCelostiProtiv;
    @XmlAttribute(name = "UCelostiUzdrzano")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger uCelostiUzdrzano;
    @XmlAttribute(name = "DatumGlasanja")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumGlasanja;
    @XmlAttribute(name = "Id", required = true)
    protected String id;
    @XmlAttribute(name = "Naslov")
    protected String naslov;
    @XmlAttribute(name = "RedniBroj")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger redniBroj;

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
     * Gets the value of the deo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Deo }
     * 
     * 
     */
    public List<Deo> getDeo() {
        if (deo == null) {
            deo = new ArrayList<Deo>();
        }
        return this.deo;
    }

    /**
     * Gets the value of the datumPodnosenja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumPodnosenja() {
        return datumPodnosenja;
    }

    /**
     * Sets the value of the datumPodnosenja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumPodnosenja(XMLGregorianCalendar value) {
        this.datumPodnosenja = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTip(String value) {
        this.tip = value;
    }

    /**
     * Gets the value of the sluzbeniGlasnik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSluzbeniGlasnik() {
        return sluzbeniGlasnik;
    }

    /**
     * Sets the value of the sluzbeniGlasnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSluzbeniGlasnik(String value) {
        this.sluzbeniGlasnik = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the uNaceluZa property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUNaceluZa() {
        return uNaceluZa;
    }

    /**
     * Sets the value of the uNaceluZa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUNaceluZa(BigInteger value) {
        this.uNaceluZa = value;
    }

    /**
     * Gets the value of the uNaceluProtiv property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUNaceluProtiv() {
        return uNaceluProtiv;
    }

    /**
     * Sets the value of the uNaceluProtiv property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUNaceluProtiv(BigInteger value) {
        this.uNaceluProtiv = value;
    }

    /**
     * Gets the value of the uNaceluUzdrzano property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUNaceluUzdrzano() {
        return uNaceluUzdrzano;
    }

    /**
     * Sets the value of the uNaceluUzdrzano property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUNaceluUzdrzano(BigInteger value) {
        this.uNaceluUzdrzano = value;
    }

    /**
     * Gets the value of the uCelostiZa property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUCelostiZa() {
        return uCelostiZa;
    }

    /**
     * Sets the value of the uCelostiZa property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUCelostiZa(BigInteger value) {
        this.uCelostiZa = value;
    }

    /**
     * Gets the value of the uCelostiProtiv property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUCelostiProtiv() {
        return uCelostiProtiv;
    }

    /**
     * Sets the value of the uCelostiProtiv property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUCelostiProtiv(BigInteger value) {
        this.uCelostiProtiv = value;
    }

    /**
     * Gets the value of the uCelostiUzdrzano property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUCelostiUzdrzano() {
        return uCelostiUzdrzano;
    }

    /**
     * Sets the value of the uCelostiUzdrzano property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUCelostiUzdrzano(BigInteger value) {
        this.uCelostiUzdrzano = value;
    }

    /**
     * Gets the value of the datumGlasanja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumGlasanja() {
        return datumGlasanja;
    }

    /**
     * Sets the value of the datumGlasanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumGlasanja(XMLGregorianCalendar value) {
        this.datumGlasanja = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
