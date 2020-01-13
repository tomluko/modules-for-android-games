package com.tomluk.entities;

public interface Component<GAME_OBJECT> {

    void onAdd(Entity<GAME_OBJECT> entity);

    void onRemove(Entity<GAME_OBJECT> entity);

    /**
     * @param time time in ms
     */
    void update(Entity<GAME_OBJECT> entity, double time);
}
