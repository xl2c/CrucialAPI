package de.crucial.CrucialAPI.API;

import de.crucial.CrucialAPI.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.logging.Logger;

public class Item {

    private static Main plugin = Main.getPlugin(Main.class);
    private static Logger logger = Server.getLogger("CrucialAPI");

    public boolean addCustomItem(int id, String name, List lore, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name, lore);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            logger.info("Failed to create " + name);
            return false;
        }
    }

    public boolean addCustomItem(int id, String name, ItemStack stack, String[] ingredients){
        try{
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            logger.info("Failed to create " + name);
            return false;
        }
    }

    public boolean addCustomItem(int id, String name, String material, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(Material.getMaterial(material), name);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            logger.info("Failed to create " + name);
            return false;
        }
    }

    public boolean addCustomHead(int id, String name, List lore, String head, String[] ingredients){
        try{
            ItemStack stack = Stack.setStack(head, name, lore);
            return addRecipe(id, name, ingredients, stack);
        } catch(IllegalArgumentException e) {
            logger.info("Failed to create " + name);
            return false;
        }
    }

    public boolean addRecipe(int id, String name, String[] ingredients, ItemStack stack) {
        NamespacedKey key = new NamespacedKey(plugin, name + id);
        ShapedRecipe recipe = new ShapedRecipe(key, stack);
        int num = 47;

        recipe.shape("123","456","789");

        for (String item:ingredients) {
            num++;
            recipe.setIngredient((char) num, Material.getMaterial(item));
        }

        Bukkit.addRecipe(recipe);
        logger.info("Successfully created " + name + " (key: " + name+id + ")");
        return true;
    }
}