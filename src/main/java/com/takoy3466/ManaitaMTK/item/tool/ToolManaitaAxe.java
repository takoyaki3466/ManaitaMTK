package com.takoy3466.ManaitaMTK.item.tool;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;

public class ToolManaitaAxe extends AxeItem {
    public ToolManaitaAxe() {
        super(new Tier() {
            //耐久値の設定(0にすると破壊不可)
            public int getUses() {
                return 0;
            }

            //採掘スピードの設定
            public float getSpeed() {
                return 2100000000f;
            }

            //攻撃力ボーナスの設定
            public float getAttackDamageBonus() {
                return 210000000000000000000000000f;
            }

            //わすれた
            public int getLevel() {
                return 2100000000;
            }

            //エンチャントの付きやすさの設定
            public int getEnchantmentValue() {
                return 2100000000;
            }

            //修理できるか否か(耐久値0ならまあ意味ない)
            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
            //攻撃力、攻撃速度の設定
        }, 1, 2100000000f, new Item.Properties()
                .fireResistant()//燃える耐性を付ける
        );
    }
}
