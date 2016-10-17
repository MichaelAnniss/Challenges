package me.mikey.challenges.week2.tools;

import me.mikey.challenges.week2.ComponentManager;
import me.mikey.challenges.week2.components.FollowMouseComponent;
import me.mikey.challenges.week2.util.Position;

/**
 * Created by Michael on 17/10/16.
 */
public class FollowMouseTool extends PlacementTool {
    @Override
    public void activate(Position pos) {
        ComponentManager.getInstance().registerComponent(new FollowMouseComponent(this.getRandomString(), pos,
                RANDOM.nextInt(3) + 1));
    }

    @Override
    public String getName() {
        return "Follow Mouse";
    }
}
