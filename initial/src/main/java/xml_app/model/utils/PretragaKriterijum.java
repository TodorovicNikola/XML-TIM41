package xml_app.model.utils;

/**
 * Created by Aleksa on 6/13/2016.
 */
public class PretragaKriterijum {
    private String tip;
    private String kriterijum;
    private String status;

    public PretragaKriterijum(String tip,String kriterijum,String status)
    {
        this.tip=tip;
        this.kriterijum=kriterijum;
        this.status=status;
    }
    public PretragaKriterijum()
    {}

    public String getStatus()
    {
        return status;
    }
    public String getTip()
    {
        return tip;
    }
    public String getKriterijum()
    {
        return kriterijum;
    }
}
