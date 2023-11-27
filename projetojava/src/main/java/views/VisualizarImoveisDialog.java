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

public class VisualizarImoveisDialog extends JDialog {

    public VisualizarImoveisDialog(Frame owner, List<Imoveis> listaImoveis) {
        super(owner, "Lista de Imóveis", true);
        setSize(400, 300);
        setLocationRelativeTo(owner);

        DefaultListModel<Imoveis> listModel = new DefaultListModel<>();
        JList<Imoveis> imoveisList = new JList<>(listModel);

        // Obtem a lista de imóveis do banco de dados
        List<Imoveis> imoveis = obterListaImoveis();

        for (Imoveis imovel : imoveis) {
            listModel.addElement(imovel);
        }

        JScrollPane scrollPane = new JScrollPane(imoveisList);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnFechar, BorderLayout.SOUTH);

        imoveisList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = imoveisList.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Imoveis imovelSelecionado = listModel.getElementAt(index);
                        exibirDetalhesImovel(imovelSelecionado);
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    private List<Imoveis> obterListaImoveis() {
        try {
            DBO dbo = new DBO(DBUtil.conectar());
            return dbo.listarImoveis();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao obter a lista de imóveis.", "Erro", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    private void exibirDetalhesImovel(Imoveis imovel) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Tipo: ").append(imovel.getTipo()).append("\n");
        mensagem.append("Preço: R$ ").append(imovel.getPreco()).append("\n");
        mensagem.append("Metros Quadrados: ").append(imovel.getMetros_quadrados()).append("\n");

        JOptionPane.showMessageDialog(this, mensagem.toString(), "Detalhes do Imóvel", JOptionPane.INFORMATION_MESSAGE);
    }
}
