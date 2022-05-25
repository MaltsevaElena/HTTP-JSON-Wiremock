package ru.maltseva.elena.service;

import ru.maltseva.elena.entity.Pokemon;
import ru.maltseva.elena.view.ShowImage;


public class PokemonFightingClubServiceImpl implements PokemonFightingClubService {
    private ShowImage showImage;
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
        System.out.println("Pokemon "+winner.getPokemonName()+" won.");
        showImage = new ShowImage();
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        to.setHp((short) (to.getHp() - from.getAttack()));
    }
}
