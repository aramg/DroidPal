package com.dev47apps.dp.anim;

public class Jump extends DroidPalAnim {
	int x,y, cR, cL;
	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0:
			cR = manager.aObjs[2].mBmp.getWidth() / 2;
			cL = manager.aObjs[3].mBmp.getWidth() / 2;
			manager.Rotate(2, 0,  20, cR, 10, 200, 0, 0);
			manager.Rotate(3, 0, -20, cL, 10, 200, 0, 0);

			x = manager.aObjs[1].idleOffset[0];
			y = manager.aObjs[1].idleOffset[1];
			manager.Translate(1, x, x, y, y + 8, 200, 0, 0);
			break;
		case 1:
			manager.Rotate(2, 20, 5, cR, 5, 400, 0, 0);
			manager.Rotate(3, -20, -5, cL, 5, 400, 0, 0);
			manager.Translate(1, x, x, y + 8, y-(manager.mScreenHeight-y)/2, 800, 0, 1);
		break;
		case 2:
			manager.Rotate(2, 5, 0, cR, 5, 1000, 0, 0);
			manager.Rotate(3, -5, 0, cL, 5, 1000, 0, 0);
			manager.Translate(1, x, x, y-(manager.mScreenHeight-y)/2, y, 500, 0, 2);
		break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
