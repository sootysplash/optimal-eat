package me.sootysplash;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.*;
import net.minecraft.network.chat.Component;


public class ModMenuOE implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigOE config = ConfigOE.getInstance();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Component.nullToEmpty("Config"))
                    .setSavingRunnable(config::save);

            ConfigCategory handle = builder.getOrCreateCategory(Component.nullToEmpty("Handling"));
            ConfigEntryBuilder cfghandle =  builder.entryBuilder();

            handle.addEntry(cfghandle.startBooleanToggle(Component.nullToEmpty("Enabled"), config.enabled)
                    .setDefaultValue(true)
                    .setTooltip(Component.nullToEmpty("Optimally eat?\nNOTE: With this mod enabled you cannot hold rmb to continually eat food!"))
                    .setSaveConsumer(newValue -> config.enabled = newValue)
                    .build());

            return builder.build();
        };
    }

}
