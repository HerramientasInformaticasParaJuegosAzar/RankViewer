package view;

import card.Play;
import card.Suit;
import card.Value;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JLabel labelRange;
    private JSlider sliderRange;
    private JPanel panelButtons;
    private CardButton[][] cardButtons;
    
    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 688, 566);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{436, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 0, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 0;
        contentPane.add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0};
        gbl_panel.rowHeights = new int[]{425, 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);

        panelButtons = new JPanel();
        GridBagConstraints gbc_panelButtons = new GridBagConstraints();
        gbc_panelButtons.insets = new Insets(0, 0, 5, 0);
        gbc_panelButtons.fill = GridBagConstraints.BOTH;

        gbc_panelButtons.gridx = 0;
        gbc_panelButtons.gridy = 0;
        panel.add(panelButtons, gbc_panelButtons);
        panelButtons.setLayout(new GridLayout(13, 13, 0, 0));

        labelRange = new JLabel("Rango: 0%");
        GridBagConstraints gbc_labelRange = new GridBagConstraints();
        gbc_labelRange.insets = new Insets(0, 0, 5, 0);
        gbc_labelRange.gridx = 0;
        gbc_labelRange.gridy = 2;
        panel.add(labelRange, gbc_labelRange);

        sliderRange = new JSlider();
        sliderRange.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                changeRange();
            }
        });
        GridBagConstraints gbc_slider = new GridBagConstraints();
        gbc_slider.fill = GridBagConstraints.HORIZONTAL;
        gbc_slider.gridx = 0;
        gbc_slider.gridy = 3;
        panel.add(sliderRange, gbc_slider);

        JPanel panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 0;
        contentPane.add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);

        JLabel lblJugador = new JLabel("Jugador:");
        GridBagConstraints gbc_lblJugador = new GridBagConstraints();
        gbc_lblJugador.insets = new Insets(0, 0, 5, 0);
        gbc_lblJugador.gridx = 0;
        gbc_lblJugador.gridy = 0;
        panel_1.add(lblJugador, gbc_lblJugador);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 1;
        panel_1.add(textField, gbc_textField);
        textField.setColumns(10);

        createButtons();
    }

    private void createButtons() {
        cardButtons=new CardButton[13][13];
        for (Play p: Play.values()) {
                this.panelButtons.add(new CardButton(p));
          
        }
    }

    protected void changeRange() {
        this.labelRange.setText("Rango: " + this.sliderRange.getValue() + "%");
    }

}
