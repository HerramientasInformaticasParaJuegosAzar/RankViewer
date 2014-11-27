package view;

import card.Play;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import rankMatrix.RankMatrix;

public class MainWindow extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JLabel labelRange;
    private JSlider sliderRange;
    private JPanel panelButtons;
    private CardButton[][] cardButtons;
    public boolean isClickActive = false;
    private RankMatrix rankMatrix = new RankMatrix();

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1277, 566);
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
        textField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    showButtons();
                }
            }
        });
        createButtons();
        rankMatrix.parse();
        sliderRange.setValue(0);
    }

    private void createButtons() {
        cardButtons = new CardButton[13][13];
        int i = 0, j = 0;

        for (Play p : Play.values()) {
            CardButton button = new CardButton(p, textField, this);
            this.panelButtons.add(button);
            this.cardButtons[i][j] = button;
            i++;
            if (i == 13) {
                i = 0;
                j++;
            }
        }
    }

    public void setIsClickActive(boolean isClickActive) {
        this.isClickActive = isClickActive;
    }

    public boolean isIsClickActive() {
        return isClickActive;
    }

    public void deactivateAllButtons() {
        for (int i = 0; i < cardButtons.length; i++) {
            for (int j = 0; j < cardButtons[0].length; j++) {
                cardButtons[i][j].deactivate();
            }
        }
    }

    private void showButtons() {
        deactivateAllButtons();
        setIsClickActive(false);
        activateButtons(textField.getText());
    }

    protected void changeRange() {
        this.labelRange.setText("Rango: " + this.sliderRange.getValue() + "%");
        this.textField.setText("");
        this.deactivateAllButtons();
        this.setIsClickActive(true);
        Play[] selectedPlays=this.rankMatrix.perc(this.sliderRange.getValue());
        
        for(Play p:selectedPlays){
            activateButton(p);
        }
    }

    private void activateButton(Play p) {
        for (int i = 0; i < cardButtons.length; i++) {
            for (int j = 0; j < cardButtons[0].length; j++) {
                if(cardButtons[i][j].getPlay()==p){
                cardButtons[i][j].click();
                return;
                }
            }
        }
    }

    /*
     Dado un string marca los botones representados en el.
     Si un boton está representado un numero par de veces, aparecera como NO MARCADO
     */
    private void activateButtons(String s) {

        String[] pl = s.split(",");
        String[] bt;
        int x = 0;
        int y = 0;

        for (int i = 0; i < pl.length; i++) {

            bt = pl[i].split("");
            int t = bt.length;
            if (bt.length == 3 && bt[1].equals(bt[2])) {

                activateEquals(bt);
            } else if (bt.length == 4 && bt[3].equals("+")) {

                activateEqualsPlus(bt);
            } else if (bt.length == 4 && (bt[3].equalsIgnoreCase("o") || bt[3].equalsIgnoreCase("s"))) {

                activateDif(bt);
            } else if (bt.length == 5 && bt[4].equals("+")) {

                activatePlus(bt);
            } else if (bt.length == 8) {

                activateInterval(bt);
            }

        }
    }

    private int activateEquals(String[] bt) {

        int i = index(bt[1]);
        this.cardButtons[i][i].setActive();

        return i;
    }

    private void activateEqualsPlus(String[] bt) {

        int x = activateEquals(bt);

        for (int i = x - 1; i >= 0; i--) {

            this.cardButtons[i][i].setActive();
        }
    }

    private int[] activateDif(String[] bt) {

        int[] pos = new int[2];

        int i = index(bt[1]);
        int j = index(bt[2]);

        if (bt[3].equalsIgnoreCase("o")) {

            this.cardButtons[i][j].setActive();
        } else if (bt[3].equalsIgnoreCase("s")) {

            this.cardButtons[j][i].setActive();
        }

        pos[0] = i;
        pos[1] = j;

        return pos;
    }

    private void activatePlus(String[] bt) {

        int[] pos = activateDif(bt);
        pos[1]--;

        if (bt[3].equalsIgnoreCase("o")) {

            while (pos[1] != pos[0]) {

                this.cardButtons[pos[0]][pos[1]].setActive();
                pos[1]--;
            }
        } else {

            while (pos[1] != pos[0]) {

                this.cardButtons[pos[1]][pos[0]].setActive();
                pos[1]--;
            }
        }

    }

    private void activateInterval(String[] bt) {

        int a2 = index(bt[2]);
        int b1 = index(bt[5]);
        int b2 = index(bt[6]);

        if (bt[3].equalsIgnoreCase("o")) {

            while (a2 <= b2) {

                this.cardButtons[b1][b2].setActive();
                b2--;
            }
        } else {

            while (a2 <= b2) {

                this.cardButtons[b2][b1].setActive();
                b2--;
            }
        }
    }

    private int index(String s) {

        int i;
        int x;

        switch (s) {
            case "A":
                i = 0;
                break;
            case "K":
                i = 1;
                break;
            case "Q":
                i = 2;
                break;
            case "J":
                i = 3;
                break;
            case "T":
                i = 4;
                break;
            default:
                x = Integer.parseInt(s);
                i = 14 - x;
                break;
        }

        return i;
    }
}
