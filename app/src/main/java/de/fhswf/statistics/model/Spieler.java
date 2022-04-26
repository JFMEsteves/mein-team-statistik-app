package de.fhswf.statistics.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spieler {

    private int id;

    private String name;

    @Nullable
    private List<SpielSpieler> stats;

    public Spieler(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Spieler() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public List<SpielSpieler> getStats() {
        return stats;
    }

    public void setStats(@Nullable List<SpielSpieler> stats) {
        this.stats = stats;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Spieler addstats(@NonNull SpielSpieler stat) {
        if (this.stats == null)
            this.stats = new ArrayList<>();

        if (!stats.contains(stat))
            stats.add(stat);

        return this;
    }
}
