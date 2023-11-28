package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    private static final String URL = "jdbc:postgresql://isabelle.db.elephantsql.com:5432/jpjquvlo";
    private static final String USUARIO = "jpjquvlo";
    private static final String SENHA = "H1BC69P5fjFMg0MWQkTs0k-REhcayKTZ";

    private Connection conexao;

    public DBUtil() {
        this.conexao = null;
    }

    public static Connection conectar() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean executarSQL(String sql) {
        Statement stmt = null;
        try {
            stmt = conexao.createStatement();
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
