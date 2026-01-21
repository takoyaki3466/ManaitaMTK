package com.takoy3466.manaitamtk.api.mtkTier;


import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * MTKEnumからの移行。基本的にはMTKEnumから何も変わっていません
 * new作成またはcreateメソッドから作成できます。
 */
@SuppressWarnings("ClassCanBeRecord")
public class MTKTier {
    private final int multiple;
    private final String name;

    /**
     * newを用いた作成以外にも、createメソッドを使うことも出来ます。
     * @param multiple 倍率を示します。MTKEnumのMagnificationと同じです。自然数を入れてください。
     * @param name Stringネームです。レジストリ登録の効率化のために設けていますが使わなければnullでもよいです。
     */
    public MTKTier(int multiple, @Nullable String name) {
        this.multiple = multiple;
        this.name = name;
    }

    /**
     * MTKTierを作成します。
     * @param multiple 倍率を示します。MTKEnumのMagnificationと同じです。自然数を入れてください。
     * @param name Stringネームです。レジストリ登録の効率化のために設けていますが使わなければnullでもよいです。
     * @return MTKTier自身を返します。
     */
    public static MTKTier create(int multiple, @Nullable String name) {
        return new MTKTier(multiple, name);
    }

    /**
     * @return 倍率を返します。
     */
    public int getMultiple() {
        return multiple;
    }

    /**
     * @return Stringネームを返します。nullで登録している場合は使用しないでください。
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MTKTier{" +
                "multiple=" + multiple +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MTKTier mtkTier)) return false;
        return getMultiple() == mtkTier.getMultiple() && Objects.equals(getName(), mtkTier.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMultiple(), getName());
    }
}
