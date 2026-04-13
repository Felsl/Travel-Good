package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    /* CHÚ Ý: Thay đổi user và pass theo máy của bạn */
    private final String serverName = "localhost";
    private final String dbName = "travelgooddb";
    private final String portNumber = "3306";
    private final String userID = "root";
    private final String password = "";   // ← Thay mật khẩu MySQL của bạn vào đây

    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/travelgooddb"
                + "?useUnicode=true&characterEncoding=UTF-8"
                + "&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        return DriverManager.getConnection(url, userID, password);
    }
}