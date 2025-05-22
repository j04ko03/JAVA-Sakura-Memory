package main.java.model;

import java.util.HashMap;
import java.util.Map;

public class SettingsManager {
    private final Map<String, Object> settings;

    public SettingsManager() {
        settings = new HashMap<>();
        loadDefaultSettings();
    }

    private void loadDefaultSettings() {
        settings.put("theme", "Classic");
        settings.put("sound", true);
        settings.put("difficulty", "Normal");
    }

    public void setSetting(String key, Object value) {
        settings.put(key, value);
    }

    public String getSetting(String key) {
        return settings.get(key).toString();
    }
}