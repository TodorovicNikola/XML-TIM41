package xml_app.model.utils;

/**
 * Created by Vuletic on 15.6.2016.
 */
public class SearchMetadata {

    private String podnosilac;
    private String vremeDonosenjaOd;
    private String vremeDonosenjaDo;
    private String glasnik;
    private String tip;
    private String statusAkta;

    public SearchMetadata() {
    }

    public SearchMetadata(String podnosilac, String vremeDonosenjaOd, String vremeDonosenjaDo, String glasnik, String tip, String statusAkta) {
        this.podnosilac = podnosilac;
        this.vremeDonosenjaOd = vremeDonosenjaOd;
        this.vremeDonosenjaDo = vremeDonosenjaDo;
        this.glasnik = glasnik;
        this.tip = tip;
        this.statusAkta = statusAkta;
    }

    public String getPodnosilac() {
        return podnosilac;
    }

    public String getVremeDonosenjaOd() {
        return vremeDonosenjaOd;
    }

    public String getVremeDonosenjaDo() {
        return vremeDonosenjaDo;
    }

    public String getGlasnik() {
        return glasnik;
    }

    public String getTip() {
        return tip;
    }

    public String getStatusAkta() {
        return statusAkta;
    }
}
