import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI implements ActionListener {
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel text_field = new JLabel();
    JButton[][] buttons = new JButton[3][3];
    Game game;
    private void endGame(){
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j].setEnabled(false);
            }
        }
        if(game.Playerturn==game.p1 ) {
            game.Playerturn = game.p2;
        }else {
            game.Playerturn= game.p1;
        }
        new EndGameGUI(game.Playerturn);
    }
    public GUI(char startPlayer){
        game=new Game(startPlayer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        text_field.setBackground(new Color(0,0,0));
        text_field.setForeground(new Color(0,255,0));
        text_field.setFont(new Font("MV Boli",Font.BOLD,75));
        text_field.setHorizontalAlignment(JLabel.CENTER);
        text_field.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,600,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150,150,150));

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                buttons[i][j] = new GameButton(i,j);
                button_panel.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("MV Boli", Font.BOLD, 120));
                buttons[i][j].setFocusable(false);
                buttons[i][j].addActionListener(this);
            }
        }
        title_panel.add(text_field);
        text_field.setText(game.Playerturn + " turn");
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameButton but=(GameButton) e.getSource();
        if (Objects.equals(but.getText(), "")) {
            but.setText(String.valueOf(game.Playerturn));
            if(game.Playerturn=='O'){
                but.setForeground(new Color(255, 0, 0));
            } else if (game.Playerturn=='X') {
                but.setForeground(new Color(0,0,255));
            }
            switch(game.turn(but.x, but.y)){
                case 0:
                    text_field.setText(game.Playerturn + " turn");
                    break;
                case 1:
                    for(int x=0;x<4;x++){
                        switch(game.win[x]){
                            case 0: case 1: case 2:
                                text_field.setText(game.Playerturn + " wins");
                                if(x==0){
                                    for(int y=0;y<3;y++){
                                        buttons[game.win[x]][y].setBackground(Color.GREEN);
                                    }
                                } else if (x==1) {
                                    for(int y=0;y<3;y++){
                                        buttons[y][game.win[x]].setBackground(Color.GREEN);
                                    }
                                }
                                endGame();
                                break;

                            case 3:
                                text_field.setText(game.Playerturn + " wins");
                                for(int y=0;y<3;y++){
                                    buttons[y][y].setBackground(Color.GREEN);
                                }
                                endGame();
                                break;

                            case 4:
                                text_field.setText(game.Playerturn + " wins");
                                for(int y=0;y<3;y++){
                                    buttons[y][2-y].setBackground(Color.GREEN);
                                }
                                endGame();
                        }
                    }
                    break;
                case 2:
                    text_field.setText("DRAW");
                    endGame();
                    break;
            }
        }
    }
}
class GameButton extends JButton{
    public int x;
    public int y;
    public GameButton(int x,int y) {
        this.x = x;
        this.y = y;
    }
}