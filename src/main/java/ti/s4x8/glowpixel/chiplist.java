
package ti.s4x8.glowpixel;

import org.redstonechips.RCPrefs;
import org.redstonechips.RedstoneChips;
import org.redstonechips.circuit.CircuitLibrary;

public class chiplist extends CircuitLibrary {
    public Class[] getCircuitClasses() {
        return new Class[] { glowpixel.class };
    };

	public void onRedstoneChipsEnable(RedstoneChips rsc) {
		RCPrefs.registerCircuitPreference(glowpixel.class, "maxdistance", 7);
	};
};
