package view;

import card.Play;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CardButton extends JButton {

    private Play p;
    private boolean isSelected;
    private Color color;
    private JTextField textfield;
    private MainWindow mw;

    public CardButton() {
        super();

    }

    CardButton(Play p, JTextField textfield,MainWindow mw) {
        super();
        isSelected = false;
        this.textfield = textfield;
        this.mw=mw;
        this.p = p;
        if (p.toString().startsWith("_")) {
            this.setText(p.toString().substring(1, p.toString().length()));
        } else {
            this.setText(p + "");
        }
        if (p.toString().charAt(p.toString().length() - 1) == 's') {
            this.color=new Color(255, 199, 153);
            this.setBackground(color);
        } else if (p.toString().charAt(p.toString().length() - 1) == 'o') {
            this.color=new Color(255, 255, 153);
            this.setBackground(color);
        } else {
            this.color=new Color(153, 255, 255);
            this.setBackground(color);
        }

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                click();
            }
        });

    }

    public Play getPlay() {
        return p;
    }

    
    void click() {
        if(!mw.isClickActive){
            mw.deactivateAllButtons();
            textfield.setText("");
            mw.setIsClickActive(true);
        }
        String carta = "";
        if (p.toString().startsWith("_")) {
            carta = p.toString().substring(1, p.toString().length());
        } else {
            carta = p + "";
        }

        if (this.isSelected) {
            this.isSelected = false;
            this.setBackground(this.color);
            String text = textfield.getText();
            String[] split = text.split(carta);
            text = split[0]+split[1];
            text=text.replace(",,", ",");
            if(text.startsWith(","))
                text=text.substring(1,text.length());
            this.textfield.setText(text);
            
        }
        else{
            this.isSelected = true;
            this.setBackground(new Color(221, 158, 255));
            String text = textfield.getText();
            String[] split = text.split(carta);
            text += carta+",";
            this.textfield.setText(text);
            
        }

    }

    void deactivate(){
        this.isSelected = false;
        this.setBackground(this.color);
    }
    
    void setActive() {
        if (this.isSelected) {
            this.isSelected = false;
            this.setBackground(this.color);
            
        }
        else{
            this.isSelected = true;
            this.setBackground(new Color(221, 158, 255));            
        }
    }
}
