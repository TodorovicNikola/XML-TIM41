
package xml_app.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml_app.model package. 
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

    private final static QName _Podnosilac_QNAME = new QName("http://www.xmlProjekat.com/akt", "Podnosilac");
    private final static QName _Alineja_QNAME = new QName("http://www.xmlProjekat.com/akt", "Alineja");
    private final static QName _Sadrzaj_QNAME = new QName("http://www.xmlProjekat.com/akt", "Sadrzaj");
    private final static QName _SadrzajTipReferenca_QNAME = new QName("http://www.xmlProjekat.com/akt", "Referenca");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml_app.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SadrzajTip }
     * 
     */
    public SadrzajTip createSadrzajTip() {
        return new SadrzajTip();
    }

    /**
     * Create an instance of {@link ElementAmandmana }
     * 
     */
    public ElementAmandmana createElementAmandmana() {
        return new ElementAmandmana();
    }

    /**
     * Create an instance of {@link Clan }
     * 
     */
    public Clan createClan() {
        return new Clan();
    }

    /**
     * Create an instance of {@link Stav }
     * 
     */
    public Stav createStav() {
        return new Stav();
    }

    /**
     * Create an instance of {@link Tacka }
     * 
     */
    public Tacka createTacka() {
        return new Tacka();
    }

    /**
     * Create an instance of {@link Podtacka }
     * 
     */
    public Podtacka createPodtacka() {
        return new Podtacka();
    }

    /**
     * Create an instance of {@link Amandman }
     * 
     */
    public Amandman createAmandman() {
        return new Amandman();
    }

    /**
     * Create an instance of {@link Odeljak }
     * 
     */
    public Odeljak createOdeljak() {
        return new Odeljak();
    }

    /**
     * Create an instance of {@link Pododeljak }
     * 
     */
    public Pododeljak createPododeljak() {
        return new Pododeljak();
    }

    /**
     * Create an instance of {@link Akt }
     * 
     */
    public Akt createAkt() {
        return new Akt();
    }

    /**
     * Create an instance of {@link Deo }
     * 
     */
    public Deo createDeo() {
        return new Deo();
    }

    /**
     * Create an instance of {@link Glava }
     * 
     */
    public Glava createGlava() {
        return new Glava();
    }

    /**
     * Create an instance of {@link SadrzajTip.Referenca }
     * 
     */
    public SadrzajTip.Referenca createSadrzajTipReferenca() {
        return new SadrzajTip.Referenca();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xmlProjekat.com/akt", name = "Podnosilac")
    public JAXBElement<String> createPodnosilac(String value) {
        return new JAXBElement<String>(_Podnosilac_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SadrzajTip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xmlProjekat.com/akt", name = "Alineja")
    public JAXBElement<SadrzajTip> createAlineja(SadrzajTip value) {
        return new JAXBElement<SadrzajTip>(_Alineja_QNAME, SadrzajTip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SadrzajTip }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xmlProjekat.com/akt", name = "Sadrzaj")
    public JAXBElement<SadrzajTip> createSadrzaj(SadrzajTip value) {
        return new JAXBElement<SadrzajTip>(_Sadrzaj_QNAME, SadrzajTip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SadrzajTip.Referenca }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.xmlProjekat.com/akt", name = "Referenca", scope = SadrzajTip.class)
    public JAXBElement<SadrzajTip.Referenca> createSadrzajTipReferenca(SadrzajTip.Referenca value) {
        return new JAXBElement<SadrzajTip.Referenca>(_SadrzajTipReferenca_QNAME, SadrzajTip.Referenca.class, SadrzajTip.class, value);
    }

}
