package gr.teicm.mp.thefmanager.models.themes;

/**
 * Created by Achilleas Naoumidis on 4/18/14.
 */
public enum EThemes {
    NAPKIN("net.sourceforge.napkinlaf.NapkinLookAndFeel"),
    SEAGLASS("com.seaglasslookandfeel.SeaGlassLookAndFeel"),
    QUAQUA("ch.randelshofer.quaqua.QuaquaLookAndFeel"),
    ALUMINIUM("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"),
    HIFI("com.jtattoo.plaf.hifi.HiFiLookAndFeel"),
    BERNSTEIN("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");

    private String themeClassName;

    EThemes(String themeName) {
        this.themeClassName = themeName;
    }

    public String getThemeClassName() {
        return themeClassName;
    }
}
