package gr.teicm.mp.thefmanager.controllers.themes;

import gr.teicm.mp.thefmanager.DAO.IThemeDAO;
import gr.teicm.mp.thefmanager.DAO.ThemeDAO;

import java.io.IOException;

/**
 * Created by Elias Myronidis on 26/3/2014.
 */
public class WriteThemeController implements IWriteThemeController {

    IThemeDAO myDAO = new ThemeDAO();

    public void writeThemeToXML(String themeName){

        try {
            myDAO.writeTheme(themeName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
