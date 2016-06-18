package com.dev47apps.dp.anim;

public class ShakeHead extends DroidPalAnim {
	int x1,y1, x2,y2, x3,y3, x4,y4, z;
	int count;

	public int LoadNextStage() {
		int stageDelay = 0;
		if (currStage == 0){ // Begin moving eyes + ant
			count  = (manager.getRand(4) + 3);
			z = manager.aObjs[6].mBmp.getWidth();

			manager.Scale(6, 1f, 1f, 1f, 0.4f, 0.5f, 0, 200, 0, 0);	// Eyes
			manager.Scale(7, 1f, 1f, 1f, 0.4f, 0.5f, 0, 200, 0, 0);

			x1 = manager.aObjs[6].idleOffset[0];
			y1 = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, x1, x1-z, y1, y1, 400, 500, 0);

			x2 = manager.aObjs[7].idleOffset[0];
			y2 = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, x2, x2-z, y2, y2, 400, 500, 0);

			manager.Scale(8, 1f, 1f, 1f, 0.9f, 1f, 1f, 400, 500, 0); // Antentas
			manager.Scale(9, 1f, 1f, 1f, 1.1f, 0f, 1f, 400, 500, 0);

			x3 = manager.aObjs[8].idleOffset[0];
			y3 = manager.aObjs[8].idleOffset[1];
			manager.Translate(8, x3, x3-2, y3, y3, 1, 500, 0);

			x4 = manager.aObjs[9].idleOffset[0];
			y4 = manager.aObjs[9].idleOffset[1];
			manager.Translate(9, x4, x4+2, y4, y4, 1, 500, 0);
		} else if (currStage == count) { // Last one, move back to idle pos
			manager.Scale(6, 1f, 1f, 0.4f, 1f, 0.5f, 0, 500, 500, 0);
			manager.Scale(7, 1f, 1f, 0.4f, 1f, 0.5f, 0, 500, 500, 0);

			manager.Translate(6, x1+z, x1, y1, y1, 300, 0, 0);
			manager.Translate(7, x2+z, x2, y2, y2, 300, 0, 0);

			manager.Scale(8, 1f, 1f, 1.1f, 1f, 1f, 1f, 300, 0, 0);
			manager.Scale(9, 1f, 1f, 0.9f, 1f, 0f, 1f, 300, 0, 0);
			manager.Translate(8, x3, x3, y3, y3, 1, 0, 0);
			manager.Translate(9, x4, x4, y4, y4, 1, 0, 0);
		} else if (currStage > count){
			stageDelay = -1; // END
		} else { // Transitional - shake left <-> right
			if (currStage % 2 == 0){
				manager.Scale(6, 1f, 1f, 0.4f, 0.4f, 0.5f, 0, 1, 0, 0);
				manager.Scale(7, 1f, 1f, 0.4f, 0.4f, 0.5f, 0, 1, 0, 0);
				manager.Translate(6, x1+z, x1-z, y1, y1, 300, 0, 0);
				manager.Translate(7, x2+z, x2-z, y2, y2, 300, 0, 0);

				manager.Scale(8, 1f, 1f, 1.1f, 0.9f, 1f, 1f, 300, 0, 0);
				manager.Scale(9, 1f, 1f, 0.9f, 1.1f, 0f, 1f, 300, 0, 0);
				manager.Translate(8, x3+2, x3, y3, y3, 1, 0, 0);
				manager.Translate(9, x4-2, x4, y4, y4, 1, 0, 0);
			} else {
				manager.Scale(6, 1f, 1f, 0.4f, 0.4f, 0.5f, 0, 1, 0, 0);
				manager.Scale(7, 1f, 1f, 0.4f, 0.4f, 0.5f, 0, 1, 0, 0);

				manager.Translate(6, x1-z, x1+z, y1, y1, 300, 0, 0);
				manager.Translate(7, x2-z, x2+z, y2, y2, 300, 0, 0);

				manager.Scale(8, 1f, 1f, 0.9f, 1.1f, 1f, 1f, 300, 0, 0);
				manager.Scale(9, 1f, 1f, 1.1f, 0.9f, 0f, 1f, 300, 0, 0);
				manager.Translate(8, x3-2, x3+2, y3, y3, 1, 0, 0);
				manager.Translate(9, x4+2, x4-2, y4, y4, 1, 0, 0);
			}
		}
		currStage++;
		return stageDelay;
	}

}
