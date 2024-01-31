import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameGUI implements ActionListener {
    char newGameStartPlayer;
    JFrame end_frame=new JFrame();
    JLabel end_text = new JLabel();
    JPanel end_title = new JPanel();
    JButton endY=new JButton();
    JButton endN=new JButton();
    JPanel button_panel = new JPanel();

     EndGameGUI(char newTurn){
         newGameStartPlayer=newTurn;
         button_panel.setLayout(new GridLayout(0,2));
         endY.setText("Yes");
         endN.setText("No");
         endY.addActionListener(this);
         endN.addActionListener(this);
         endY.setPreferredSize(new Dimension(200, 100));
         endN.setPreferredSize(new Dimension(200, 100));
         button_panel.add(endY);
         button_panel.add(endN);

         end_text.setFont(new Font("MV Boli",Font.BOLD,30));
         end_text.setText("New Game? ");
         end_title.add(end_text);
         end_frame.add(end_title,BorderLayout.NORTH);
         end_frame.add(button_panel,BorderLayout.SOUTH);
         end_frame.setSize(400,200);
         end_frame.getContentPane().setBackground(new Color(50,50,50));
         end_frame.setVisible(true);
         end_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
     @Override
     public void actionPerformed(ActionEvent e) {
         if(e.getSource()==endN){
             end_frame.dispose();
         }
         if(e.getSource()==endY){
             end_frame.dispose();
             new GUI(newGameStartPlayer);
         }
     }
}

