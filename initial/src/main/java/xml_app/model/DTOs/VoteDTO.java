package xml_app.model.DTOs;


public class VoteDTO {
    private String id;
    private int glasoviZa;
    private int glasoviProtiv;
    private int glasoviUzdrzani;

    public int getGlasoviZa() {
        return glasoviZa;
    }

    public void setGlasoviZa(int glasoviZa) {
        this.glasoviZa = glasoviZa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGlasoviProtiv() {
        return glasoviProtiv;
    }

    public void setGlasoviProtiv(int glasoviProtiv) {
        this.glasoviProtiv = glasoviProtiv;
    }

    public int getGlasoviUzdrzani() {
        return glasoviUzdrzani;
    }

    public void setGlasoviUzdrzani(int glasoviUzdrzani) {
        this.glasoviUzdrzani = glasoviUzdrzani;
    }
}
