package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Imoveis;
import models.Pessoa;

public class DBO {

    private Connection connection;

    public DBO(Connection connection) {
        this.connection = connection;
    }    

    public void cadastrarPessoa(Pessoa pessoa) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }

        String sql = "INSERT INTO Pessoa (nome, login, telefone, email, senha) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getLogin());
            preparedStatement.setString(3, pessoa.getTelefone());
            preparedStatement.setString(4, pessoa.getEmail());
            preparedStatement.setString(5, pessoa.getSenha());

            preparedStatement.executeUpdate();
        }
    }

    public Pessoa buscarLogin(String login) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }

        String sql = "SELECT login, senha FROM Pessoa WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setLogin(resultSet.getString("login"));
                    pessoa.setSenha(resultSet.getString("senha"));
                    return pessoa;
                }
            }
        }

        return null;
    }

    public Pessoa obterPerfil(String login) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }

        String sql = "SELECT nome, telefone, email, senha FROM Pessoa WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setLogin(login);
                    pessoa.setNome(resultSet.getString("nome"));
                    pessoa.setTelefone(resultSet.getString("telefone"));
                    pessoa.setEmail(resultSet.getString("email"));
                    pessoa.setSenha(resultSet.getString("senha"));
                    return pessoa;
                }
            }
        }

        return null;
    }

    public void atualizarPerfil(Pessoa pessoa) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }
        if (pessoa == null || pessoa.getLogin() == null) {
            throw new IllegalArgumentException("Perfil ou login nulo.");
        }
    
        String sql = "UPDATE Pessoa SET nome = ?, telefone = ?, email = ?, senha = ? WHERE login = ?";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getTelefone());
            preparedStatement.setString(3, pessoa.getEmail());
            preparedStatement.setString(4, pessoa.getSenha());
            preparedStatement.setString(5, pessoa.getLogin());
    
            preparedStatement.executeUpdate();
        }
    }    

    public void cadastrarImovel(Imoveis imovel) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }

        String sql = "INSERT INTO Imoveis (qtd_quartos, qtd_banheiros, tipo, descricao, garagem, preco, idade, suite, endereco, metros_quadrados) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, imovel.getQtd_quartos());
            preparedStatement.setInt(2, imovel.getQtd_banheiros());
            preparedStatement.setString(3, imovel.getTipo());
            preparedStatement.setString(4, imovel.getDescricao());
            preparedStatement.setInt(5, imovel.getGaragem());
            preparedStatement.setFloat(6, imovel.getPreco());
            preparedStatement.setInt(7, imovel.getIdade());
            preparedStatement.setInt(8, imovel.getSuite());
            // preparedStatement.setString(9, imovel.getFoto());
            preparedStatement.setString(9, imovel.getEndereco());
            preparedStatement.setInt(10, imovel.getMetros_quadrados());

            preparedStatement.executeUpdate();
        }
    }

    public List<Imoveis> listarImoveis() throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }
    
        List<Imoveis> listaImoveis = new ArrayList<>();
    
        String sql = "SELECT * FROM Imoveis";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Imoveis imovel = new Imoveis();
                    imovel.setQtd_quartos(resultSet.getInt("qtd_quartos"));
                    imovel.setQtd_banheiros(resultSet.getInt("qtd_banheiros"));
                    imovel.setTipo(resultSet.getString("tipo"));
                    imovel.setDescricao(resultSet.getString("descricao"));
                    imovel.setGaragem(resultSet.getInt("garagem"));
                    imovel.setPreco(resultSet.getFloat("preco"));
                    imovel.setIdade(resultSet.getInt("idade"));
                    imovel.setSuite(resultSet.getInt("suite"));
                    imovel.setEndereco(resultSet.getString("endereco"));
                    imovel.setMetros_quadrados(resultSet.getInt("metros_quadrados"));
    
                    listaImoveis.add(imovel);
                }
            }
        }
    
        return listaImoveis;
    }
    

    // public String salvarImagemLocalmente(String caminhoOrigem) throws IOException {
    //     String pastaDestino = "src/main/java/images/properties";

    //     File destino = new File(pastaDestino, new File(caminhoOrigem).getName());
    //     Files.copy(Path.of(caminhoOrigem), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

    //     return destino.getAbsolutePath();
    // }

    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
