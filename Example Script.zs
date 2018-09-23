import mods.efabct.EFabRecipe;

//	public static EFabRecipe shaped(IItemStack output, IIngredient[][] ingredients)
//	public static EFabRecipe shapeless(IItemStack output, IIngredient[][] ingredients)
//	public EFabRecipe fluid(ILiquidStack stack)
//	public EFabRecipe rfPerTick(int rf)
//	public EFabRecipe manaPerTick(int mana)
//	public EFabRecipe time(int t)
//	public EFabRecipe tier(String name)

// Some very dumb recipes done for testing.

EFabRecipe.shaped(<minecraft:dirt>, [[<ore:ingotIron>, <ore:ingotGold>, <ore:ingotIron>]])
    .tier("COMPUTING")
    .time(20);
    
EFabRecipe.shaped(<minecraft:dirt>, [[<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>],
[<ore:ingotIron>, <ore:ingotIron>, <ore:ingotGold>],
[<ore:ingotIron>, <ore:ingotIron>, <ore:cobblestone>]])
    .tier("GEARBOX")
    .time(20)
    .rfPerTick(10);
    
    
EFabRecipe.shaped(<minecraft:grass>, [[<ore:ingotIron>, <ore:ingotIron>, <ore:ingotIron>]])
    .tier("GEARBOX")
    .tier("COMPUTING")
    .time(20)
    .rfPerTick(10);
    
    
EFabRecipe.shapeless(<minecraft:iron_pickaxe>, [[<ore:ingotIron>], [<ore:ingotGold>], [<minecraft:stick>]])
    .time(250);
    
EFabRecipe.shaped(<minecraft:stone_pickaxe>, [[<ore:gemDiamond>], [<ore:ingotGold>], [<minecraft:stick>]])
    .time(50)
    .fluid(<liquid:water> * 500);
    
    
EFabRecipe.shaped(<minecraft:diamond_block>, [[<ore:ingotIron>, null, <ore:ingotIron>], 
        [<ore:cobblestone>, null, null],
        [null, null, <minecraft:stick>]])
    .tier("GEARBOX")
    .tier("COMPUTING")
    .time(20);