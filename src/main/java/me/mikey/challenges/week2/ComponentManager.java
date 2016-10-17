package me.mikey.challenges.week2;

import me.mikey.challenges.week2.components.FollowMouseComponent;
import me.mikey.challenges.week2.components.ScrollingComponent;
import me.mikey.challenges.week2.util.Position;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Michael on 15/10/16.
 */
public class ComponentManager {
    private static ComponentManager instance = new ComponentManager();

    private List<GUIComponent> components = new CopyOnWriteArrayList<>();

    //Register some initial components
    public void init() {
        //Horizontal
        registerComponent(new ScrollingComponent("b", Position.from(0, 40), 2,
                ScrollingComponent.TextDirection.LEFT_TO_RIGHT));

        registerComponent(new ScrollingComponent("a", Position.from(CompatLayer.getInstance().getWidth(), 60), 4,
                ScrollingComponent.TextDirection.RIGHT_TO_LEFT));

        //Vertical
        registerComponent(new ScrollingComponent("c", Position.from(CompatLayer.getInstance().getWidth() / 2, 0), 4,
                ScrollingComponent.TextDirection.TOP_TO_BOTTOM));

        registerComponent(new ScrollingComponent("d", Position.from((CompatLayer.getInstance().getWidth() / 2) + 20, CompatLayer.getInstance().getHeight()), 3,
                ScrollingComponent.TextDirection.BOTTOM_TO_TOP));

        //Dynamic
        registerComponent(new FollowMouseComponent("e", Position.from(0, 0), 1));
    }

    public void registerComponent(GUIComponent component) {
        this.components.add(component);
    }

    public void paint(Graphics g) {
        for(GUIComponent component : components) {
            component.tick();
            g.setColor(component.getColor());
            component.redraw(g);
            g.setColor(Color.BLACK);

            //Draw the bounding box if it is enabled
            if(CompatLayer.getInstance().isDrawBoundingBoxes() && component.getBoundingBox() != null) {

                if(component.isCollisionEnabled())
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.RED);

                g.drawRect(component.getBoundingBox().getMin().getX(),
                        component.getBoundingBox().getMin().getY(), component.getBoundingBox().getWidth(), component.getBoundingBox().getHeight());
                g.setColor(Color.BLACK);
            }
        }
    }

    public List<GUIComponent> getComponents() {
        return components;
    }

    public static ComponentManager getInstance() {
        return instance;
    }
}
