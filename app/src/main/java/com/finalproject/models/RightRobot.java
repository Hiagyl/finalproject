package com.finalproject.models;

import java.util.ArrayList;
import java.util.Random;

import com.finalproject.core.GameState;

public class RightRobot extends Robot implements Movable {
    private boolean running;

    public RightRobot(ArrayList<String> path, int level) {
        super(path, level);
        this.running = true;
        // startThread();
    }

    @Override
    public void transferRoom() {
        Random random = new Random();
        int randomNumber = random.nextInt(20) + 1; // Generate a random number between 1 and 20

        if (randomNumber < this.getLevel()) {
            moveToNextPath();
        }
    }

    @Override
    public void moveToNextPath() {
        if (this.getCurrentPath() == 0) {
            this.setCurrentPath(1); // Move to path(1)
        } else if (this.getCurrentPath() == 1) {
            this.setCurrentPath(2); // Move to path(2)
            attack(); // Call attack method
        }
        System.out.println("Right robot moved to path " + this.getCurrentPath());
    }

    @Override
    public void attack() {
        try {
            // Pause for 6 seconds
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the right door is open
        if (GameState.isRightDoorOpen()) {
            // Set the game state to game over
            GameState.setGameOver(true);
            System.out.println("Game Over: Right door was open during attack.");
        }
    }

    @Override
    public void startThread() {
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(8000); // Wait for 8 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                transferRoom();
            }
        }).start();
    }

    public void stop() {
        running = false;
    }
}