package view;

import card.Play;
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
            this.setText(p.toString().substring(1, 3));
        else
            this.setText(p+"");
    }
}
