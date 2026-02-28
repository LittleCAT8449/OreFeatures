package spite.oneheart.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import spite.oneheart.OreConfig;
import spite.oneheart.OreSpawnConfusion;

import java.util.HashMap;
import java.util.Map;

@Mixin(OreFeature.class)
public abstract class OreFeatures {
    private static final Logger LOGGER = LogManager.getLogger("OreFeatures");
    private static final Map<Long, RandomSource> chunkRandomMap = new HashMap<>();

    @ModifyVariable(
            method = "place",
            at = @At("HEAD"),
            argsOnly = true
    )
    private FeaturePlaceContext<OreConfiguration> modifyContext(FeaturePlaceContext<OreConfiguration> context) {
        if (!OreConfig.OpenDebug.get()) {
            return context;
        }

        int xConfusion = OreConfig.XConfusion.get();
        int yConfusion = OreConfig.YConfusion.get();
        int zConfusion = OreConfig.ZConfusion.get();

        if (xConfusion == 0 && yConfusion == 0 && zConfusion == 0) {
            return context;
        }

        OreSpawnConfusion.checkAndClearCache();
        BlockPos origin = context.origin();
        int chunkX = (int)Math.floor(origin.getX() / 16.0);
        int chunkZ = (int)Math.floor(origin.getZ() / 16.0);

        long chunkKey = OreSpawnConfusion.getChunkKey(chunkX, chunkZ);
        RandomSource random = chunkRandomMap.get(chunkKey);
        if (random == null) {
            random = new XoroshiroRandomSource(System.nanoTime());
            chunkRandomMap.put(chunkKey, random);
        }

        int offsetX = random.nextIntBetweenInclusive(-xConfusion / 2, xConfusion / 2);
        int offsetY = random.nextIntBetweenInclusive(-yConfusion / 2, yConfusion / 2);
        int offsetZ = random.nextIntBetweenInclusive(-zConfusion / 2, zConfusion / 2);

        BlockPos newOrigin = origin.offset(offsetX, offsetY, offsetZ);


        return new FeaturePlaceContext<>(
                context.topFeature(),
                context.level(),
                context.chunkGenerator(),
                context.random(),
                newOrigin,
                context.config()
        );
    }
}