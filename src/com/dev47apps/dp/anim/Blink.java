package com.dev47apps.dp.anim;

import com.dev47apps.dp.AnimManager;

public class Blink extends DroidPalAnim {
	int total;
	int count;
	boolean toggle;

	@Override
	public void SetManager(AnimManager ref){
		super.SetManager(ref);
		total = manager.getRand(2);
		count = 0;
		toggle = true;
	}

	/* Random number of blinks; 1 - 3 */
	public int LoadNextStage() {
		int stageDelay;
		if (toggle){
			if (count++ > total) { stageDelay = -1; }else {
				stageDelay = 250;
				manager.Scale(6, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 200, 0, 2);
				manager.Scale(7, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 200, 0, 2);
			}
		} else {
			manager.Scale(6, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 200, 0, 0);
			manager.Scale(7, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 200, 0, 0);
			stageDelay = 120;
		}
		toggle = !toggle;

		if (stageDelay != -1){
		manager.Translate(6,
				manager.aObjs[6].idleOffset[0], manager.aObjs[6].idleOffset[0],
				manager.aObjs[6].idleOffset[1], manager.aObjs[6].idleOffset[1], 1, 0, 0);

		manager.Translate(7,
				manager.aObjs[7].idleOffset[0], manager.aObjs[7].idleOffset[0],
				manager.aObjs[7].idleOffset[1], manager.aObjs[7].idleOffset[1], 1, 0, 0);
		}
		return stageDelay;
	}
}
