package me.mikey.challenges.week2;

import me.mikey.challenges.week2.tools.PlacementTool;
import me.mikey.challenges.week2.util.AABB;
import me.mikey.challenges.week2.util.Position;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Michael on 17/10/16.
 */
public class Week2Test {
    @Test
    public void testComponentRegister() {
        assertEquals(ComponentManager.getInstance().getComponents().size(), 0);

        ComponentManager.getInstance().registerComponent(new GUIComponent(Position.from(0, 0)) {
            @Override
            public void tick() {}

            @Override
            public void redraw(Graphics g) {}

            @Override
            public void updateBoundingBox() {}
        });

        assertEquals(ComponentManager.getInstance().getComponents().size(), 1);
        ComponentManager.getInstance().getComponents().clear();
    }

    @Test
    public void testAABB() {
        AABB aa = new AABB(Position.from(0, 0), Position.from(10, 10));

        assertTrue(aa.inside(Position.from(1, 1)));
        assertTrue(aa.inside(Position.from(1, 2)));
        assertTrue(aa.inside(Position.from(2, 3)));
        assertTrue(aa.inside(Position.from(4, 5)));
        assertFalse(aa.inside(Position.from(11, 11)));

        AABB bb = new AABB(Position.from(1, 1), Position.from(2, 2));
        assertTrue(aa.intersectsWith(bb));
    }

    @Test
    public void testPositionAssignment() {
        //Test basic assignment
        Position pos = Position.from(0, 1);
        assertEquals(pos.getX(), 0);
        assertEquals(pos.getY(), 1);
    }

    @Test
    public void testPositionDistance() {
        Position pos = Position.from(0, 1);
        Position to = Position.from(1, 1);

        assertEquals(pos.distance(to), 1);

        to = Position.from(2, 5);
        assertEquals(pos.distance(to), (int) Math.sqrt(20));
    }

    @Test
    public void testPositionBounds() {
        Position pos = Position.from(200, 200);
        pos.bounds(10, 10, Position.from(0, 0));

        assertEquals(pos.getX(), 0);
        assertEquals(pos.getY(), 0);

        Position to = Position.from(2, 2);
        int initialDistance = pos.distance(to);
        pos.tickTowards(to, 1);

        assertTrue(pos.distance(to) < initialDistance);
    }

    @Test
    public void testTickTool() {
        ToolManager.getInstance().registerTool(new PlacementTool() {
            @Override
            public void activate(Position pos) {}

            @Override
            public String getName() {
                return "test1";
            }
        });

        ToolManager.getInstance().registerTool(new PlacementTool() {
            @Override
            public void activate(Position pos) {}

            @Override
            public String getName() {
                return "test2";
            }
        });

        assertTrue(ToolManager.getInstance().getCurrentTool().getName().equals("test1"));
        ToolManager.getInstance().toggleTool();
        assertTrue(ToolManager.getInstance().getCurrentTool().getName().equals("test2"));
        ToolManager.getInstance().toggleTool();
        assertTrue(ToolManager.getInstance().getCurrentTool().getName().equals("test1"));

        //Cleanup
        ToolManager.getInstance().getAllTools().clear();
    }
}
