package com.dev47apps.dp.anim;

public class LookUp extends DroidPalAnim {

	int x1,y1, x2,y2, z;
	public int LoadNextStage() {
		int stageDelay = 0;

		switch(currStage){
		case 0:
			// Antenas
			manager.Scale(8, 1f, 1f, 1.0f, 0.8f, 1f, 1f, 200, 0, 0);
			manager.Scale(9, 1f, 1f, 1.0f, 1.3f, 1f, 1f, 200, 0, 0);

			manager.Rotate(8, 0, 10, 0, manager.aObjs[8].mBmp.getHeight(), 200, 0, 0);
			manager.Rotate(9, 0, 30, 0, manager.aObjs[9].mBmp.getHeight(), 200, 0, 0);

			// HEAD Up a bit
			x1 = manager.aObjs[0].idleOffset[0];
			y1 = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x1, x1, y1, y1-5, 200, 0, 0);

			z = manager.aObjs[6].mBmp.getHeight();
			break;
		case 1: // Eyes up
			x1 = manager.aObjs[6].idleOffset[0];
			y1 = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, x1, x1-3, y1, y1 - z, 400, 0, 1);

			x2 = manager.aObjs[7].idleOffset[0];
			y2 = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, x2, x2-1, y2, y2 - z +5 , 400, 0, 1);
			break;
		case 2: // Eyes back down
			stageDelay = 1500;

			x1 = manager.aObjs[6].idleOffset[0];
			y1 = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, x1-3, x1, y1-z, y1, 400, 0, 1);

			x2 = manager.aObjs[7].idleOffset[0];
			y2 = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, x2-1, x2, y2-z +5, y2, 400, 0, 1);
			break;
		case 3:
			// Antenas
			manager.Scale(8, 1f, 1f, 0.8f, 1f, 1f, 1f, 100, 0, 0);
			manager.Scale(9, 1f, 1f, 1.3f, 1f, 1f, 1f, 100, 0, 0);

			manager.Rotate(8, 10, 0, 0, manager.aObjs[8].mBmp.getHeight(), 100, 0, 0);
			manager.Rotate(9, 30, 0, 0, manager.aObjs[9].mBmp.getHeight(), 100, 0, 0);

			// HEAD
			x1 = manager.aObjs[0].idleOffset[0];
			y1 = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x1, x1, y1-5, y1, 100, 0, 0);
			break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
