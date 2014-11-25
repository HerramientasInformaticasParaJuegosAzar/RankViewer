package view;

import card.Play;
import java.awt.Color;
import javax.swing.JButton;

public class CardButton extends JButton{
    private Play p;
    

    public CardButton(){
	super();
    }

    CardButton(Play p) {
        super();
        this.p = p;
        if(p.toString().startsWith("_"))
            this.setText(p.toString().substring(1, p.toString().length()));
        else
            this.setText(p+"");
        if(p.toString().charAt(p.toString().length()-1) == 's')
            this.setBackground(Color.GREEN);
            else if (p.toString().charAt(p.toString().length()-1) == 'o')
                this.setBackground(Color.red);
                    else
                       this.setBackground(Color.cyan);
                
            
        
    }
}
