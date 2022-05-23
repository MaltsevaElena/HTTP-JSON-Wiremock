package ru.maltseva.elena.service;

import ru.maltseva.elena.entity.Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {
    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {

        while (p2.getHp() >= 1 && p1.getHp()>=1){
            doDamage(p1, p2);
            if (p2.getHp() > 0) {
                doDamage(p2, p1);
            }
        }

        if (p1.getHp() <= 0) {
            return p2;
        } else {
            return p1;
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
       /* Image img = ImageIO.read(new File("winner.jpg").getImage();
        img.drawImage(img, 0, 0, null);//выводим линию

        img.drawLine(20, 20, 200, 200);//выводим изображение*/

    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        to.setHp((short) (to.getHp() - from.getAttack()));
    }
}
