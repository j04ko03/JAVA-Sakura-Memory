package main.java.model;

public class GameMechanics {
    private static final int BASE_SCORE = 10;
    private static final int CHALLENGE_BONUS = 50;

    private final String gameMode;
    private final ScoringSystem scoringSystem;

    public GameMechanics(GameConfig config) {
        this.gameMode = config.getGameMode();
        this.scoringSystem = new ScoringSystem();
    }

    public void processMatch(int cardId) {
        scoringSystem.addScore(BASE_SCORE);
        checkForCompletedChallenges();
    }

    private void checkForCompletedChallenges() {
        if (scoringSystem.getCurrentScore() % 100 == 0) {
            scoringSystem.addScore(CHALLENGE_BONUS);
        }
    }

    public String getGameMode() {
        return gameMode;
    }

    public ScoringSystem getScoringSystem() {
        return scoringSystem;
    }


    public void processMove(int cardId) {
    }
}