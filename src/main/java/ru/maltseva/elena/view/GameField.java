package ru.maltseva.elena.view;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private ImageIcon winnerIcon;
    private Image winner;
    private final String path = "C:\\ForJava\\HTTP-JSON-Wiremock\\src\\main\\resources\\winner.jpg";

    public GameField(){
        setBackground(Color.black);
        winnerIcon = new ImageIcon(path);
        winner = winnerIcon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(winner,90,90,this);

    }

}
