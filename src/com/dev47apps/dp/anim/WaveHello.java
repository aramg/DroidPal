package com.dev47apps.dp.anim;

public class WaveHello extends DroidPalAnim {
	int c, x, y;
	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0:
			x = manager.aObjs[2].idleOffset[0];
			y = manager.aObjs[2].idleOffset[1];
			c = manager.aObjs[2].mBmp.getWidth()/2;
			manager.Rotate(2, 0, 200, c,  10, 800, 0, 0);

			// Move the head a bit as well
			manager.Rotate(0, 0, 5, manager.aObjs[0].mBmp.getWidth(), manager.aObjs[0].mBmp.getHeight(), 500, 0, 0);
			manager.Translate(0,
				manager.aObjs[0].idleOffset[0], manager.aObjs[0].idleOffset[0]+5,
				manager.aObjs[0].idleOffset[1], manager.aObjs[0].idleOffset[1]-10, 500, 0, 0);
			break;
		case 1: case 3:
			manager.Rotate(2, 200, 120, c, 10, 200, 0, 0);
			break;
		case 2: case 4:
			manager.Rotate(2, 120, 200, c, 10, 200, 0, 0);
			break;
		case 5:
			manager.Rotate(2, 200, 0, c, 10, 800, 200, 0);

			manager.Rotate(0, 5, 0, manager.aObjs[0].mBmp.getWidth(), manager.aObjs[0].mBmp.getHeight(), 200, 0, 2);
			manager.Translate(0,
				manager.aObjs[0].idleOffset[0]+5, manager.aObjs[0].idleOffset[0],
				manager.aObjs[0].idleOffset[1]-10, manager.aObjs[0].idleOffset[1], 200, 0, 2);
			break;
		default: stageDelay = -1;
		}

		if (stageDelay != -1)
			manager.Translate(2, x, x, y, y, 1, 0, 0);	// Always have to pin to idle pos

		currStage ++;
		return stageDelay;
	}
}
