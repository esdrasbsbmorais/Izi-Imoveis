package views;

import com.toedter.calendar.JDateChooser;
import models.Imoveis;
import util.DBO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TelaReservaAluguel extends JDialog {

    private JComboBox<String> imoveisComboBox;
    private JDateChooser dataInicioChooser;
    private JDateChooser dataFimChooser;
    private Imoveis imovelSelecionado;
    private DBO dbo;
    private int usuarioId;

    public TelaReservaAluguel(TelaBuscaImoveis telaBuscaImoveis, Imoveis imovelSelecionado, DBO dbo, int usuarioId) {
        super(telaBuscaImoveis, "Reserva de Aluguel", true);
        this.dbo = dbo;
        this.imovelSelecionado = imovelSelecionado;
        this.usuarioId = usuarioId;

        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 14));

        setSize(500, 300);
        setLocationRelativeTo(telaBuscaImoveis);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Imóvel Selecionado:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        imoveisComboBox = new JComboBox<>(new String[]{imovelSelecionado.getDescricao()});
        imoveisComboBox.setEnabled(false);
        panel.add(imoveisComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Data de Início:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        dataInicioChooser = new JDateChooser();
        panel.add(dataInicioChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Data de Fim:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        dataFimChooser = new JDateChooser();
        panel.add(dataFimChooser, gbc);

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReserva();
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(btnReservar, gbc);

        add(panel, BorderLayout.CENTER);
    }

    private void realizarReserva() {
        Date dataInicio = dataInicioChooser.getDate();
        Date dataFim = dataFimChooser.getDate();
    
        if (dataInicio == null || dataFim == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione datas de início e fim.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (dataInicio.after(dataFim)) {
            JOptionPane.showMessageDialog(this, "A data de início deve ser anterior à data de fim.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        if (dataInicio.before(new Date())) {
            JOptionPane.showMessageDialog(this, "A data de início não pode ser anterior à data atual.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        try {
            if (!dbo.isImovelDisponivel(imovelSelecionado.getImoveis_id(), dataInicio, dataFim)) {
                JOptionPane.showMessageDialog(this, "O imóvel não está disponível para as datas selecionadas.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao verificar a disponibilidade do imóvel.", "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
    
        int dias = calcularDias(dataInicio, dataFim);
        double valorTotal = calcularValorProporcional(imovelSelecionado.getPreco_aluguel(), dias);
    
        int resposta = JOptionPane.showConfirmDialog(this, 
            "O valor total da reserva é: R$" + valorTotal + "\nDeseja confirmar a reserva?",
            "Confirmar Reserva", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            try {
                dbo.registrarReservaServico(usuarioId, imovelSelecionado.getImoveis_id(), dataInicio, dataFim, (float) valorTotal);
                JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso. Valor total: R$" + valorTotal, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao registrar a reserva.", "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Reserva não realizada.", "Reserva Cancelada", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private int calcularDias(Date dataInicio, Date dataFim) {
        long diffInMillies = Math.abs(dataFim.getTime() - dataInicio.getTime());
        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private double calcularValorProporcional(double valorImovel, int dias) {
        int diasNoMes = 30;
        double valorDiario = valorImovel / diasNoMes;
        double valorTotal = valorDiario * dias;
    
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);
        return Double.parseDouble(df.format(valorTotal));
    }
}
