package gr.teicm.mp.thefmanager.controllers;

import gr.teicm.mp.thefmanager.DAO.IThemeDAO;
import gr.teicm.mp.thefmanager.DAO.ThemeDAO;

/**
 * Created by Elias Myronidis on 26/3/2014.
 */
public class ReadThemeController implements IReadThemeController {

    IThemeDAO myDAO = new ThemeDAO();

    @Override
    public String readThemeFromFile(){

        String themeName = myDAO.readTheme();

        return themeName;
    }
}
