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

    public Connection conectar() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");

            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            return conexao;
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
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
        try (Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
