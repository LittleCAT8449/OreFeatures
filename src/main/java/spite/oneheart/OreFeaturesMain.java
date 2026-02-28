package spite.oneheart;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(OreFeaturesMain.MODID)
public class OreFeaturesMain {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "ore_features";
    private static final Logger LOGGER = LogManager.getLogger("OreFeatures");

    public OreFeaturesMain(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.COMMON, OreConfig.SPEC);


    }

}
