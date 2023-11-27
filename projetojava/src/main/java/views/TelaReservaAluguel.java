package views;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TelaReservaAluguel extends JDialog {

    private JComboBox<String> imoveisComboBox;
    private JDateChooser dataInicioChooser;
    private JDateChooser dataFimChooser;
    private JTextField valorImovelField;
    private String imovelSelecionado;

    public TelaReservaAluguel(TelaBuscaImoveis telaBuscaImoveis, String imovelSelecionado) {
        super(telaBuscaImoveis, "Reserva de Aluguel", true);

        this.imovelSelecionado = imovelSelecionado;

        setSize(400, 300);
        setLocationRelativeTo(telaBuscaImoveis);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Selecione o Imóvel:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        imoveisComboBox = new JComboBox<>(new String[]{imovelSelecionado});
        imoveisComboBox.setEnabled(false);
        formPanel.add(imoveisComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Data de Início:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        dataInicioChooser = new JDateChooser();
        formPanel.add(dataInicioChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Data de Fim:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        dataFimChooser = new JDateChooser();
        formPanel.add(dataFimChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Valor do Imóvel:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        valorImovelField = new JTextField(10);
        formPanel.add(valorImovelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Imóvel Selecionado:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JTextField imovelSelecionadoField = new JTextField(imovelSelecionado);
        imovelSelecionadoField.setEditable(false);
        formPanel.add(imovelSelecionadoField, gbc);

        panel.add(formPanel, BorderLayout.CENTER);

        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarReserva();
            }
        });
        panel.add(btnReservar, BorderLayout.SOUTH);

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

        if (valorImovelField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira o valor do imóvel.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int dias = calcularDias(dataInicio, dataFim);
        double valorImovel = Double.parseDouble(valorImovelField.getText());

        double valorTotal = calcularValorProporcional(valorImovel, dias);

        JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso. Valor total: R$" + valorTotal, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private int calcularDias(Date dataInicio, Date dataFim) {
        long diffInMillies = Math.abs(dataFim.getTime() - dataInicio.getTime());
        return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    private double calcularValorProporcional(double valorImovel, int dias) {
        int diasNoMes = 30;
        double valorDiario = valorImovel / diasNoMes;
        return valorDiario * dias;
    }
}
