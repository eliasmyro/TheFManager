package gr.teicm.mp.thefmanager.DAO;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by EliasMyro on 24/3/2014.
 */
public class ThemeDAO implements IThemeDAO {
    @Override
    public void writeTheme(String themeName) throws IOException {
        File file = new File("theme.txt");
        FileUtils.writeStringToFile(file, themeName);
    }

    public String readTheme(){
        String themeName = null;
        File file = new File("theme.txt");
        try {
            themeName = FileUtils.readFileToString(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return themeName;
    }
}
