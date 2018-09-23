# EFabCTBindings
A very simple mod that adds CraftTweaker bindings for EFab.

This allows for some easier adding of EFab recipes, as you have access to various features of ZenScript - like loops.
No more making 244 recipes for each tool part by hand!

For examples, see [the example script](Example-Script.zs).

#### Building the mod

* Clone the repository.
* Get EFab 0.1.1 from [CurseForge](https://minecraft.curseforge.com/projects/efab) and deobfuscate it with [BON2](https://github.com/tterrag1098/BON2).
* Stick the resulting deobf jar into `libs`.
* `gradlew setupDecompWorkspace build`