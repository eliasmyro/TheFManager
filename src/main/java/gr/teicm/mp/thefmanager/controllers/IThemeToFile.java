package gr.teicm.mp.thefmanager.controllers;

/**
 * Created by EliasMyro on 24/3/2014.
 */
public interface IThemeToFile {
    public void writeThemeToFile(String themeName);
    public String readThemeFromFile();
}
