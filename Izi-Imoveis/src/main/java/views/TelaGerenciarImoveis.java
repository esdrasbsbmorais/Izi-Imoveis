package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import models.Imoveis;
import util.DBO;
import util.DBUtil;

public class TelaGerenciarImoveis extends JDialog {

    private int usuarioId;
    private JList<Imoveis> imoveisList;
    private DefaultListModel<Imoveis> listModel;

    public TelaGerenciarImoveis(Frame owner, int usuarioId) {
        super(owner, "Gerenciar Meus Imóveis", true);
        this.usuarioId = usuarioId;
        setSize(400, 300);
        setLocationRelativeTo(owner);
        initializeComponents();
    }

    private void initializeComponents() {
        listModel = new DefaultListModel<>();
        imoveisList = new JList<>(listModel);
        atualizarListaImoveis();

        JScrollPane scrollPane = new JScrollPane(imoveisList);
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JButton btnEditar = new JButton("Editar Imóvel Selecionado");
        btnEditar.addActionListener(e -> editarImovelSelecionado());

        imoveisList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editarImovelSelecionado();
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnFechar, BorderLayout.SOUTH);
        panel.add(btnEditar, BorderLayout.NORTH);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private void editarImovelSelecionado() {
        Imoveis imovelSelecionado = imoveisList.getSelectedValue();
        if (imovelSelecionado != null) {
            TelaEditarImovel editarImovelDialog = new TelaEditarImovel(this, imovelSelecionado);
            editarImovelDialog.setVisible(true);
            atualizarListaImoveis();
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum imóvel selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarListaImoveis() {
        listModel.clear();
        List<Imoveis> meusImoveis = obterMeusImoveis();
        meusImoveis.forEach(listModel::addElement);
    }

    private List<Imoveis> obterMeusImoveis() {
        try {
            DBO dbo = new DBO(DBUtil.conectar());
            return dbo.listarImoveisUsuario(usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter a lista de meus imóveis.", "Erro", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }
}
