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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Mod(
	modid = EFabCTBindings.MOD_ID,
	name = EFabCTBindings.MOD_NAME,
	version = EFabCTBindings.VERSION,
	dependencies = EFabCTBindings.DEPENDENCIES
)
public class EFabCTBindings {

	public static final String MOD_ID = "efabctbindings";
	public static final String MOD_NAME = "EFab CT Bindings";
	public static final String VERSION = "0.1";
	public static final String DEPENDENCIES = "required-after:efab;required-after:crafttweaker;";

	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static final List<IAction> ADDITIONS = new ArrayList<>();

	static void addRecipe(IAction action) {
		ADDITIONS.add(action);
	}

	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event) {
		ADDITIONS.forEach(IAction::apply);
		CraftTweakerAPI.logInfo("Added " + ADDITIONS.size() + " EFab recipes");
	}


}
