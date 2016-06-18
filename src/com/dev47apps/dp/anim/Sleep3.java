package com.dev47apps.dp.anim;

public class Sleep3 extends DroidPalAnim {
	int x,y;
	public int LoadNextStage() {
		int stageDelay = 0;

// Wake Up
		switch(currStage){
		case 0:
			// Eyes
			manager.Scale(6, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 1000, 0, 0);
			manager.Scale(7, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 1000, 0, 0);

			// Arms
			x = manager.aObjs[2].idleOffset[0];
			y = manager.aObjs[2].idleOffset[1];
			manager.Translate(2, x, x, y+10, y, 800, 100, 0);
			x = manager.aObjs[3].idleOffset[0];
			y = manager.aObjs[3].idleOffset[1];
			manager.Translate(3, x, x, y+5, y, 800, 100, 0);

			// Head
			manager.Rotate(0, 4f, 0, 0, manager.aObjs[0].mBmp.getHeight(), 800, 300, 0);
			x = manager.aObjs[0].idleOffset[0];
			y = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x+5, x, y, y, 500, 500, 0);
		break;
		case 1:
		case 2:
			stageDelay = 200;
			manager.Scale(6, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 400/currStage, 0, currStage-1);
			manager.Scale(7, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 400/currStage, 0, currStage-1);
			manager.Translate(0, x, x, y, y, 2, 2, 0);
		break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
