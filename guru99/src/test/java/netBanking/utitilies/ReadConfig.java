package netBanking.utitilies;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties properties = new Properties();

    public ReadConfig() {

        File src = new File("./Configuration/config.properties");

        try {
            FileInputStream fis = new FileInputStream(src);
            properties.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is" + e.getMessage());
        }
    }

    public String getApplicationUrl() {
        String url = properties.getProperty("url");
        return url;
    }

    public String getUserName() {
        String user = properties.getProperty("userId");
        return user;
    }

    public String getPassword() {
        String pwd = properties.getProperty("passWord");
        return pwd;
    }

    public String getChromePath() {
        String chromePath = properties.getProperty("chromePath");
        return chromePath;
    }

    public String getFireFoxPath() {
        String firefox = properties.getProperty("fireFoxPath");
        return firefox;
    }

    public String getEdgePath() {
        String iePath = properties.getProperty("iePath");
        return iePath;
    }
}
