package me.Freeze_Dolphin.lab;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Chunk;
import org.bukkit.block.Biome;

public class OreGenSystem {
    public static final Map<String, GEOResource> map = new HashMap<>();

    public static Collection<GEOResource> listResources() {

        return map.values();
    }

    public static void registerResource(GEOResource resource) {

        map.put(resource.getName(), resource);
    }

    public static GEOResource getResource(String name) {

        return map.get(name);
    }

    private static int getDefault(GEOResource resource, Biome biome) {

        if (resource == null) return 0;

        Config cfg = new Config("plugins/Slimefun/generators/" + resource.getName() + ".cfg");

        return cfg.getConfiguration().getInt(biome.toString());
    }

    public static void setSupplies(GEOResource resource, Chunk chunk, int amount) {

        if (resource == null) return;
        BlockStorage.setChunkInfo(
                chunk.getWorld(),
                chunk.getX(),
                chunk.getZ(),
                "resources_" + resource.getName().toUpperCase(),
                String.valueOf(amount));
    }

    public static int generateSupplies(GEOResource resource, Chunk chunk) {

        if (resource == null) return 0;

        int supplies = getDefault(resource, chunk.getBlock(5, 50, 5).getBiome());

        BlockStorage.setChunkInfo(
                chunk.getWorld(),
                chunk.getX(),
                chunk.getZ(),
                "resources_" + resource.getName().toUpperCase(),
                String.valueOf(supplies));

        return supplies;
    }

    public static int getSupplies(GEOResource resource, Chunk chunk, boolean generate) {

        if (resource == null) return 0;

        if (wasResourceGenerated(resource, chunk)) {

            return Integer.parseInt(BlockStorage.getChunkInfo(
                    chunk.getWorld(),
                    chunk.getX(),
                    chunk.getZ(),
                    "resources_" + resource.getName().toUpperCase()));
        }

        if (!generate) {

            return 0;
        }

        return generateSupplies(resource, chunk);
    }

    public static boolean wasResourceGenerated(GEOResource resource, Chunk chunk) {

        if (resource == null) return false;

        return BlockStorage.getChunkInfo(
                        chunk.getWorld(),
                        chunk.getX(),
                        chunk.getZ(),
                        "resources_" + resource.getName().toUpperCase())
                != null;
    }
}
