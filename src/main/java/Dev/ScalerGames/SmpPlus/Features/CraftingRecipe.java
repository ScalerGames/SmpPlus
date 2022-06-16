package Dev.ScalerGames.SmpPlus.Features;

import Dev.ScalerGames.SmpPlus.Main;
import Dev.ScalerGames.SmpPlus.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CraftingRecipe {

    public static void addRecipes() {
        if (Main.getInstance().getConfig().getConfigurationSection("CraftingRecipes") != null) {
            for (String recipe : Main.getInstance().getConfig().getConfigurationSection("CraftingRecipes").getKeys(false)) {
                ItemStack item = new ItemStack(Material.matchMaterial(Main.getInstance().getConfig().getString("CraftingRecipes." + recipe + ".result.item")));
                ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), recipe), item);
                shapedRecipe.shape(Main.getInstance().getConfig().getString("CraftingRecipes." + recipe + ".shape.row_1"),
                        Main.getInstance().getConfig().getString("CraftingRecipes." + recipe + ".shape.row_2"),
                        Main.getInstance().getConfig().getString("CraftingRecipes." + recipe + ".shape.row_3"));
                recipeShape(recipe, shapedRecipe);
                Bukkit.addRecipe(shapedRecipe);
            }
        }
    }

    public static void recipeShape(String recipe, ShapedRecipe shapedRecipe) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int i = 0;
        for (String item : Main.getInstance().getConfig().getStringList("CraftingRecipes." + recipe + ".materials")) {
            shapedRecipe.setIngredient(alphabet.charAt(i), Material.matchMaterial(item));
            i++;
        }
    }
}
