package util;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.*;

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
    
        String sql = "SELECT pessoa_id, login, senha FROM Pessoa WHERE login = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setPessoa_id(resultSet.getInt("pessoa_id"));
                    pessoa.setLogin(resultSet.getString("login"));
                    pessoa.setSenha(resultSet.getString("senha"));
                    return pessoa;
                }
            }
        }
    
        return null;
    }    

    public Pessoa obterPerfilPorId(int id) throws SQLException {
        String sql = "SELECT nome, telefone, email, senha FROM Pessoa WHERE pessoa_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Pessoa pessoa = new Pessoa();
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
    
        if (pessoa == null) {
            throw new IllegalArgumentException("Objeto Pessoa é nulo.");
        }
    
        String sql = "UPDATE Pessoa SET nome = ?, telefone = ?, email = ?, senha = ? WHERE pessoa_id = ?";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getTelefone());
            preparedStatement.setString(3, pessoa.getEmail());
            preparedStatement.setString(4, pessoa.getSenha());
            preparedStatement.setInt(5, pessoa.getPessoa_id());
    
            preparedStatement.executeUpdate();
        }
    }    

    public void cadastrarImovel(Imoveis imovel, int pessoaId) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }
    
        String sql = "INSERT INTO Imoveis (pessoa_id, tipo, descricao, garagem, preco_aluguel, idade, endereco, metros_quadrados) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pessoaId);
            preparedStatement.setString(2, imovel.getTipo());
            preparedStatement.setString(3, imovel.getDescricao());
            preparedStatement.setInt(4, imovel.getGaragem());
            preparedStatement.setFloat(5, imovel.getPreco_aluguel());
            preparedStatement.setInt(6, imovel.getIdade());
            preparedStatement.setString(7, imovel.getEndereco());
            preparedStatement.setInt(8, imovel.getMetros_quadrados());
    
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
                    imovel.setImoveis_id(resultSet.getInt("imoveis_id"));
                    imovel.setTipo(resultSet.getString("tipo"));
                    imovel.setDescricao(resultSet.getString("descricao"));
                    imovel.setGaragem(resultSet.getInt("garagem"));
                    imovel.setPreco_aluguel(resultSet.getFloat("preco_aluguel"));
                    imovel.setIdade(resultSet.getInt("idade"));
                    imovel.setEndereco(resultSet.getString("endereco"));
                    imovel.setMetros_quadrados(resultSet.getInt("metros_quadrados"));
    
                    listaImoveis.add(imovel);
                }
            }
        }
    
        return listaImoveis;
    }

    public float obterValorImovelPorId(int imoveis_id) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }
    
        String sql = "SELECT preco_aluguel FROM Imoveis WHERE imoveis_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, imoveis_id);
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getFloat("preco_aluguel");
                } else {
                    throw new SQLException("Imóvel não encontrado com o ID: " + imoveis_id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter o valor do imóvel: " + e.getMessage());
            throw e;
        }
    }    

    public List<Imoveis> listarImoveisUsuario(int pessoaId) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }

        List<Imoveis> listaImoveis = new ArrayList<>();
        String sql = "SELECT * FROM Imoveis WHERE pessoa_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pessoaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Imoveis imovel = new Imoveis();
                    imovel.setImoveis_id(resultSet.getInt("imoveis_id"));
                    imovel.setTipo(resultSet.getString("tipo"));
                    imovel.setDescricao(resultSet.getString("descricao"));
                    imovel.setGaragem(resultSet.getInt("garagem"));
                    imovel.setPreco_aluguel(resultSet.getFloat("preco_aluguel"));
                    imovel.setIdade(resultSet.getInt("idade"));
                    imovel.setEndereco(resultSet.getString("endereco"));
                    imovel.setMetros_quadrados(resultSet.getInt("metros_quadrados"));

                    listaImoveis.add(imovel);
                }
            }
        }
        return listaImoveis;
    }  

    public void atualizarImovel(Imoveis imovel) throws SQLException {
        String sql = "UPDATE Imoveis SET tipo = ?, descricao = ?, preco_aluguel = ?, endereco = ? WHERE imoveis_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, imovel.getTipo());
            preparedStatement.setString(2, imovel.getDescricao());
            preparedStatement.setFloat(3, imovel.getPreco_aluguel());
            preparedStatement.setString(4, imovel.getEndereco());
            preparedStatement.setInt(5, imovel.getImoveis_id());
    
            preparedStatement.executeUpdate();
        }
    }    

    public void registrarReservaServico(int pessoa_id, int imoveis_id, Date dataInicio, Date dataFim, float valorTotal) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new SQLException("Conexão não está disponível.");
        }
    
        String servicoTipo = "Aluguel"; 
        String sql = "INSERT INTO Servico (servico_tipo, imoveis_id, pessoa_id, data_inicio, data_fim, valor_total) VALUES (?, ?, ?, ?, ?, ?)";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, servicoTipo);
            preparedStatement.setInt(2, imoveis_id);
            preparedStatement.setInt(3, pessoa_id);
            preparedStatement.setDate(4, new java.sql.Date(dataInicio.getTime()));
            preparedStatement.setDate(5, new java.sql.Date(dataFim.getTime()));
            preparedStatement.setFloat(6, valorTotal);

            preparedStatement.executeUpdate();
        }
    }

    public List<Servico> reservasUsuario(int pessoaId) throws SQLException {
        List<Servico> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Servico WHERE pessoa_id = ?";
    
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, pessoaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Servico reserva = new Servico(
                        resultSet.getInt("servico_id"),
                        resultSet.getString("servico_tipo"),
                        resultSet.getInt("imoveis_id"),
                        resultSet.getInt("pessoa_id"),
                        resultSet.getDate("data_inicio"),
                        resultSet.getDate("data_fim"),
                        resultSet.getFloat("valor_total")
                    );
                    reservas.add(reserva);
                }
            }
        }
        return reservas;
    }

    public void excluirReserva(int servicoId) throws SQLException {
        String sql = "DELETE FROM Servico WHERE servico_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, servicoId);
            preparedStatement.executeUpdate();
        }
    }

    public boolean isImovelDisponivel(int imovelId, Date dataInicio, Date dataFim) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Servico WHERE imoveis_id = ? AND (data_fim >= ? AND data_inicio <= ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, imovelId);
            preparedStatement.setDate(2, new java.sql.Date(dataInicio.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(dataFim.getTime()));
    
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0;
                }
            }
        }
        return false;
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
