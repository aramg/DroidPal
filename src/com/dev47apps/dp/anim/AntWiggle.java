package com.dev47apps.dp.anim;

public class AntWiggle extends DroidPalAnim {

	public int LoadNextStage() {
		int stageDelay = 0;

		switch(currStage){
		case 0:
			manager.Rotate(8, 0,  10, manager.aObjs[8].mBmp.getWidth(), manager.aObjs[8].mBmp.getHeight(), 200, 0, 0);
			manager.Rotate(9, 0, -10, 0, manager.aObjs[9].mBmp.getHeight(), 200, 0, 0);
			break;
		case 1: case 3:
			manager.Rotate(8, 10, -10, manager.aObjs[8].mBmp.getWidth(), manager.aObjs[8].mBmp.getHeight(), 200, 0, 0);
			manager.Rotate(9, -10, 10, 0, manager.aObjs[9].mBmp.getHeight(), 200, 0, 0);
			break;
		case 2: case 4:
			manager.Rotate(8, -10, 10, manager.aObjs[8].mBmp.getWidth(), manager.aObjs[8].mBmp.getHeight(), 200, 0, 0);
			manager.Rotate(9, 10, -10, 0, manager.aObjs[9].mBmp.getHeight(), 200, 0, 0);
			break;
		case 5:
			manager.Rotate(8, 10, 0, manager.aObjs[8].mBmp.getWidth(), manager.aObjs[8].mBmp.getHeight(), 200, 0, 0);
			manager.Rotate(9, -10, 0, 0, manager.aObjs[9].mBmp.getHeight(), 200, 0, 0);
			break;
		default: stageDelay = -1;
		}

		if (stageDelay != -1){ // Always have to pin to idle pos

		manager.Translate(8,
				manager.aObjs[8].idleOffset[0], manager.aObjs[8].idleOffset[0],
				manager.aObjs[8].idleOffset[1], manager.aObjs[8].idleOffset[1], 1, 0, 0);


		manager.Translate(9,
				manager.aObjs[9].idleOffset[0], manager.aObjs[9].idleOffset[0],
				manager.aObjs[9].idleOffset[1], manager.aObjs[9].idleOffset[1], 1, 0, 0);
		}
		currStage++;
		return stageDelay;
	}

}
