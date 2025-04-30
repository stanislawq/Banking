package bank.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbUtil {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) {
                throw new IllegalStateException("db.properties not found in classpath");
            }
            props.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws Exception {
        String url = props.getProperty("jdbc.url");
        String usr = props.getProperty("jdbc.user");
        String pwd = props.getProperty("jdbc.password");
        return DriverManager.getConnection(url, usr, pwd);
    }
}
