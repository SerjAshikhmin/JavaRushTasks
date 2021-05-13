package com.javarush.task.task32.task3209.listeners;

import com.javarush.task.task32.task3209.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

public class TextEditMenuListener implements MenuListener {
    private View view;
    public TextEditMenuListener(View view) {
        this.view = view;
    }

    public void menuDeselected(MenuEvent e) {

    }

    public void menuCanceled(MenuEvent e) {

    }

    public void menuSelected(MenuEvent menuEvent) {
        JMenu menuSelected = (JMenu) menuEvent.getSource();
        Component[] menuComponents = menuSelected.getMenuComponents();
        for (Component menuComponent : menuComponents) {
            menuComponent.setEnabled(view.isHtmlTabSelected());
        }
    }
}
