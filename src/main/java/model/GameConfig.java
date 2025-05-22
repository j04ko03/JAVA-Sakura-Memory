package main.java.model;

public class GameConfig {
    private String gameMode;
    private SettingsManager settings;

    // Constructor privado para usar Builder
    private GameConfig(String gameMode, SettingsManager settings) {
        this.gameMode = gameMode;
        this.settings = settings;
    }

    public static class Builder {
        private String gameMode = "Classic";
        private SettingsManager settings = new SettingsManager();

        public Builder withGameMode(String gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public Builder withSetting(String key, Object value) {
            this.settings.setSetting(key, value);
            return this;
        }

        public GameConfig build() {
            return new GameConfig(this.gameMode, this.settings);
        }
    }

    // Getters
    public String getGameMode() { return gameMode; }
    public SettingsManager getSettings() { return settings; }
}