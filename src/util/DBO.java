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

    public void inserirPessoa(Pessoa pessoa) throws SQLException {
      
        String sql = "INSERT INTO Pessoa (pessoa_id, nome, senha) VALUES (?, ?, ?)";

      
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, pessoa.getPessoa_id());
        preparedStatement.setString(2, pessoa.getNome());
        preparedStatement.setString(3, pessoa.getSenha());
        preparedStatement.executeUpdate();

      
    }
}
