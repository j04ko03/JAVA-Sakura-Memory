package main.java.model;

public class ScoringSystem {
    private int currentScore;

    public ScoringSystem() {
        currentScore = 0;
    }

    public void calculateScore(int basePoints) {
        currentScore += basePoints;
    }

    public void applyBonus(int bonus) {
        currentScore += bonus;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void addScore(int challengeBonus) {
    }
}