package com.dev47apps.dp.anim;

public class Enter extends DroidPalAnim {

	//int rEye[], lEye[];
	int x,y;
	boolean hideHp;
	public Enter(boolean hideHp){
		super();
		this.hideHp = hideHp;
	}

	public int LoadNextStage(){
		int stageDelay = 0;


		switch(currStage)
		{
		case 0: // Peek
			x = manager.aObjs[1].idleOffset[0];
			y = manager.aObjs[1].idleOffset[1];
			manager.Translate(1, x, x, y + manager.mScreenHeight,
					manager.mScreenHeight - 50, 500, 0, 1);

			if (hideHp) manager.Translate(10, -1024,-1024, -1024, -1024, 1, 0, 0);// headphones
		break;
/*		case 1: // NULL stage for delay
			stageDelay = 1000;
			rEye = manager.GetCurrentPosition(6);
			lEye = manager.GetCurrentPosition(7);
			manager.Translate(6, rEye[0], rEye[0], rEye[1], rEye[1], 1, 0, 0);
			break;
		case 2: case 4: // blink
			manager.Scale(6, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 100, 0, 0);
			manager.Scale(7, 1f, 1f, 1f, 0.1f, 0.5f, 0.5f, 100, 0, 0);
			manager.Translate(6, rEye[0], rEye[0], rEye[1], rEye[1], 1, 0, 0);
			manager.Translate(7, lEye[0], lEye[0], lEye[1], lEye[1], 1, 0, 0);
			break;
		case 3: case 5: // blink
			manager.Scale(6, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 100, 0, 0);
			manager.Scale(7, 1f, 1f, 0.1f, 1f, 0.5f, 0.5f, 100, 0, 0);
			manager.Translate(6, rEye[0], rEye[0], rEye[1], rEye[1], 1, 0, 0);
			manager.Translate(7, lEye[0], lEye[0], lEye[1], lEye[1], 1, 0, 0);
			break;
		case 6:
			stageDelay = 1000;
FIXME*/case 1:	//stageDelay = 1000;
	manager.Translate(1, x, x, manager.mScreenHeight - 50, y, 500, 0, 2);
			break;
		default: stageDelay = -1;
		}

		currStage++;
		return stageDelay;
	}
}
