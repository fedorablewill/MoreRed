package absorr.morered.base;

//import net.minecraft.src.absorr.morered.external.IC2Handler;
import net.minecraft.src.*;
import net.minecraftforge.client.*;
import net.minecraftforge.common.*;
import java.awt.List;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.network.NetworkMod;
import absorr.morered.base.*;
import absorr.morered.materials.*;

@Mod(modid="MoreRed", name="MoreRed", version="Build 008")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class MoreRed extends BaseMod
{
	@SidedProxy(clientSide = "absorr.morered.base.ClientProxy", serverSide = "absorr.morered.base.CommonProxy", bukkitSide = "absorr.morered.base.CommonProxy")
	public static CommonProxy proxy;
	
	//Creates the items and blocks
    public static Item rsChunk;
    public static Item rsIngot;
    public static Item rsPick;
    public static Item rsSpade;
    public static Item rsHoe;
    public static Item rsAxe;
    public static Item rsSword;
    public static Item rsMulti;
    public static Item crowbar;
    public static Item screwdriver;
    public static Block recharger; 
    public static Block invScanner; 
    public static Block ironButton; 
    public static Block rsChest;
    public static Block detecPlate; 
    
    
    public void load()
    {
    	proxy.registerRenderers();
    	
    	rsChunk = new MoreItems(Config.rsChunkID, 64, CreativeTabs.tabMaterials).setItemName("redstoneChunk").setIconIndex(2);
    	rsIngot = new MoreItems(Config.rsIngotID, 64, CreativeTabs.tabMaterials).setItemName("redstoneIngot").setIconIndex(3);
    	rsPick = new ItemPickaxe(Config.rsPickID, EnumToolMaterial.IRON).setItemName("redstonePickaxe").setIconIndex(4).setTextureFile(proxy.itemPic).setCreativeTab(CreativeTabs.tabTools);
        rsSpade = new ItemSpade(Config.rsSpadeID, EnumToolMaterial.IRON).setItemName("redstoneShovel").setIconIndex(5).setTextureFile(proxy.itemPic).setCreativeTab(CreativeTabs.tabTools);
        rsHoe = new ItemHoe(Config.rsHoeID, EnumToolMaterial.IRON).setItemName("redstoneHoe").setIconIndex(6).setTextureFile(proxy.itemPic).setCreativeTab(CreativeTabs.tabTools);
        rsAxe = new ItemAxe(Config.rsAxeID, EnumToolMaterial.IRON).setItemName("redstoneAxe").setIconIndex(7).setTextureFile(proxy.itemPic).setCreativeTab(CreativeTabs.tabTools);
        rsSword = new ItemRsSword(Config.rsSwordID, EnumToolMaterial.IRON).setItemName("redstoneSword").setIconIndex(8).setTextureFile(proxy.itemPic).setCreativeTab(CreativeTabs.tabTools);
        rsMulti = new ItemMultiTool(Config.rsMultiID, 1, EnumToolMaterial.IRON).setItemName("redstoneMultitool").setIconIndex(14);
        crowbar = new MoreItems(Config.crowbarID, 1, CreativeTabs.tabTools).setItemName("crowbar").setIconIndex(16);
        screwdriver = new MoreItems(Config.screwdriverID, 1, CreativeTabs.tabTools).setItemName("screwdriver").setIconIndex(1);
        recharger = new BlockToolCharger(Config.rechargeID, 0).setHardness(1.0F).setResistance(6000.0F).setLightValue(1.0F).setBlockName("Tool Recharging Station"); 
        invScanner = new BlockInventoryScanner(Config.scannerID, 6).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Inventory Scanner"); 
        ironButton = new BlockIronButton(Config.ironButtonID, 22).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Iron Button"); 
        rsChest = new BlockRedChest(Config.rsChestID).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Redstone Chest"); 
        detecPlate = new BlockDetecPlate(Config.detecID, 9, Material.rock).setHardness(1.0F).setResistance(6000.0F).setLightValue(0.0F).setBlockName("Detection Plate");

    	loadMaterials();
    	proxy.addMerchantRecipies();
  		ModLoader.registerTileEntity(TileEntityScanner.class, "Inventory Scanner");
  		ModLoader.registerTileEntity(TileEntityDetecPlate.class, "Detection Plate");
    	EntityList.entityEggs.put(Integer.valueOf(63), new EntityEggInfo(63, 0, 9118312));
    }
    public boolean classExists (String className)
    {
    	try {
    		Class.forName (className);
    		return true;
    	}
    	catch (ClassNotFoundException exception) {
    		return false;
    	}
    }
    public void loadMaterials()
    {
    	//ModLoader.getMinecraftInstance().thePlayer.addChatMessage("MoreCrafts Development Build Loaded Succesfully");
    	
    	if(classExists("mod_IC2")) 
        {
            //IC2Handler.loadRecipies();
        }
    	//Redstone Chunk
        ModLoader.addName(rsChunk, "Redstone Chunk");
        ModLoader.addSmelting(Item.redstone.shiftedIndex, new ItemStack(rsChunk));
        //Redstone Ingot
        ModLoader.addName(rsIngot, "Redstone Ingot");
        ModLoader.addShapelessRecipe(new ItemStack(rsIngot, 1), new Object[] {rsChunk, rsChunk});
        //Redstone Pickaxe
        ModLoader.addName(rsPick, "Redstone Pickaxe");
        ModLoader.addRecipe(new ItemStack(rsPick, 1), new Object[] {"RRR", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Tool Recharging Station
        ModLoader.registerBlock(recharger); 
        ModLoader.addName(recharger, "Tool Recharging Station");
        ModLoader.addRecipe(new ItemStack(recharger, 1), new Object[] { 
        "IRI", "STS", "SSS", Character.valueOf('S'), Block.stone, Character.valueOf('T'), Block.torchRedstoneActive, Character.valueOf('R'), Item.redstone, Character.valueOf('I'), Block.blockSteel
        }); 
        //Redstone Shovel
        ModLoader.addName(rsSpade, "Redstone Shovel");
        ModLoader.addRecipe(new ItemStack(rsSpade, 1), new Object[] {"ORO", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Hoe
        ModLoader.addName(rsHoe, "Redstone Hoe");
        ModLoader.addRecipe(new ItemStack(rsHoe, 1), new Object[] {"ORR", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        ModLoader.addRecipe(new ItemStack(rsHoe, 1), new Object[] {"RRO", "OSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Axe
        ModLoader.addName(rsAxe, "Redstone Axe");
        ModLoader.addRecipe(new ItemStack(rsAxe, 1), new Object[] {"RRO", "RSO", "OSO", 'R', rsIngot, 'S', Item.stick});
        ModLoader.addRecipe(new ItemStack(rsAxe, 1), new Object[] {"ORR", "OSR", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Sword
        ModLoader.addName(rsSword, "Redstone Sword");
        ModLoader.addRecipe(new ItemStack(rsSword, 1), new Object[] {"ORO", "ORO", "OSO", 'R', rsIngot, 'S', Item.stick});
        //Redstone Multi-Tool
        ModLoader.addName(rsMulti, "Redstone Multi-Tool");
        ModLoader.addRecipe(new ItemStack(rsMulti, 1), new Object[] {"AHP", "OSO", "OSO", 'S', Item.stick, 'A', rsAxe, 'H', rsSpade, 'P', rsPick});
        //Crowbar
        ModLoader.addName(crowbar, "Crowbar");
        ModLoader.addRecipe(new ItemStack(crowbar, 1), new Object[] {"IOO", "IIO", "OOI", 'I', Item.ingotIron});
        //Inventory Scanner
        ModLoader.registerBlock(invScanner); 
        ModLoader.addName(new ItemStack(invScanner, 1), "Inventory Scanner");
        ModLoader.addRecipe(new ItemStack(invScanner, 1), new Object[] {"LEL", "IRI", "LEL", 'E', Item.enderPearl, 'I', Item.ingotIron, 'R', Item.redstone, 'L', new ItemStack(Item.dyePowder, 1, 4)});
        //Iron Button
        ModLoader.registerBlock(ironButton); 
        ModLoader.addName(new ItemStack(ironButton, 1), "Iron Button");
        ModLoader.addRecipe(new ItemStack(ironButton, 1), new Object[] {"OIO", "IBI", "OIO", 'B', Block.stoneButton, 'I', Item.ingotIron});
        //Redstone Block
        //ModLoader.addLocalization("tile.blankSpawner.blankSpawn.name", "Empty Monster Spawner");
        //ModLoader.addLocalization("tile.blankSpawner.rsBlock.name", "Redstone Block");
        //ModLoader.addRecipe(new ItemStack(blankSpawner, 1, 1), new Object[] { "RR", "RR", Character.valueOf('R'), rsIngot});
        //Screwdriver
        ModLoader.addName(screwdriver, "Screwdriver");
        ModLoader.addRecipe(new ItemStack(screwdriver, 1), new Object[] {"IO", "IO", 'I', Item.ingotIron});
        //Redstone Chest
        ModLoader.registerBlock(rsChest); 
        ModLoader.addName(new ItemStack(rsChest), "Redstone Chest");
        ModLoader.addRecipe(new ItemStack(rsChest, 1), new Object[] {"ORO", "RCR", "RRR", 'R', rsIngot, 'C', Block.chest});
        //Detection Plate
        ModLoader.registerBlock(detecPlate); 
        ModLoader.addName(new ItemStack(detecPlate), "Detection Plate");
        ModLoader.addRecipe(new ItemStack(detecPlate), new Object[] {"CRC", "RER", "CBC", 'R', rsIngot, 'B', Block.brick, 'C', Item.brick, 'E', Item.enderPearl});
    }
    public String getVersion()
    {
        return "1.1.008";
    }
    public GuiContainer getContainerGUI(EntityClientPlayerMP player, int id, int x, int y, int z)
    {
    	switch(id)
    	{
    		case 0: return new GuiScanner(player.inventory, (TileEntityScanner)player.worldObj.getBlockTileEntity(x, y, z));
    		default: return null;
    	}
    }
}