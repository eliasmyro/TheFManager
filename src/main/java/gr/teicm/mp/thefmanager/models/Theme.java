package gr.teicm.mp.thefmanager.models;

public enum Theme {
    NAPKIN("net.sourceforge.napkinlaf.NapkinLookAndFeel"),
    SEAGLASS("com.seaglasslookandfeel.SeaGlassLookAndFeel"),
    QUAQUA("ch.randelshofer.quaqua.QuaquaLookAndFeel"),
    ALUMINIUM("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel"),
    HIFI("com.jtattoo.plaf.hifi.HiFiLookAndFeel"),
    BERNSTEIN("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");

    private String themeClassName;

    Theme(String themeName) {
        this.themeClassName = themeName;
    }

    public String getThemeClassName() {
        return themeClassName;
    }
}
