package views;

import models.Imoveis;
import util.DBO;
import util.DBUtil;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaEditarImovel extends JDialog {
    private Imoveis imovel;
    private JTextField txtTipo, txtDescricao, txtPrecoAluguel, txtEndereco, txtGaragem, txtIdade, txtMetrosQuadrados;
    // Adicione outros campos conforme necessário

    public TelaEditarImovel(TelaGerenciarImoveis telaGerenciarImoveis, Imoveis imovel) {
        super(telaGerenciarImoveis, "Editar Imóvel", true);
        this.imovel = imovel;
        setSize(400, 300);
        setLocationRelativeTo(telaGerenciarImoveis);
        initializeComponents();
    }

    private void initializeComponents() {
        // Inicializar os componentes do formulário com os dados do imóvel
        txtTipo = new JTextField(imovel.getTipo(), 20);
        txtDescricao = new JTextField(imovel.getDescricao(), 20);
        txtPrecoAluguel = new JTextField(String.valueOf(imovel.getPreco_aluguel()), 20);
        txtEndereco = new JTextField(imovel.getEndereco(), 20);
        txtGaragem = new JTextField(String.valueOf(imovel.getGaragem()), 20);
        txtIdade = new JTextField(String.valueOf(imovel.getIdade()), 20);
        txtMetrosQuadrados = new JTextField(String.valueOf(imovel.getMetros_quadrados()), 20);
        // Adicione outros campos conforme necessário

        JButton btnSave = new JButton("Salvar Alterações");
        btnSave.addActionListener(e -> salvarAlteracoes());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));
        panel.add(new JLabel("Tipo:"));
        panel.add(txtTipo);
        panel.add(new JLabel("Descrição:"));
        panel.add(txtDescricao);
        panel.add(new JLabel("Preço Aluguel:"));
        panel.add(txtPrecoAluguel);
        panel.add(new JLabel("Endereço:"));
        panel.add(txtEndereco);
        panel.add(new JLabel("Garagem:"));
        panel.add(txtGaragem);
        panel.add(new JLabel("Idade:"));
        panel.add(txtIdade);
        panel.add(new JLabel("Metros Quadrados:"));
        panel.add(txtMetrosQuadrados);
        // Adicione outros campos ao painel

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(btnSave, BorderLayout.SOUTH);
    }

    private void salvarAlteracoes() {
        imovel.setTipo(txtTipo.getText());
        imovel.setDescricao(txtDescricao.getText());
        imovel.setPreco_aluguel(Float.parseFloat(txtPrecoAluguel.getText()));
        imovel.setEndereco(txtEndereco.getText());
        imovel.setGaragem(Integer.parseInt(txtGaragem.getText()));
        imovel.setIdade(Integer.parseInt(txtIdade.getText()));
        imovel.setMetros_quadrados(Integer.parseInt(txtMetrosQuadrados.getText()));

        try {
            DBO dbo = new DBO(DBUtil.conectar());
            dbo.atualizarImovel(imovel);
            JOptionPane.showMessageDialog(this, "Imóvel atualizado com sucesso!");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o imóvel.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
