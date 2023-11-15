package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Pessoa;

public class DBO {
    private Connection connection;

   
    public DBO(Connection connection) {
      this.connection = connection;
    }

    public void conectar(Connection connection) throws SQLException {
        this.connection = connection;
    }

}
