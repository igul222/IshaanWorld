package ishaanworld;

import java.util.TimerTask;


class ActingTask extends TimerTask {
    @Override
    public void run() {

        /* we can't use simple enumeration, a.k.a. "enhanced for" here
         because it implements thread safety checks which become a serious
         issue when doing things like adding actors in the middle of a
         loop. */
        for(int i=0;i<Actor.troupe().size();i++) {
            Actor actor = Actor.troupe().get(i);
            if(!actor.isDestroyed())
                actor.act();
        }

        Actor.purgeDestroyedActors();
        Grid.get().updateActorLocations();
        // fixes the annoying "trails" bug
        MainFrame.get().repaint();

        // nothing prevents us from using simple enumeration here! :)
        for(Actor actor : Actor.troupe()) {
            actor.hasMoved = false;
        }

    }

}