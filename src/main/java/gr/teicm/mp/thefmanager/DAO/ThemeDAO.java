package gr.teicm.mp.thefmanager.DAO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Elias Myronidis on 24/3/2014.
 */
public class ThemeDAO implements IThemeDAO {
    @Override
    public void writeTheme(String themeName) throws IOException {

        Properties properties = new Properties();
        properties.setProperty("currentTheme", themeName);

        File file = new File("settings.xml");
        FileOutputStream fileOut = new FileOutputStream(file);
        properties.storeToXML(fileOut, "Settings");
        fileOut.close();
    }

    public String readTheme() throws IOException {
        String themeValue = null;

        File file = new File("settings.xml");
        FileInputStream fileInput = new FileInputStream(file);
        Properties properties = new Properties();
        properties.loadFromXML(fileInput);
        fileInput.close();

        String themeKey = "currentTheme";
        themeValue = properties.getProperty(themeKey);

        return themeValue;
    }
}
