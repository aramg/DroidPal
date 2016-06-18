package com.dev47apps.dp.anim;

public class Idle extends DroidPalAnim {
	public int LoadNextStage() {
		int stageDelay = 0;
		// Need to update all the transforms after screen parameters change
		// So the android is properly re-drawn at the new idle coords
		if (currStage == 0)
			manager.Translate(1, manager.aObjs[1].idleOffset[0], manager.aObjs[1].idleOffset[0],
					manager.aObjs[1].idleOffset[1], manager.aObjs[1].idleOffset[1], 2, 0, 0);

		else stageDelay = -1;

		currStage ++;
		return stageDelay;
	}
}
