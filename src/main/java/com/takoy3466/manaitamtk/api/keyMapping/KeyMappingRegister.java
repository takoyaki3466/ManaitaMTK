package com.takoy3466.manaitamtk.api.keyMapping;

import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;

public class KeyMappingRegister {
    private final String MOD_ID;
    private final String KEY_CATEGORIES;
    private String KEY_NAME;

    public KeyMappingRegister(String modId) {
        this.MOD_ID = modId;
        this.KEY_CATEGORIES = "key." + modId + ".categories";
    }

    public static KeyMappingRegister create(String modId) {
        return new KeyMappingRegister(modId);
    }

    public KeyMapping register(@NotNull String keyName, @NotNull int key) {
        this.KEY_NAME = "key." + MOD_ID + "."+ keyName;
        return new KeyMapping(KEY_NAME, key, KEY_CATEGORIES);

    }

    public String getCategories() {
        return KEY_CATEGORIES;
    }
}
