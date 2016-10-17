package me.mikey.challenges.week2.listeners;

import me.mikey.challenges.week2.ToolManager;
import me.mikey.challenges.week2.util.Position;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Michael on 16/10/16.
 */
public class Week2MouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Left click to activate tool
        if(e.getButton() == MouseEvent.BUTTON1) {
            ToolManager.getInstance().activateTool(Position.from(e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
