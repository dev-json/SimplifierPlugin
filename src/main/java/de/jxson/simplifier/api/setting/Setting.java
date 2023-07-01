package de.jxson.simplifier.api.setting;

public interface Setting {

    String getName();
    String getDescription();

    boolean isEnabled();

    void onRegister();
    void onUnregister();
}
