package com.tomluk.loop;

/**
 * Instances of this class should not be reused.
 */
public class BasicGameLoop extends Thread {

    public static final int TARGET_FPS_30 = 30;
    public static final int TARGET_FPS_60 = 60;

    private final GameScene gameScene;

    private volatile boolean isRunning = false;

    private double frameTime;
    private double deltaTime;

    public BasicGameLoop(GameScene gameScene) {
        this.gameScene = gameScene;
        setTargetFps(TARGET_FPS_30);
    }

    @Override
    public void run() {
        while (isRunning) {
            double startTime = getTimeInMilliseconds();
            gameScene.draw(deltaTime);
            gameScene.update(deltaTime);
            double workTime = getTimeInMilliseconds() - startTime;
            sleepForGivenTime(calculateTimeToSleep(workTime));
            deltaTime = getTimeInMilliseconds() - startTime;
        }
    }

    private double getTimeInMilliseconds() {
        return (double) System.nanoTime() / 1000_000;
    }

    private void sleepForGivenTime(double timeToSleep) {
        try {
            sleep((long) timeToSleep);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private double calculateTimeToSleep(double workTime) {
        double timeToSleep = frameTime - workTime;
        timeToSleep = timeToSleep > 0 ? timeToSleep : 0;
        return timeToSleep;
    }

    public void startRunning() {
        isRunning = true;
        start();
    }

    public void stopRunning() {
        isRunning = false;
        waitToFinish();
    }

    private void waitToFinish() {
        boolean retry = true;
        while (retry) {
            try {
                join();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void setTargetFps(int targetFps) {
        frameTime = 1000d / targetFps;
    }
}
