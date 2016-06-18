package com.dev47apps.dp.anim;

import com.dev47apps.dp.AnimManager;

public abstract class DroidPalAnim {

	AnimManager manager;
	int currStage;

	public DroidPalAnim(){
		currStage = 0;
	}
	public void SetManager(AnimManager ref){
		manager = ref;
	}

	/**
	 * Load the next stage by calling AnimManager utility functions.
	 * Return the stage delay, or -1 if no more stages left
	 */
	public abstract int LoadNextStage();
}
