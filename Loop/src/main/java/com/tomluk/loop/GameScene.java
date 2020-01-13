package com.tomluk.loop;

public interface GameScene {

    /**
     * @param time between updates, in ms
     */
    void update(double time);

    /**
     * @param time btween frames drawn, in ms
     */
    void draw(double time);
}
