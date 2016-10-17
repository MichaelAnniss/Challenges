package me.mikey.challenges.week2.tools;

import me.mikey.challenges.week2.ComponentManager;
import me.mikey.challenges.week2.components.ScrollingComponent;
import me.mikey.challenges.week2.util.Position;

/**
 * Created by Michael on 17/10/16.
 */
public class ScrollingTextTool extends PlacementTool {
    @Override
    public void activate(Position pos) {
        ComponentManager.getInstance().registerComponent(new ScrollingComponent(this.getRandomString(), pos,
                RANDOM.nextInt(3) + 1,

                //Pick a random direction
                ScrollingComponent.TextDirection.values()[RANDOM.nextInt(ScrollingComponent.TextDirection.values().length)]));
    }

    @Override
    public String getName() {
        return "Scrolling Text";
    }
}
