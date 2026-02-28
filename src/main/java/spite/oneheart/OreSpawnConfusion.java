package spite.oneheart;

import net.minecraft.util.RandomSource;

import net.neoforged.fml.common.Mod;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Mod(OreSpawnConfusion.MOD_ID)
public class OreSpawnConfusion {
    public static final String MOD_ID = OreFeaturesMain.MODID;


    public static final Map<Long, RandomSource> chunkRandomMap = new ConcurrentHashMap<>();
    public static long lastClearTime = System.currentTimeMillis();
    public static final long CLEAR_INTERVAL = 5 * 60 * 1000L;



    public static void clearCache() {
        chunkRandomMap.clear();
        lastClearTime = System.currentTimeMillis();
    }


    public static void checkAndClearCache() {
        long currentNow = System.currentTimeMillis();
        if (currentNow - lastClearTime > CLEAR_INTERVAL) {
            clearCache();
        }
    }

    public static long getChunkKey(int chunkX, int chunkZ) {
        return ((long) chunkX << 32) | (chunkZ & 0xFFFFFFFFL);
    }

}
