import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetrePromo extends JFrame implements ActionListener{

  public FenetrePromo(){
		
    this.setTitle("Box Layout");
    this.setSize(350, 120);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    JPanel b1 = new JPanel();
    //On définit le layout en lui indiquant qu'il travaillera en ligne
    b1.setLayout(new GridLayout());
    b1.add(new JButton("Dame"));
    b1.add(new JButton("Fou"));
    b1.add(new JButton("Cavalier"));
    b1.add(new JButton("Tour"));
    this.getContentPane().add(b1);
    this.setVisible(true);
  }
  public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

public static void main(String[] args){
	new FenetrePromo();
}

}

