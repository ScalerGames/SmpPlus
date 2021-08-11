package Dev.ScalerGames.SmpPlus;

import Dev.ScalerGames.SmpPlus.Commands.*;
import Dev.ScalerGames.SmpPlus.Commands.Item.LoreCMD;
import Dev.ScalerGames.SmpPlus.Commands.Item.LoreTAB;
import Dev.ScalerGames.SmpPlus.Commands.Item.NameCMD;
import Dev.ScalerGames.SmpPlus.Files.Config;
import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Gui.GuiCreator;
import Dev.ScalerGames.SmpPlus.Gui.GuiListener;
import Dev.ScalerGames.SmpPlus.Listeners.*;
import Dev.ScalerGames.SmpPlus.Utils.Format;
import Dev.ScalerGames.SmpPlus.Utils.UpdateChecker;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public static Economy econ = null;

    public Data data;
    public Gui gui;

    @Override
    public void onEnable() {

        plugin = this;
        enableCommands();
        enableFiles();
        enableListeners();
        enableAddons();
        ConfigurationSection section = Gui.getGuiConfig().getConfigurationSection("Menus");
        for (String menu : section.getKeys(false)) {
            String menuname = menu.substring(menu.lastIndexOf(".") + 1);
            GuiCreator.store(menuname, ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', Gui.getGuiConfig().getString("Menus." + menuname + ".name"))));
        }
        getLogger().info(GuiCreator.storage.values().toString() + GuiCreator.storage.keySet().toString());
        new UpdateChecker(this,89809).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                getLogger().info(Format.color("&2There is a new update available"));
            } else {
                getLogger().info(Format.color("&2You are running the latest version!"));
            }
        });

    }

    @Override
    public void onDisable() {



    }

    public void enableCommands() {
        getCommand("itemframe").setExecutor((CommandExecutor)new FrameCMD());
        getCommand("smp").setExecutor((CommandExecutor)new SmpCMD());
        getCommand("nickname").setExecutor((CommandExecutor)new NickCMD());
        getCommand("itemname").setExecutor((CommandExecutor)new NameCMD());
        getCommand("itemlore").setExecutor((CommandExecutor)new LoreCMD());
        getCommand("itemlore").setTabCompleter((TabCompleter)new LoreTAB());
        getCommand("smp").setTabCompleter((TabCompleter)new SmpTAB());
        getCommand("rules").setExecutor((CommandExecutor)new RulesCMD());
        getCommand("help").setExecutor((CommandExecutor)new HelpCMD());
    }

    public void enableListeners() {
        Bukkit.getPluginManager().registerEvents(new onSleep(), this);
        Bukkit.getPluginManager().registerEvents(new onJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onQuit(), this);
        Bukkit.getPluginManager().registerEvents(new onDeath(), this);
        Bukkit.getPluginManager().registerEvents(new onMobDeath(), this);
        Bukkit.getPluginManager().registerEvents(new onMobSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        Bukkit.getPluginManager().registerEvents(new onChat(), this);
    }

    public void enableFiles() {
        Config.enableConfig();
        this.data = new Data(this);
        Data.saveDefaultData();
        Main.getInstance().reloadConfig();
        this.gui = new Gui(this);
        Gui.saveDefaultGui();
        Gui.reloadGui();
    }

    public void enableAddons() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info(Format.color("&2Successfully hooked into PlaceholderAPI"));
        }

        if (setupEconomy()) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info(Format.color("&2Successfully hooked into vault"));
        }

        if (Bukkit.getPluginManager().getPlugin("HeadDatabase") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info(Format.color("&2Successfully hooked into HeadDatabase"));
        }

    }

    public static Main getInstance() {
        return plugin;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}
