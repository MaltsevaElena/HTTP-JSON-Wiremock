package ru.maltseva.elena.view;

import javax.swing.*;

public class ShowImage extends JFrame {

    public ShowImage(){
        setTitle("Pokemon winner");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300,300);
        add(new GameField());
        setVisible(true);
    }

}
