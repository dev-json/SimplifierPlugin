package de.jxson.simplifier.plugin.setting;

import de.jxson.simplifier.api.setting.Setting;

public abstract class SettingImpl implements Setting {

    private String name;
    private String description;

    private boolean enabled;

    public SettingImpl(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.enabled = false;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
