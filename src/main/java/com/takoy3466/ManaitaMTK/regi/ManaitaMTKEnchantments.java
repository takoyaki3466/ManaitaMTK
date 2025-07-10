package com.takoy3466.ManaitaMTK.regi;

import com.takoy3466.ManaitaMTK.ManaitaMTK;
import com.takoy3466.ManaitaMTK.enchant.FortuneMTK;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ManaitaMTKEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, ManaitaMTK.MOD_ID);

    public static final RegistryObject<Enchantment> FORTUNE_MTK =
            ENCHANTMENTS.register("fortune_mtk", FortuneMTK::new);
}
