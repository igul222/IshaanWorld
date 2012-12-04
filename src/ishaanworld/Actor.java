package ishaanworld;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
public abstract class Actor extends JPanel {
    private static ArrayList<Class> actorTypes = new ArrayList<Class>();
    private static ArrayList<Actor> troupe = new ArrayList<Actor>();
    private Color color;
    private int dir;
    private Loc loc;
    private boolean destroyed;
    // package visibility
    boolean hasMoved;

    static ArrayList<Class> getActorTypes() {
        return actorTypes;
    }

    public Actor(Loc aLoc) {
        this();
        if(!loc.isOccupied())
            loc = aLoc;
    }

    public Actor() {
        if(!actorTypes.contains(this.getClass()))
            actorTypes.add(this.getClass());
        
        destroyed = false;

        Random gen = new Random();
        int boardSize = IshaanWorld.getBoardSize();

        int i = 0;
        do {
            loc = new Loc(gen.nextInt(boardSize),gen.nextInt(boardSize));
            if(i>1000) {
                IshaanWorld.error("Too many actors on the board!");
            }
            i++;
        } while(loc.isOccupied());
        
        dir = 90;
        color = Color.red;
        troupe().add(this);

        // this runs a ton of layout code, so make sure loc/dir are set up!
        Grid.get().insertActor(this);
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public static void purgeDestroyedActors() {
        for(int i=0;i<troupe().size();i++) {
            Actor actor = troupe().get(i);
            if(actor.isDestroyed()) {
                Grid.get().remove(actor);
                troupe().remove(i);
            }
        }
    }

    public static ArrayList<Actor> troupe() {
        return troupe;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    @Override
    public void paintComponent(Graphics g) {
        // anti-aliasing
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle box = getBounds();
        int pad=(int)Math.round(box.width*(IshaanWorld.getCellPadding()/100.0));
        box.setLocation(pad, pad);
        box.setSize(box.width-(2*pad), box.height-(2*pad));
        styleBox(g2,box);
    }

    // default appearance
    public void styleBox(Graphics2D g, Rectangle box) {
        GradientPaint gp = new GradientPaint(
                0,
                0,
                getColor().brighter().brighter(),
                0,
                getHeight(),
                getColor().darker(),
                true);
        g.setPaint(gp);
        g.fillOval(box.x,box.y,box.width,box.height);
    }

    public Loc getLoc() {
        return loc;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public boolean canMove() {
        Loc nextMove = getLoc().adjacentLocInDir(dir);
        return (nextMove!=null && nextMove.isEmpty());
    }

    public void moveForward() {
        if(hasMoved)
            IshaanWorld.error("Can't move more than once per turn!");
        else {
            hasMoved = true;
            if(canMove())
                loc = getLoc().adjacentLocInDir(dir);
        }
    }

    public void turnClockwise() {
        dir += 45;
    }

    public void destroy() {
        /* most of the time, this is called from within act(), which
         is called from within ActingTask, which loops through troupe().
         This means that we *cannot modify* troupe() here, and must
         employ crude workarounds instead. got it? */
        destroyed = true;
    }

    public abstract void act();
}
