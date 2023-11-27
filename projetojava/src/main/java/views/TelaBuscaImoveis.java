package views;

import models.Imoveis;
import util.DBO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaBuscaImoveis extends JDialog {

    private List<Imoveis> listaImoveis;

    public TelaBuscaImoveis(Frame owner, DBO dbo) {
        super(owner, "Buscar Imóveis", true);

        try {
            this.listaImoveis = dbo.listarImoveis();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar imóveis.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        setSize(500, 300);
        setLocationRelativeTo(owner);

        adicionarListaImoveis();
    }

    private void adicionarListaImoveis() {
        JPanel panel = new JPanel(new BorderLayout());
    
        DefaultListModel<Imoveis> listModel = new DefaultListModel<>();
        for (Imoveis imovel : listaImoveis) {
            listModel.addElement(imovel);
        }
    
        JList<Imoveis> lista = new JList<>(listModel);
    
        JScrollPane scrollPane = new JScrollPane(lista);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        JButton btnReservar = new JButton("Reservar Imóvel Selecionado");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Imoveis imovelSelecionado = lista.getSelectedValue();
                if (imovelSelecionado != null) {
                    abrirTelaReserva(imovelSelecionado);
                } else {
                    JOptionPane.showMessageDialog(TelaBuscaImoveis.this, "Nenhum imóvel selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnReservar, BorderLayout.SOUTH);
    
        add(panel);
    }
    
    private void abrirTelaReserva(Imoveis imovelSelecionado) {
        TelaReservaAluguel telaReserva = new TelaReservaAluguel(this, imovelSelecionado.getDescricao());
        telaReserva.setVisible(true);
    }    
}
