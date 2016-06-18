package com.dev47apps.dp.anim;

public class LookDown extends DroidPalAnim {

	int x1,y1, x2,y2, z;

	public int LoadNextStage() {
		int stageDelay = 0;

		switch(currStage){
		case 0:
			// Antenas
			manager.Scale(8, 1f, 1f, 1f, 1.1f, 1f, 1f, 200, 0, 0);
			manager.Scale(9, 1f, 1f, 1f, 1.1f, 1f, 1f, 200, 0, 0);

			manager.Rotate(8, 0,  5, 0, manager.aObjs[8].mBmp.getHeight(), 200, 0, 0);
			manager.Rotate(9, 0, -5, 0, manager.aObjs[9].mBmp.getHeight(), 200, 0, 0);

			// HEAD Down a bit
			x1 = manager.aObjs[0].idleOffset[0];
			y1 = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x1, x1, y1, y1+2, 200, 0, 0);

			z = manager.aObjs[6].mBmp.getHeight()/2;
			break;
		case 1:	// EYES Down
			x1 = manager.aObjs[6].idleOffset[0];
			y1 = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, x1, x1+3, y1, y1 + z, 600, 0, 1);

			x2 = manager.aObjs[7].idleOffset[0];
			y2 = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, x2, x2-3, y2, y2 + z, 600, 0, 1);
			break;

		case 2: // Eyes back up
			stageDelay = 1500;

			manager.Translate(6, x1+3, x1, y1 + z, y1, 500, 0, 2);
			manager.Translate(7, x2-3, x2, y2+z, y2, 500, 0, 2);
			break;
		case 3: // head + ant
			manager.Scale(8, 1f, 1f, 1.1f, 1f, 1f, 1f, 100, 0, 0);
			manager.Scale(9, 1f, 1f, 1.1f, 1f, 1f, 1f, 100, 0, 0);

			manager.Rotate(8,  5, 0, 0, manager.aObjs[8].mBmp.getHeight(), 100, 0, 0);
			manager.Rotate(9, -5, 0, 0, manager.aObjs[9].mBmp.getHeight(), 100, 0, 0);

			x1 = manager.aObjs[0].idleOffset[0];
			y1 = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x1, x1, y1+2, y1, 100, 0, 2);
			break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}

}
