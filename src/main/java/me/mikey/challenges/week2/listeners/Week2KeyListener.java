package me.mikey.challenges.week2.listeners;

import me.mikey.challenges.week2.ComponentManager;
import me.mikey.challenges.week2.GUIComponent;
import me.mikey.challenges.week2.ToolManager;
import me.mikey.challenges.week2.Week2;
import me.mikey.challenges.week2.util.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 17/10/16.
 */
public class Week2KeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Toggle bounding boxes
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Week2.getInstance().setDrawBoundingBoxes(!Week2.getInstance().isDrawBoundingBoxes());
        }

        //Switch between tools
        if(e.getKeyCode() == KeyEvent.VK_T) {
            ToolManager.getInstance().toggleTool();
        }

        //Toggle collision for the GUIComponent
        if(e.getKeyCode() == KeyEvent.VK_B) {
            Position pos = Week2.getInstance().getAdaptedMousePosition();

            for(GUIComponent component : ComponentManager.getInstance().getComponents()) {
                if(component.getBoundingBox().inside(pos)) {
                    component.setCollisionEnabled(!component.isCollisionEnabled());
                }
            }
        }

        //Delete the component(s) at the current mouse position
        if(e.getKeyCode() == KeyEvent.VK_D) {

            //List of components to remove
            List<GUIComponent> toRemove = new ArrayList<>();

            Position pos = Week2.getInstance().getAdaptedMousePosition();

            //Loop through bounding boxes and check that the position is in the components bounding box
            for (GUIComponent component : ComponentManager.getInstance().getComponents()) {
                if (component.getBoundingBox().inside(pos)) {
                    toRemove.add(component);
                }
            }

            //Remove all of the components in the list
            ComponentManager.getInstance().getComponents().removeAll(toRemove);
        }
    }
}
