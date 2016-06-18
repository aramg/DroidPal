package com.dev47apps.dp.anim;

public class Sleep1 extends DroidPalAnim {
	public int LoadNextStage() {
		int x,y;
		int stageDelay = 0;

// Only 1 stage, stays 'asleep'
		switch(currStage){
		case 0: // Need to pin the whole body in case the screen is flipped while asleep
			x = manager.aObjs[1].idleOffset[0];
			y = manager.aObjs[1].idleOffset[1];
			manager.Translate(1, x, x, y, y, 2, 0, 0);
		break;
		case 1:
			// Eyes
			manager.Scale(6, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 1, 0, 0);
			manager.Scale(7, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 1, 0, 0);

			// Head
			manager.Rotate(0, 0, -2f, 0, manager.aObjs[0].mBmp.getHeight(), 1, 0, 0);

			x = manager.aObjs[0].idleOffset[0];
			y = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x, x, y, y+5, 2, 0, 0);

			// Arms
			x = manager.aObjs[2].idleOffset[0];
			y = manager.aObjs[2].idleOffset[1];
			manager.Translate(2, x, x, y, y+10, 2, 0, 0);
			x = manager.aObjs[3].idleOffset[0];
			y = manager.aObjs[3].idleOffset[1];
			manager.Translate(3, x, x, y, y+5, 2, 0, 0);
			break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
