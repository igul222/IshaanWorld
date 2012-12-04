/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ishaanworld;

/**
 *
 * @author ishaan
 */
public class Loc {
    public int x;
    public int y;
    
    public Loc(int x, int y) {
        this.x = x;
        this.y = y;
    }


    // returns the adjacent Loc in a given direction,
    // or null if the Loc exists outside of the grid.
    public Loc adjacentLocInDir(int dir) {
        // trigonometry really is fun!
        double ang = Math.toRadians(dir);
        int diff_x = (int)Math.round(Math.cos(ang));
        int diff_y = (int)Math.round(Math.sin(ang));

        Loc ret = new Loc(x-diff_x,y-diff_y);

        // check that the new Loc is valid before returning it!
        int maxCoord = IshaanWorld.getBoardSize()-1;
        if(ret.x > maxCoord || ret.y > maxCoord || ret.x < 0 || ret.y < 0) {
            // invalid: location out of bounds!
            return null;
        }
        return ret;
    }

    public boolean isOccupied() {
        for(Actor actor : Actor.troupe()) {
            if(actor.getLoc().equals(this) && 
               !actor.isDestroyed())
               return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if(o==null || !(o instanceof Loc))
            return false;
        Loc other = (Loc)o;
        return (other.x==x && other.y==y);
    }

    @Override
    public int hashCode() {
        return x*y;
    }

    public boolean isEmpty() {
        return !isOccupied();
    }

    @Override
    public String toString() {
        return "Loc("+x+","+y+")";
    }

}
