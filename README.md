S4X8 Glowstone Pixel 1.0 (30/VIII/2012)
=======================================

What is this?
-------------

Glowstone Pixel is a [RedstoneChips] plugin for [Bukkit]-based [Minecraft] SMP servers which allows you to create a pixel using Glowstone (when on) and Obsidian (when off). Altough this allows you to use only two colors, this pixel has the advantage of having a higher contrast (difference in brightness between the ON and OFF states) than Redstone Lamp, and produces light itself, while wool-based pixels does not.

Installation
------------

To install the chip on your Bukkit server, you may either download the pre-built version, or build it yourself.

The pre-built versions can be found at [/binary].

To compile it yourself, you need a working [Maven] enviroment. First, open a new shell window and download the latest version of etCommon:

	git clone http://github.com/eisental/etCommon
	cd etCommon

Without closing the shell window, open your favourite text editor, edit `/etCommon/pom.xml` file and add this inside the `<project>` tag:

	<repositories>
		<repository>
			<id>bukkit-repo</id>
			<url>http://repo.bukkit.org/content/groups/public/</url>
		</repository>
	</repositories>

Save it, go back to the shell and run the following:

	mvn install
	cd ..
	
Installing etCommon should be done automatically by Maven when installing RedstoneChips, but as the author of etCommon is not providing a binary for Maven, it has to be done manually.

Now download and compile RedstoneChips:
	
	git clone http://github.com/eisental/RedstoneChips
	cd RedstoneChips
	mvn package install
	cd ..
	
Then download and compile Glowstone Pixel:

	git clone http://github.com/socram8888/glowpixel
	cd glowpixel
	mvn package
	
The resulting compiled Java Archive file (.jar) ready to be used will be placed at /target. Just copy it to Bukkit's plugin folder. If you don't have RedstoneChips installed on your server, get it from ./RedstoneChips/target.

How can I build the chip?
-------------------------

After having the chip installed, you need to build a chip with `glowpixel` on the sign, with a single input, one or more interface pins, and no outputs.

When the chip receives a signal through its input pin, the chip will change pixel blocks (obsidian and glowstone) near the interface pins to obsidian, if the input is low, or glowstone, if the input is high.

"Near" means that the pixel blocks must be directly in contact with the interface blocks, or in contact with other pixel blocks.

Configuration
-------------

To prevent the server from crashing, I've added a limit in the radius. By default the limit is set to 20 blocks. Pixel blocks outside this limit will not get modified.

This file is set in the `glowpixel.maxdistance` configuration key of RedstoneChips. This value is a double-precision float. You may either specify an integer (for example, "30") or a decimal number (like "29.515649").

To change this limit, you can either manually modify this value in plugins/RedstoneChips/preferences.yml with the server stopped, or type `/rcprefs glowpixel.maxdistance (distance)` in-game or in the server console. 

Things to do
------------

 * Add features from the original `pixel` chip (like wireless channels and DMA)

License
-----

This software is released under the MIT license:

	Copyright © 2012 Marcos Vives Del Sol

	Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

Changelog
---------
 * 30/VIII/2012 1.0: First release

About the author
----------------

My name is Marcos Vives Del Sol, aka "socram8888". I'm a 17-year-old Spanish guy who wants to became a programmer. If you want to report a bug, ask for a new feature, or just say hello, you can contact me in my e-mail account [socram8888@gmail.com].

Notes
-----

Minecraft is © Mojang AB.  
RedstoneChips is © Tal Eisenberg.

  [/binary]: https://github.com/socram8888/glowpixel/tree/master/binary
  [Bukkit]: http://bukkit.org/
  [CC-BY 3.0 license]: http://creativecommons.org/licenses/by-sa/3.0
  [Maven]: http://maven.apache.org/
  [Minecraft]: http://www.minecraft.net/
  [RedstoneChips]: http://eisental.github.com/RedstoneChips/
  [socram8888@gmail.com]: mailto:socram8888@gmail.com
  [the full version of the license CC-BY 3.0 license]: http://creativecommons.org/licenses/by-sa/3.0/legalcode

