import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        if(random.nextInt(2)==0){
            new GUI('X');
        }else{
            new GUI('O');
        }
    }
}