package com.github.irvinglink.ThroneEssentials.Storage;

import com.github.irvinglink.ThroneEssentials.MClass;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class FileSystem {

    private final MClass plugin;

    private FileConfiguration config;
    private FileConfiguration lang;

    private File configFile;
    private File langFile;
    private File mainFolder;

    public FileSystem(MClass plugin) {
        this.plugin = plugin;
        this.mainFolder = plugin.getDataFolder();
    }

    public void setup() {

        this.configFile = new File(mainFolder, "config.yml");
        this.langFile = new File(mainFolder, "lang.yml");

        if (!mainFolder.exists()) {
            mainFolder.mkdirs();
        }

        if (!configFile.exists()) {
            try {
                Files.copy(Objects.requireNonNull(plugin.getResource(configFile.getName())), this.configFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!langFile.exists()) {
            try {
                Files.copy(Objects.requireNonNull(plugin.getResource(langFile.getName())), this.langFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void reloadConfig() {

        if (this.config != null)
            this.config = YamlConfiguration.loadConfiguration(this.configFile);

        if (this.lang != null)
            this.lang = YamlConfiguration.loadConfiguration(this.langFile);
    }

    public void saveConfig() {
        if (this.config != null)
            try {
                this.config.save(this.configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (this.lang != null)
            try {
                this.lang.save(this.langFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public FileConfiguration getConfig() {

        if (this.config != null)
            return this.config;

        this.config = new YamlConfiguration();

        try {
            this.config.load(this.configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return this.config;
    }

    public FileConfiguration getLang() {
        if (this.lang != null)
            return this.lang;
        this.lang = new YamlConfiguration();
        try {
            this.lang.load(this.langFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return this.lang;
    }

}
