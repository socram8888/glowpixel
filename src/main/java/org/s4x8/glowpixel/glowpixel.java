/**
 * @author Marcos Vives Del Sol
 * @version 1.0, 28/VI/12
 *
 * glowpixel.java
 * The glowpixel chip
 * 
 * Licensed under the CC-BY 3.0 (http://creativecommons.org/licenses/by/3.0/) license
 */

package org.s4x8.glowpixel;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

import org.tal.redstonechips.circuit.Circuit;
import org.tal.redstonechips.circuit.io.InterfaceBlock;
import org.tal.redstonechips.util.Locations;

public class glowpixel extends Circuit {
	private static Material[] materials = new Material[] { Material.OBSIDIAN, Material.GLOWSTONE };

	private static BlockFace[] faces = new BlockFace[] { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST };

	private boolean isPixelMaterial(Material material) {
		if (materials[0] == material) return true;
		if (materials[1] == material) return true;
		return false;
	};

	private Material newMaterial;
	private ArrayList<Location> scanned = new ArrayList<Location>();
	private Location origin;

	private double maxDistance = 20;

	private void scanBlock(Location here) {
		if (scanned.contains(here)) return;
		scanned.add(here);
		if (origin.distance(here) > maxDistance) return;
		
		for (BlockFace face : faces) {
			Location near = Locations.getFace(here, face);
			Block nearBlock = world.getBlockAt(near);
			if (isPixelMaterial(nearBlock.getType())) {
				nearBlock.setType(newMaterial);
				scanBlock(near);
			};
		};
	};

    public void inputChange(int index, boolean state) {
		try {
			maxDistance = Double.parseDouble(redstoneChips.getPrefs().getPrefs().get("glowpixel.maxdistance").toString());
		} catch (Exception e) { };

		newMaterial = materials[state ? 1 : 0];
		scanned.clear();

		for (InterfaceBlock ib : interfaceBlocks) {
			origin = ib.getLocation();
			scanBlock(origin);
		};
    };

    public boolean init(CommandSender sender, String[] strings) {
		if (inputs.length != 1) {
			info(sender, "This chip must have a single input");
			return false;
		};
		
		if (outputs.length != 0) {
			info(sender, "This chip does not need any output");
			return false;
		};

		if (interfaceBlocks.length < 1) {
			error(sender, "This chip must have at least one interface block");
			return false;
		};

        return true;
    };
};

