/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

public abstract class Screen implements IScreen {

	protected GameWorld world;

	public Screen(GameWorld world) {
		this.world = world;
	}
}
