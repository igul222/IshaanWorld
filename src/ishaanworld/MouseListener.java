package ishaanworld;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
class PopUpDemo extends JPopupMenu {
    ArrayList<JMenuItem> menuItems;
    public PopUpDemo(){
        menuItems = new ArrayList<JMenuItem>();
        for(Class actorType : Actor.getActorTypes()) {
            JMenuItem item = new JMenuItem("new "+actorType.getSimpleName()+"()");

            item.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {

                    Actor actor = (Actor)actorType.newInstance();
                    
                }
            });

            menuItems.add(item);
            add(item);
        }
    }
}

class MouseListener extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }
    @Override
    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        PopUpDemo menu = new PopUpDemo();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}
