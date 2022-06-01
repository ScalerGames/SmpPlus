package Dev.ScalerGames.SmpPlus.Files;

import Dev.ScalerGames.SmpPlus.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Data {

    private static FileConfiguration dataConfig = null;
    private static File dataFile = null;

    public static void reloadData() {
        if (dataFile == null)
            dataFile = new File(Main.getInstance().getDataFolder(), "data.yml");

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        InputStream defaultStream = Main.getInstance().getResource("data.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            dataConfig.setDefaults(defaultConfig);
        }
    }

    public static FileConfiguration getDataConfig() {
        if (dataConfig == null)
            reloadData();

        return dataConfig;
    }

    public static void saveData() {
        if (dataConfig == null || dataFile == null)
            return;

        try {
            getDataConfig().save(dataFile);
        } catch (IOException ex) {
            Main.getInstance().getLogger().warning("Could not save config to " + dataFile);
        }
    }

    public static void saveDefaultData() {
        if (dataFile == null)
            dataFile = new File(Main.getInstance().getDataFolder(), "data.yml");

        if (!dataFile.exists()) {
            Main.getInstance().saveResource("data.yml", false);
        }
    }

}
