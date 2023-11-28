package views;

import controllers.PerfilController;
import controllers.SalvarSenha;
import models.Pessoa;
import util.DBO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaPerfil extends JDialog {

    private JTextField txtNome;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private PerfilController perfilController;
    private int usuarioLogadoId;

    public TelaPerfil(Frame owner, int usuarioLogadoId, DBO dbo) {
        super(owner, "Perfil", true);
        this.usuarioLogadoId = usuarioLogadoId;
        this.perfilController = new PerfilController(dbo);

        setSize(400, 300);
        setLocationRelativeTo(owner);

        try {
            Pessoa perfil = perfilController.obterPerfilPorId(usuarioLogadoId);
            if (perfil != null) {
                exibirPerfil(perfil);
            } else {
                JOptionPane.showMessageDialog(this, "Perfil não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter perfil.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exibirPerfil(Pessoa pessoa) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtNome = new JTextField(pessoa.getNome(), 20);
        formPanel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        txtTelefone = new JTextField(pessoa.getTelefone(), 20);
        formPanel.add(txtTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        txtEmail = new JTextField(pessoa.getEmail(), 20);
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        txtSenha = new JPasswordField(pessoa.getSenha(), 20);
        formPanel.add(txtSenha, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarPerfil());
        panel.add(btnSalvar, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    private void salvarPerfil() {
        String nome = txtNome.getText();
        String telefone = txtTelefone.getText();
        String email = txtEmail.getText();
        String senha = new String(txtSenha.getPassword());

        Pessoa pessoa = new Pessoa();
        pessoa.setPessoa_id(usuarioLogadoId);
        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setSenha(senha);

        pessoa.setSenha(SalvarSenha.hashPassword(senha));

        try {
            perfilController.atualizarPerfil(pessoa);
            JOptionPane.showMessageDialog(this, "Perfil atualizado com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar perfil.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
