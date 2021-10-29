package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.*;
import com.javarush.task.task34.task3410.model.Box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;
    
    public Field(View view){
        this.view = view;
        addKeyListener(new KeyHandler());
        setFocusable(true);
    }
    public void paint(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 1000);
        try{
        for (GameObject go: view.getGameObjects().getAll()) {
            if(go == null) continue;
            go.draw(g);
             }
        }catch (NullPointerException e){

        }
    }

    public class KeyHandler extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == (KeyEvent.VK_LEFT)) eventListener.move(Direction.LEFT);
            if(e.getKeyCode() ==(KeyEvent.VK_RIGHT)) eventListener.move(Direction.RIGHT);
            if(e.getKeyCode() ==(KeyEvent.VK_UP)) eventListener.move(Direction.UP);
            if(e.getKeyCode() ==(KeyEvent.VK_DOWN)) eventListener.move(Direction.DOWN);
            if(e.getKeyCode() ==(KeyEvent.VK_R)) eventListener.restart();
        }
    }

    public void setEventListener(EventListener eventListener){
        this.eventListener = eventListener;
    }
}