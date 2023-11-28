package views;

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import models.Servico;
import util.DBO;
import util.DBUtil;

public class TelaGerenciarReservas extends JDialog {
    private int usuarioId;
    private JList<Servico> listaReservas;
    private DefaultListModel<Servico> modeloLista;

    public TelaGerenciarReservas(Frame owner, int usuarioId) {
        super(owner, "Gerenciar Reservas", true);
        this.usuarioId = usuarioId;
        setSize(500, 300);
        setLocationRelativeTo(owner);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        modeloLista = new DefaultListModel<>();
        listaReservas = new JList<>(modeloLista);
        atualizarListaReservas();
    
        JButton btnExcluir = new JButton("Excluir Reserva");
        btnExcluir.addActionListener(e -> excluirReservaSelecionada());
    
        // JButton btnEditar = new JButton("Editar Reserva");
        // btnEditar.addActionListener(e -> editarReservaSelecionada());
    
        JPanel botoesPanel = new JPanel();
        botoesPanel.add(btnExcluir);
        // botoesPanel.add(btnEditar);
    
        JScrollPane scrollPane = new JScrollPane(listaReservas);
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
    
        JPanel painel = new JPanel(new BorderLayout());
        painel.add(botoesPanel, BorderLayout.NORTH); // Agora painel está definido
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(btnFechar, BorderLayout.SOUTH);
    
        setLayout(new BorderLayout());
        add(painel, BorderLayout.CENTER);
    }    

    private void atualizarListaReservas() {
        modeloLista.clear();
        List<Servico> reservas = obterReservasUsuario();
        reservas.forEach(modeloLista::addElement);
    }

    private List<Servico> obterReservasUsuario() {
        try {
            DBO dbo = new DBO(DBUtil.conectar());
            return dbo.reservasUsuario(usuarioId);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao buscar reservas.", "Erro", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    private void excluirReservaSelecionada() {
        Servico reservaSelecionada = listaReservas.getSelectedValue();
        if (reservaSelecionada != null) {
            int confirmacao = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir a reserva selecionada?",
                    "Excluir Reserva",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacao == JOptionPane.YES_OPTION) {
                try {
                    DBO dbo = new DBO(DBUtil.conectar());
                    dbo.excluirReserva(reservaSelecionada.getServico_id());
                    JOptionPane.showMessageDialog(this, "Reserva excluída com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    atualizarListaReservas();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma reserva para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // private void editarReservaSelecionada() {
    //     Servico reservaSelecionada = listaReservas.getSelectedValue();
    //     if (reservaSelecionada != null) {
    //     } else {
    //         JOptionPane.showMessageDialog(this, "Selecione uma reserva para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
    //     }
    // }
}
