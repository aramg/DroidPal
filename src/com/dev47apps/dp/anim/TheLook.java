package com.dev47apps.dp.anim;

public class TheLook extends DroidPalAnim {

	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0:	// 1/2 Close
			manager.Scale(6, 1f, 1f, 1f, 0.4f, 0.5f, 0, 400, 0, 2);
			manager.Scale(7, 1f, 1f, 1f, 0.4f, 0.5f, 0, 400, 0, 2);
			break;
		case 1: // back up
			stageDelay = 3000;
			manager.Scale(6, 1f, 1f, 0.4f, 1f, 0.5f, 0, 600, 0, 0);
			manager.Scale(7, 1f, 1f, 0.4f, 1f, 0.5f, 0, 600, 0, 0);
			break;
		default: stageDelay = -1;
		}
		if (stageDelay != -1){
		manager.Translate(6,
				manager.aObjs[6].idleOffset[0], manager.aObjs[6].idleOffset[0],
				manager.aObjs[6].idleOffset[1], manager.aObjs[6].idleOffset[1], 1, 0, 0);


		manager.Translate(7,
				manager.aObjs[7].idleOffset[0], manager.aObjs[7].idleOffset[0],
				manager.aObjs[7].idleOffset[1], manager.aObjs[7].idleOffset[1], 1, 0, 0);
		}
		currStage++;
		return stageDelay;
	}

}
