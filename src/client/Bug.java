package client;

import ishaanworld.*;

public class Bug extends Actor {

    @Override
    public void act() {
        int limit = 0;
        while(!canMove() && limit<8) {
            turnClockwise();
            limit++;
        }
        if(canMove()) {
            Loc oldLoc = getLoc();
            moveForward();
        }
    }
}