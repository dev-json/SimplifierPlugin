package de.jxson.simplifier.api.setting;

import java.util.List;

public interface SettingAPI {

    List<Setting> getSettings();
    void registerSetting(Setting setting);
    void unregisterSetting(Setting setting);

}
