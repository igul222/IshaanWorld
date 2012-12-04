package client;
import ishaanworld.*;
public class Main {

    public static void main(String[] args) {        

        IshaanWorld.setBoardSize(20);
        IshaanWorld.setAnimationDelay(300);
        
        for(int i=0;i<3;i++)
            new Bug();

    }

}