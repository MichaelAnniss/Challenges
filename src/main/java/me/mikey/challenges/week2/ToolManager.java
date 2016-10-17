package me.mikey.challenges.week2;

import me.mikey.challenges.week2.tools.PlacementTool;
import me.mikey.challenges.week2.util.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 17/10/16.
 */
public class ToolManager {
    private static ToolManager instance = new ToolManager();

    private List<PlacementTool> allTools = new ArrayList<>();
    private int currentToolIndex = 0;

    public void toggleTool() {
        if(this.allTools.size() == 0)
            return;

        currentToolIndex++;

        if(currentToolIndex >= this.allTools.size()) {
            this.currentToolIndex = 0;
        }
    }

    //Draw the name of the current tool to the screen
    public void paint(Graphics g) {
        PlacementTool currentTool = getCurrentTool();

        if(currentTool != null) {
            g.setColor(Color.BLACK);
            g.drawString(currentTool.getName(), (Week2.getInstance().getWidth() / 2) - g.getFontMetrics()
                            .stringWidth(currentTool.getName()),
                    Week2.getInstance().getHeight() - 20);
        }
    }

    //Call the current tool at the given position
    public void activateTool(Position pos) {
        PlacementTool currentTool = getCurrentTool();

        if(currentTool != null) {
            currentTool.activate(pos);
        }
    }

    //Register a new tool in the ToolManager
    public void registerTool(PlacementTool tool) {
        this.allTools.add(tool);
    }

    public PlacementTool getCurrentTool() {
        return this.allTools.get(this.currentToolIndex);
    }

    public List<PlacementTool> getAllTools() {
        return allTools;
    }

    public static ToolManager getInstance() {
        return instance;
    }
}
