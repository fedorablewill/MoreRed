package absorr.morered.base;

import net.minecraft.src.*;

public class CommonProxy 
{
	public static String itemPic = "/absorr/morered/items.png";
	public static String blockPic = "/absorr/morered/blocks.png";
	
	/*
	 * Creates more villager trading recipes
	 */
	public static void addMerchantRecipies()
	{
		MerchantRecipeList npcTrade = new MerchantRecipeList();
		npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (mod_MoreRed.rsMulti, 1)));
		npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (Item.emerald, 4), new ItemStack (mod_MoreRed.rsIngot, 3)));
		npcTrade.addToListWithCheck(new MerchantRecipe (new ItemStack (mod_MoreRed.rsIngot, 3), new ItemStack (Item.emerald, 1)));
	}
	
	/*
	 * Only used client side
	 */
	public void registerRenderers() {}
}