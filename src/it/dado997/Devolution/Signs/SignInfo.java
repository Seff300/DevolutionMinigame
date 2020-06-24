package it.dado997.Devolution.Signs;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;

public class SignInfo {


        private String worldname;
        private int x;
        private int y;
        private int z;

        public SignInfo(final String worldname, final int x, final int y, final int z) {
        this.worldname = worldname;
        this.x = x;
        this.y = y;
        this.z = z;
    }

        public SignInfo(final Block block) {
        this.worldname = block.getWorld().getName();
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
    }

    public Block getBlock() {
        if (this.worldname == null) {
            return null;
        }
        final World world = Bukkit.getWorld(this.worldname);
        if (world != null && world.isChunkLoaded(this.x >> 4, this.z >> 4)) {
            return world.getBlockAt(this.x, this.y, this.z);
        }
        return null;
    }

    protected String getWorldName() {
        return this.worldname;
    }

    protected int getX() {
        return this.x;
    }

    protected int getY() {
        return this.y;
    }

    protected int getZ() {
        return this.z;
    }

    @Override
    public int hashCode() {
        return this.worldname.hashCode() ^ this.x ^ this.y ^ this.z;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof SignInfo)) {
            return super.equals(obj);
        }
        final SignInfo si = (SignInfo)obj;
        return this.worldname.equals(si.worldname) && this.x == si.x && this.y == si.y && this.z == si.z;
    }

}
