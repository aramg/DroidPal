package com.dev47apps.dp.anim;

public class RubStomach extends DroidPalAnim {

	int x,y, z;

	@Override
	public int LoadNextStage() {
		int stageDelay = 0;


		switch(currStage){
		case 0:
			// EYES
			x = manager.aObjs[6].idleOffset[0];
			y = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, x, x+3, y, y + 5, 200, 0, 0);

			x = manager.aObjs[7].idleOffset[0];
			y = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, x, x-3, y, y + 5, 200, 0, 0);
			break;
		case 1:
			x = manager.aObjs[3].idleOffset[0] - 5;
			y = manager.aObjs[3].idleOffset[1] + 5;
			z = manager.aObjs[3].mBmp.getWidth();

			manager.Rotate(3, 0, 50, z, 3, 500, 0, 0);
			manager.Translate(3, x + 5, x, y -5, y, 1, 0, 0);
			break;
		case 2:
		case 4:
		case 6:
			manager.Rotate(3, 50, 20, z, 3, 100, 0, 0);
			manager.Translate(3, x, x, y, y, 1, 0, 0);
			break;
		case 3:
		case 5:
		case 7:
			manager.Rotate(3, 20, 50, z, 3, 100, 0, 0);
			manager.Translate(3, x, x, y, y, 1, 0, 0);
			break;
		case 8:
			manager.Rotate(3, 50, 0, z, 3, 500, 0, 0);
			manager.Translate(3, x, x+5, y, y-5, 1, 0, 0);

			x = manager.aObjs[6].idleOffset[0];
			y = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, x+3, x, y+5, y, 800, 0, 2);

			x = manager.aObjs[7].idleOffset[0];
			y = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, x-3, x, y+5, y, 800, 0, 2);
			break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}

}
