package xml_app.model.DTOs;

import xml_app.model.Amandman;

/**
 * Created by Eugene on 6/13/2016.
 */
public class BuildAmandmanDTO {

    private String amandman;
    private String reference;
    private String action;
    private String aktId;

    public String getAktId() {
        return aktId;
    }

    public void setAktId(String aktId) {
        this.aktId = aktId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAmandman() {
        return amandman;
    }

    public void setAmandman(String am) {
        this.amandman = am;
    }

}
