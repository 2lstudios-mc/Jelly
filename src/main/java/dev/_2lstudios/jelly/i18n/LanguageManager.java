package dev._2lstudios.jelly.i18n;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.InvalidConfigurationException;

import dev._2lstudios.jelly.config.Configuration;
import dev._2lstudios.jelly.utils.FileUtils;

public class LanguageManager {
    private Map<String, Configuration> languages;
    private final File directory;

    public LanguageManager(final File directory) {
        this.languages = new HashMap<>();
        this.directory = directory;

        LanguageExtractor.extractAll(directory);
    }

    public void loadLanguage(final File file) throws IOException, InvalidConfigurationException {
        final Configuration lang = new Configuration(file);
        lang.load();

        final String name = FileUtils.getBaseName(file).toLowerCase();
        this.languages.put(name, lang);
    }

    public Configuration getLanguage(final String name) {
        if (languages.containsKey(name)) {
            return languages.get(name);
        } else if (languages.containsKey(name)) {
            return languages.get(name);
        } else if (languages.containsKey(name.split("[-]")[0])) {
            return languages.get(name.split("[-]")[0]);
        } else {
            return languages.get(this.getDefaultLocale());
        }
    }

    public void loadLanguages() throws IOException, InvalidConfigurationException {
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (final File file : directory.listFiles()) {
            if (file.getName().endsWith(".yml")) {
                try {
                    this.loadLanguage(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDefaultLocale() {
        return "en";
    }
}