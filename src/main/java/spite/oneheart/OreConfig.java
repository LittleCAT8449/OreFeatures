package spite.oneheart;

import net.neoforged.neoforge.common.ModConfigSpec;

public class OreConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder().comment("All Config").push("Config");
    public static final ModConfigSpec.IntValue XConfusion = BUILDER.defineInRange("XConfusion", 16, 0, 64);
    public static final ModConfigSpec.IntValue YConfusion = BUILDER.defineInRange("YConfusion", 8, 0, 64);
    public static final ModConfigSpec.IntValue ZConfusion = BUILDER.defineInRange("ZConfusion", 16, 0, 64);
    public static final ModConfigSpec.BooleanValue OpenDebug = BUILDER.define("OpenDebug", false);
    static final ModConfigSpec SPEC = BUILDER.build();


}
