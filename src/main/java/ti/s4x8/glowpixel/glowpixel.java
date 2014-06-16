/**
 * @author Marcos Vives Del Sol
 * @version 1.0, 28/VI/12
 *
 * glowpixel.java
 * The glowpixel chip
 * 
 * Licensed under the CC-BY 3.0 (http://creativecommons.org/licenses/by/3.0/) license
 */

package ti.s4x8.glowpixel;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import org.redstonechips.RCPrefs;
import org.redstonechips.circuit.Circuit;
import org.redstonechips.chip.io.InterfaceBlock;
import org.redstonechips.util.Locations;

public class glowpixel extends Circuit {
	private static final Material[] MATERIALS = new Material[] { Material.OBSIDIAN, Material.GLOWSTONE };

	private static final BlockFace[] FACES = new BlockFace[] { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };

	private boolean isPixelMaterial(Material material) {
		if (MATERIALS[0] == material) return true;
		if (MATERIALS[1] == material) return true;
		return false;
	};

	private Material newMaterial;
	private ArrayList<Location> scanned = new ArrayList<Location>();
	private Location origin;

	private double maxDistance = 400;

	private void scanBlock(Location here) {
		if (scanned.contains(here)) return;
		scanned.add(here);
		if (origin.distanceSquared(here) > maxDistance) return;
		
		for (BlockFace face : FACES) {
			Location near = Locations.getFace(here, face);
			Block nearBlock = near.getBlock();
			if (isPixelMaterial(nearBlock.getType())) {
				nearBlock.setType(newMaterial);
				scanBlock(near);
			};
		};
	};

    public void input(boolean state, int index) {
		newMaterial = MATERIALS[state ? 1 : 0];
		scanned.clear();

		for (InterfaceBlock ib : chip.interfaceBlocks) {
			origin = ib.getLocation();
			scanBlock(origin);
		};
    };

    public Circuit init(String[] strings) {
		try {
			maxDistance = Double.parseDouble((String) RCPrefs.getPref("glowpixel.maxDistance"));
		} catch (Exception e) { };
		maxDistance = maxDistance * maxDistance;

		if (inputs.length != 1) {
			return error("This chip must have a single input");
		};
		
		if (outputs.length != 0) {
			return error("This chip does not need any output");
		};

		if (chip.interfaceBlocks.length < 1) {
			return error("This chip must have at least one interface block");
		};

        return this;
    };
};

