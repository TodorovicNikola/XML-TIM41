
package xml_app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Prezime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Email">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="([0-9A-Za-z\._\-])+@([0-9A-Za-z])+\.([0-9A-Za-z])+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Lozinka">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="3"/>
 *               &lt;maxLength value="32"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="KorisnickoIme">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="3"/>
 *               &lt;maxLength value="32"/>
 *               &lt;pattern value="([0-9A-Za-z\._\-])+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="KontaktTelefon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Tip" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Odbornik"/>
 *             &lt;enumeration value="Predsednik"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
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
    "ime",
    "prezime",
    "email",
    "lozinka",
    "korisnickoIme",
    "kontaktTelefon"
})
@XmlRootElement(name = "Korisnik", namespace = "http://www.xmlProjekat.com/korisnik")
public class Korisnik {

    @XmlElement(name = "Ime", namespace = "http://www.xmlProjekat.com/korisnik", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", namespace = "http://www.xmlProjekat.com/korisnik", required = true)
    protected String prezime;
    @XmlElement(name = "Email", namespace = "http://www.xmlProjekat.com/korisnik", required = true)
    protected String email;
    @XmlElement(name = "Lozinka", namespace = "http://www.xmlProjekat.com/korisnik", required = true)
    protected String lozinka;
    @XmlElement(name = "KorisnickoIme", namespace = "http://www.xmlProjekat.com/korisnik", required = true)
    protected String korisnickoIme;
    @XmlElement(name = "KontaktTelefon", namespace = "http://www.xmlProjekat.com/korisnik")
    protected String kontaktTelefon;
    @XmlAttribute(name = "Tip", required = true)
    protected String tip;
    @XmlAttribute(name = "Id", required = true)
    protected int id;

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */

    public Korisnik(String ime, String prezime, String email, String lozinka, String korisnickoIme, String kontaktTelefon, String tip, int id) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.korisnickoIme = korisnickoIme;
        this.kontaktTelefon = kontaktTelefon;
        this.tip = tip;
        this.id = id;
    }

    public Korisnik() {

    }

    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the lozinka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * Sets the value of the lozinka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLozinka(String value) {
        this.lozinka = value;
    }

    /**
     * Gets the value of the korisnickoIme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    /**
     * Sets the value of the korisnickoIme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKorisnickoIme(String value) {
        this.korisnickoIme = value;
    }

    /**
     * Gets the value of the kontaktTelefon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    /**
     * Sets the value of the kontaktTelefon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKontaktTelefon(String value) {
        this.kontaktTelefon = value;
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

}
