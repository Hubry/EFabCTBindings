/*
 * Copyright (c) 2018 Hubry
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package hubry.efabct;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import mcjty.efab.recipes.EFabShapedRecipe;
import mcjty.efab.recipes.EFabShapelessRecipe;
import mcjty.efab.recipes.IEFabRecipe;
import mcjty.efab.recipes.RecipeManager;
import mcjty.efab.recipes.RecipeTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Builder class for {@link EFabShapedRecipe}
 */
@ZenRegister
@ZenClass("mods.efabct.EFabRecipe")
public class EFabRecipe {
	private final IEFabRecipe delegate;

	private EFabRecipe(IEFabRecipe delegate) {
		this.delegate = delegate;
	}

	@ZenMethod
	public static EFabRecipe shaped(IItemStack output, IIngredient[][] ingredients) {
		IEFabRecipe recipe = new EFabShapedRecipe(CraftTweakerMC.getItemStack(output), mapIngredients(ingredients));
		EFabCTBindings.addRecipe(new AddRecipe(recipe));
		return new EFabRecipe(recipe);
	}

	@ZenMethod
	public static EFabRecipe shapeless(IItemStack output, IIngredient[][] ingredients) {
		IEFabRecipe recipe = new EFabShapelessRecipe(CraftTweakerMC.getItemStack(output), mapIngredients(ingredients));
		EFabCTBindings.addRecipe(new AddRecipe(recipe));
		return new EFabRecipe(recipe);
	}

	@ZenMethod
	public EFabRecipe fluid(ILiquidStack stack) {
		delegate.fluid(CraftTweakerMC.getLiquidStack(stack));
		return this;
	}

	@ZenMethod
	public EFabRecipe rfPerTick(int rf) {
		delegate.rfPerTick(rf);
		return this;
	}

	@ZenMethod
	public EFabRecipe manaPerTick(int mana) {
		delegate.manaPerTick(mana);
		return this;
	}

	@ZenMethod
	public EFabRecipe time(int t) {
		delegate.time(t);
		return this;
	}

	/**
	 * valid values: STEAM, GEARBOX, ADVANCED_GEARBOX, RF,
	 * LIQUID, MANA, COMPUTING, UPGRADE_ARMORY,
	 * UPGRADE_MAGIC, UPGRADE_POWER, UPGRADE_DIGITAL
	 *
	 * @see RecipeTier
	 */
	@ZenMethod
	public EFabRecipe tier(String name) {
		try {
			RecipeTier tier = RecipeTier.valueOf(name);
			delegate.tier(tier);
		} catch (IllegalArgumentException e) {
			CraftTweakerAPI.logWarning("Illegal EFab tier argument: " + name);
		}
		return this;
	}

	private static Object[] mapIngredients(IIngredient[][] ingredients) {

		Map<Character, Ingredient> inputMap = new LinkedHashMap<>();
		List<Object> inputs = new ArrayList<>();
		char currentInput = 'A';
		for(IIngredient[] ingredientLine : ingredients) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < 3; i++) {
				if(i < ingredientLine.length && ingredientLine[i] != null) {
					IIngredient ingredient = ingredientLine[i];
					inputMap.put(currentInput, CraftTweakerMC.getIngredient(ingredient));
					builder.append(currentInput++);
				} else {
					builder.append(' ');
				}
			}
			inputs.add(builder.toString());
		}
		for(Map.Entry<Character, Ingredient> mapping : inputMap.entrySet()) {
			inputs.add(mapping.getKey());
			inputs.add(mapping.getValue());
		}
		return inputs.toArray();
	}

	static class AddRecipe implements IAction {
		private final IEFabRecipe recipe;

		AddRecipe(IEFabRecipe recipe) {
			this.recipe = recipe;
		}

		@Override
		public void apply() {
			RecipeManager.registerRecipe(recipe);
		}

		@Override
		public String describe() {
			ItemStack output = recipe.cast().getRecipeOutput();
			return "Adding EFab recipe for " + output.getItem().getRegistryName() + ":" + output.getMetadata();
		}
	}
}
