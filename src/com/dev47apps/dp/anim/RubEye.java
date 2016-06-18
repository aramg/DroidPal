package com.dev47apps.dp.anim;

import com.dev47apps.dp.AnimManager;

public class RubEye extends DroidPalAnim {
	int x,y,c;
	public int LoadNextStage(){
		int stageDelay = 0;
		switch(currStage){
		case 0: // Head down, move right arm up, close eye
			manager.Scale(6, 1f, 0.9f, 1f, 0.3f, 0.5f, 0.5f, 300, 0, 0);
			manager.Scale(7, 1f,   1f, 1f, 0.7f, 0.5f, 0.5f, 300, 0, 0);
			manager.Translate(0,
					manager.aObjs[0].idleOffset[0], manager.aObjs[0].idleOffset[0],
					manager.aObjs[0].idleOffset[1], manager.aObjs[0].idleOffset[1]+4, 500, 0, 0);

			x = manager.aObjs[2].idleOffset[0];
			y = manager.aObjs[2].idleOffset[1];
			c = manager.aObjs[2].mBmp.getWidth()/2;

			manager.aObjs[AnimManager.aEXT1] = manager.aObjs[2];
			manager.aObjs[2] = null; // Hand must be above the eye..

			 manager.Rotate(AnimManager.aEXT1, 0, -120, c,  10, 800, 0, 0);
		break;
		case 1: case 3: case 5:
			manager.Rotate(AnimManager.aEXT1, -120, -110, c,  10, 300, 0, 0); break;
		case 2: case 4:
			manager.Rotate(AnimManager.aEXT1, -110, -120, c,  10, 200, 0, 0); break;
		case 6:
			manager.Rotate(AnimManager.aEXT1, -120, 0, c,  10, 900, 0, 0); break;
		case 7:
			manager.Scale(6, .9f, 1f, .3f, 1f, 0.5f, 0.5f, 500, 0, 0);
			manager.Scale(7, 1f,  1f, .7f, 1f, 0.5f, 0.5f, 500, 0, 0);
			manager.Translate(0,
					manager.aObjs[0].idleOffset[0], manager.aObjs[0].idleOffset[0],
					manager.aObjs[0].idleOffset[1]+4, manager.aObjs[0].idleOffset[1], 900, 0, 0);
		break;
		default:
			manager.aObjs[2] = manager.aObjs[AnimManager.aEXT1];
			manager.aObjs[AnimManager.aEXT1] = null;
			stageDelay = -1;
		}

		if (stageDelay != -1) manager.Translate(AnimManager.aEXT1, x, x, y, y, 1, 0, 0); // Pin the arm

		currStage++;
		return stageDelay;
	}
}
