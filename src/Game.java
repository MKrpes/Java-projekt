public class Game {
    private char[][] game=new char[3][3];
    public final char p1 = 'X';
    public final char p2='O';
    private int counter=0;
    public char Playerturn;
    public int[] win={-1,-1,-1,-1};  //marks which row/column/diagonal(f/r) a player filled
    public Game(char startPlayer){
        Playerturn=startPlayer;
    }
    public int turn(int x, int y){
        game[x][y]=Playerturn;
        if(counter>=4){
             if(checkWinner(x,y,Playerturn)) return 1;
        }


        counter++;
        if(counter == 9) return 2;
        if(Playerturn== p1) {
            Playerturn = p2;
        }else {
            Playerturn= p1;
        }
        return 0;
    }
    private boolean checkRow(int x, char player){
        for(int i=0;i<3;i++){
            if(game[x][i]!=player) return false;
        }
        win[0]=x;
        return true;
    }
    private boolean checkColumn(int y, char player){
        for(int i=0;i<3;i++){
            if(game[i][y]!=player) return false;
        }
        win[1]=y;
        return true;
    }
    private boolean checkDiagonalFalling(char player){
        for(int i=0;i<3;i++){
            if(game[i][i]!=player) return false;
        }
        win[2]=3;
        return true;
    }
    private boolean checkDiagonalRising(char player){
        for(int i=0;i<3;i++){
            if(game[i][2-i]!=player) return false;
        }
        win[3]=4;
        return true;
    }
    public boolean checkWinner(int x, int y, char player){
        int koord=x+y;
        switch (koord){
            case 1:
            case 3:
                if(checkColumn(y,player) | checkRow(x, player)) {
                    return true;
                }
                break;

            case 0:
            case 4:
                if(checkColumn(y,player) | checkRow(x, player) | checkDiagonalFalling(player)) {
                    return true;
                }
                    break;
            case 2:
                if(x>1 || y>1) {
                    if (checkColumn(y, player) | checkRow(x, player) | checkDiagonalRising(player)) {
                        return true;
                    }
                }
                else {
                    if (checkColumn(y, player) | checkRow(x, player) | checkDiagonalRising(player) | checkDiagonalFalling(player)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}
