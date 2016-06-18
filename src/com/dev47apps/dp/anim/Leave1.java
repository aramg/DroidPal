package com.dev47apps.dp.anim;

public class Leave1 extends DroidPalAnim {

	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0:
			int x = manager.aObjs[1].idleOffset[0];
			int y = manager.aObjs[1].idleOffset[1];
			manager.Translate(1, x, x, y, y + manager.mScreenHeight, 1000, 0, 2);
			break;
		default: stageDelay = -1;
		}
		currStage ++;
		return stageDelay;
	}

}
