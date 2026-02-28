package spite.oneheart.util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.XoroshiroRandomSource;

public class OreUtil {

    public static final int OFFSET_RANGE =2;

    public static BlockPos calculateOffsetPos(long worldSeed, BlockPos originalPos, Block oreBlock  ) {

        if(OFFSET_RANGE==0){
            return originalPos;
        }

        ChunkPos chunkPos =new ChunkPos(originalPos);
        long chunkX= chunkPos.x;
        long chunkZ=chunkPos.z;

        long seed =worldSeed;
        seed^=chunkX*3129871L;
        seed^=chunkZ*11629781L;
        seed^=oreBlock.hashCode()*42688201L;
        seed ^= (long) originalPos.getX() * 73856093L;
        seed ^= (long) originalPos.getZ() * 19349663L;
        seed ^= (long) originalPos.getY() * 83492771L;
        RandomSource random=new XoroshiroRandomSource(seed);
        int offSetX=random.nextIntBetweenInclusive(-OFFSET_RANGE,OFFSET_RANGE);
        int offSetZ=random.nextIntBetweenInclusive(-OFFSET_RANGE,OFFSET_RANGE);
        return originalPos.offset(offSetX,0,offSetZ);


    }

}
