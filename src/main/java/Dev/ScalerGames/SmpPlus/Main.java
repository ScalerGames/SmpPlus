package Dev.ScalerGames.SmpPlus;

import Dev.ScalerGames.SmpPlus.Commands.*;
import Dev.ScalerGames.SmpPlus.Commands.Item.LoreCMD;
import Dev.ScalerGames.SmpPlus.Commands.Item.NameCMD;
import Dev.ScalerGames.SmpPlus.Features.CraftingRecipe;
import Dev.ScalerGames.SmpPlus.Files.Config;
import Dev.ScalerGames.SmpPlus.Files.Data;
import Dev.ScalerGames.SmpPlus.Files.Gui;
import Dev.ScalerGames.SmpPlus.Files.Lang;
import Dev.ScalerGames.SmpPlus.Gui.GuiListener;
import Dev.ScalerGames.SmpPlus.Gui.RecipeGUI;
import Dev.ScalerGames.SmpPlus.Gui.SettingsGUI;
import Dev.ScalerGames.SmpPlus.Listeners.*;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import Dev.ScalerGames.SmpPlus.Utils.UpdateChecker;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public static Economy econ = null;

    @Override
    public void onEnable() {
        plugin = this;
        enableFiles();
        enableAddons();
        enableCommands();
        enableListeners();
        new UpdateChecker(this, 89809).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Messages.logger("&6There is a new update available");
            } else {
                Messages.logger("&2You are running the latest version");
            }
        });
        for (String menu : Gui.getGuiConfig().getConfigurationSection("Menus").getKeys(false)) {
            GuiListener.store(menu.substring(menu.lastIndexOf(".") + 1),
                    ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&',
                            Gui.getGuiConfig().getString("Menus." + menu.substring(menu.lastIndexOf(".") + 1) + ".name"))));
        }
        Messages.logger("&2Gui List: " + GuiListener.storage.toString());
        Messages.logger("&2You are running version " + Main.getInstance().getDescription().getVersion() + " of SmpPlus");
        CraftingRecipe.addRecipes();
    }

    @Override
    public void onDisable() {

    }

    public static Main getInstance() {
        return plugin;
    }

    public void enableFiles() {
        Config.enableConfig();
        Data.saveDefaultData();
        Lang.saveDefaultLang();
        Gui.saveDefaultGui();
    }

    public void enableAddons() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            Messages.logger("&2Successfully hooked into PlaceholderAPI");
        }

        if (setupEconomy()) {
            Bukkit.getPluginManager().registerEvents(this, this);
            Messages.logger("&2Successfully hooked into Vault");
        }
        if (Bukkit.getPluginManager().getPlugin("BroadcastPlus") != null) {
            Messages.logger("&2Successfully hooked into BroadcastPlus");
        }
    }

    public void enableCommands() {
        getCommand("smp").setExecutor((CommandExecutor) new SmpCMD());
        getCommand("smp").setTabCompleter((TabCompleter) new SmpCMD());
        getCommand("lock").setExecutor((CommandExecutor) new LockCMD());
        getCommand("lock").setTabCompleter((TabCompleter) new LockCMD());
        getCommand("coordinates").setExecutor((CommandExecutor) new CoordinatesCMD());
        getCommand("merchant").setExecutor((CommandExecutor) new MerchantCMD());
        getCommand("mutechat").setExecutor((CommandExecutor) new ChatCMD());
        getCommand("unmutechat").setExecutor((CommandExecutor) new ChatCMD());
        getCommand("help").setExecutor((CommandExecutor) new InfoCMD());
        getCommand("rules").setExecutor((CommandExecutor) new InfoCMD());
        getCommand("kick").setExecutor((CommandExecutor) new KickCMD());
        getCommand("kickall").setExecutor((CommandExecutor) new KickCMD());
        getCommand("itemframe").setExecutor((CommandExecutor) new FrameCMD());
        getCommand("itemframe").setTabCompleter((TabCompleter) new FrameCMD());
        getCommand("itemname").setExecutor((CommandExecutor) new NameCMD());
        getCommand("itemlore").setExecutor((CommandExecutor) new LoreCMD());
        getCommand("itemlore").setTabCompleter((TabCompleter) new LoreCMD());
        getCommand("event").setExecutor((CommandExecutor)new EventCMD());
        getCommand("event").setTabCompleter((TabCompleter)new EventCMD());
    }

    public void enableListeners() {
        Bukkit.getPluginManager().registerEvents(new onJoin(), this);
        Bukkit.getPluginManager().registerEvents(new onQuit(), this);
        Bukkit.getPluginManager().registerEvents(new onChat(), this);
        Bukkit.getPluginManager().registerEvents(new onDeath(), this);
        Bukkit.getPluginManager().registerEvents(new onSleep(), this);
        Bukkit.getPluginManager().registerEvents(new onMobDeath(), this);
        Bukkit.getPluginManager().registerEvents(new SettingsGUI(), this);
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        Bukkit.getPluginManager().registerEvents(new onMobSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new onDestroy(), this);
        Bukkit.getPluginManager().registerEvents(new RecipeGUI(), this);
        Bukkit.getPluginManager().registerEvents(new onClickBlock(), this);
        Bukkit.getPluginManager().registerEvents(new onCommand(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        } else {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp == null) {
                return false;
            }
            econ = rsp.getProvider();
            return true;
        }
    }

}
