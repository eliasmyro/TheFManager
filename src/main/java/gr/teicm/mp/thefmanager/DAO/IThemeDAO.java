package gr.teicm.mp.thefmanager.DAO;

import java.io.IOException;

/**
 * Created by EliasMyro on 24/3/2014.
 */
public interface IThemeDAO {
    void writeTheme(String themeName) throws IOException;
    public String readTheme();
}
