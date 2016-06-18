package com.dev47apps.dp.anim;

public class Wink extends DroidPalAnim {
	int eye;
	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0:
			eye = ( (int)(Math.random()*10) % 2 ) + 6;

			manager.Scale(eye, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 200, 0, 0);
			break;
		case 1:
			stageDelay = 200;
			manager.Scale(eye, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 400, 0, 0);
			break;
		default: stageDelay = -1;
		}
		if (stageDelay != -1)
		manager.Translate(eye,
				manager.aObjs[eye].idleOffset[0], manager.aObjs[eye].idleOffset[0],
				manager.aObjs[eye].idleOffset[1], manager.aObjs[eye].idleOffset[1], 1, 0, 0);
		currStage++;
		return stageDelay;
	}
}
