package ishaanworld;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;

class MainFrame extends JFrame {
    private static MainFrame instance = null;

    private MainFrame() {
        super("IshaanWorld");

        // initialize grid
        add(Grid.get());

        // window dimensions
        int len = IshaanWorld.getBoardSize()*IshaanWorld.getSquareSize();
        setAdjustedSize(len);

        // misc. properties
        setLocationRelativeTo(null); // center on screen
        setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // right-click
        this.addMouseListener(new MouseListener());

        // resizing behavior
        this.addComponentListener(new ComponentListener() {
            public void componentHidden(ComponentEvent e) {}
            public void componentShown(ComponentEvent e) {}
            public void componentMoved(ComponentEvent e) {}
            public void componentResized(ComponentEvent e) {
                int lesser = getWidth()<getHeight() ? getWidth() : getHeight();
                setAdjustedSize(lesser);
            }
        });
    }

    // returns the shared instance
    public static MainFrame get() {
        if(instance==null)
            instance = new MainFrame();
        return instance;
    }

    private void setAdjustedSize(int len) {
        Grid.get().setPreferredSize(new Dimension(len,len));
        pack();
    }
}
