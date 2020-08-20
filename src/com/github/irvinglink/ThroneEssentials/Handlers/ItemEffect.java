package com.github.irvinglink.ThroneEssentials.Handlers;

public class ItemEffect {

    private final ItemEffectType type;
    private final int multipler;

    public ItemEffect(ItemEffectType type, int multipler) {
        this.type = type;
        this.multipler = multipler;
    }

    public ItemEffectType getType() {
        return type;
    }

    public int getMultipler() {
        return multipler;
    }
}

enum ItemEffectType {

    PROJECTILEHITEVENT

}
