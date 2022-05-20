package ru.maltseva.elena.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Покемон. Поля должны заполняться из JSON, который возвратит внешний REST-service
 * Для маппинга значений из массива stats рекомендуется использовать отдельный класс Stat и аннотацию @JsonCreator
 */
public class Pokemon {

    /**
     * Уникальный идентификатор, маппится из поля pokemonId
     */
    @JsonProperty("id")
    private long pokemonId;

    /**
     * Имя покемона, маппится из поля pokemonName
     */
    @JsonProperty("name")
    private String pokemonName;

    /**
     * Здоровье покемона, маппится из массива объектов stats со значением name: "hp"
     */

    private short hp;

    /**
     * Атака покемона, маппится из массива объектов stats со значением name: "attack"
     */
    private short attack;

    /**
     * Защита покемона, маппится из массива объектов stats со значением name: "defense"
     */
    private short defense;

    public Pokemon() {
    }

    public Pokemon(long pokemonId, String pokemonName, short hp, short attack, short defense) {
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public long getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(long pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public short getHp() {
        return hp;
    }

    public void setHp(short hp) {
        this.hp = hp;
    }

    public short getAttack() {
        return attack;
    }

    public void setAttack(short attack) {
        this.attack = attack;
    }

    public short getDefense() {
        return defense;
    }

    public void setDefense(short defense) {
        this.defense = defense;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        if (pokemonId != pokemon.pokemonId) return false;
        if (hp != pokemon.hp) return false;
        if (attack != pokemon.attack) return false;
        if (defense != pokemon.defense) return false;
        return Objects.equals(pokemonName, pokemon.pokemonName);
    }

    @Override
    public int hashCode() {
        int result = (int) (pokemonId ^ (pokemonId >>> 32));
        result = 31 * result + (pokemonName != null ? pokemonName.hashCode() : 0);
        result = 31 * result + (int) hp;
        result = 31 * result + (int) attack;
        result = 31 * result + (int) defense;
        return result;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "pokemonId=" + pokemonId +
                ", pokemonName='" + pokemonName + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}
