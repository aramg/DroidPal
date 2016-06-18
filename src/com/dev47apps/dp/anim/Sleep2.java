package com.dev47apps.dp.anim;

public class Sleep2 extends DroidPalAnim {
	public int LoadNextStage() {
		int stageDelay = 1000;
		int x,y;

		switch(currStage){
		case 0:
			// Eyes + Arms pin to Sleep1 positions
			manager.Scale(6, 1f, 1f, 0.1f, 0.1f, 0.5f, 0.5f, 1, 0, 0);
			manager.Scale(7, 1f, 1f, 0.1f, 0.1f, 0.5f, 0.5f, 1, 0, 0);

			x = manager.aObjs[2].idleOffset[0];
			y = manager.aObjs[2].idleOffset[1];
			manager.Translate(2, x, x, y+10, y+10, 2, 0, 0);
			x = manager.aObjs[3].idleOffset[0];
			y = manager.aObjs[3].idleOffset[1];
			manager.Translate(3, x, x, y+5, y+5, 2, 0, 0);

			// Rotate head to other side
			manager.Rotate(0, -2f, 4f, 0, manager.aObjs[0].mBmp.getHeight(), 800, 300, 0);

			x = manager.aObjs[0].idleOffset[0];
			y = manager.aObjs[0].idleOffset[1];
			manager.Translate(0, x, x+5, y+5, y, 500, 0, 0);
		break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
