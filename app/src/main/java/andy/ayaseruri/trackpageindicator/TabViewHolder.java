package andy.ayaseruri.trackpageindicator;

/**
 * Created by ayaseruri on 15/9/1.
 */
public class TabViewHolder {
    private int iconOrgRes = -1;
    private int iconAftRes = -1;
    private String text = "";
    private int textColorOrg = -1;
    private int textColorAft = -1;

    public TabViewHolder(int iconOrgRes, int iconAftRes, String text, int textColorOrg, int textColorAft) {
        this.iconOrgRes = iconOrgRes;
        this.iconAftRes = iconAftRes;
        this.text = text;
        this.textColorOrg = textColorOrg;
        this.textColorAft = textColorAft;
    }

    public TabViewHolder(String text, int textColorOrg, int textColorAft) {
        this.text = text;
        this.textColorOrg = textColorOrg;
        this.textColorAft = textColorAft;
    }

    public int getIconOrgRes() {
        return iconOrgRes;
    }

    public int getIconAftRes() {
        return iconAftRes;
    }

    public String getText() {
        return text;
    }

    public int getTextColorOrg() {
        return textColorOrg;
    }

    public int getTextColorAft() {
        return textColorAft;
    }
}
