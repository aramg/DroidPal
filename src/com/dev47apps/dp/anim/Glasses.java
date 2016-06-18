package com.dev47apps.dp.anim;

public class Glasses extends DroidPalAnim {

	public int LoadNextStage() {
		int stageDelay = 0, x, y;

		switch(currStage){
		case 0:
			manager.Rotate(3, 0, -30, manager.aObjs[3].mBmp.getWidth()/2, 2, 100, 0, 1);

			x = manager.aObjs[3].idleOffset[0];
			y = manager.aObjs[3].idleOffset[1];
			manager.Translate(3, x, x, y, y, 1, 0, 0);
		break;
		case 1:
			stageDelay = 150;
		//	manager.LoadBitmap(10, R.drawable.glasses);
			manager.Rotate(10, 0, 220,
					manager.aObjs[10].mBmp.getWidth(), manager.aObjs[10].mBmp.getHeight()/2, 1000, 0, 0);

			manager.AddRelativeObj(3, 10,
					manager.aObjs[3].mBmp.getWidth()/2 - manager.aObjs[10].mBmp.getWidth()/2,
					manager.aObjs[3].mBmp.getHeight() - manager.aObjs[10].mBmp.getHeight()/2);

			manager.Rotate(3, -30, 100, manager.aObjs[3].mBmp.getWidth()/2, 2, 500, 2000, 1);

			x = manager.aObjs[3].idleOffset[0];
			y = manager.aObjs[3].idleOffset[1];
			manager.Translate(3, x, x, y, y, 2, 2000, 0);
		break;
		/*	case 2:
			manager.Rotate(10, 220, 0,
					manager.aObjs[10].mBmp.getWidth()/2, manager.aObjs[10].mBmp.getHeight()/2, 1000, 0, 0);
			int ret[] = manager.GetCurrentPosition(10);
			manager.Translate(10, ret[0], ret[0], ret[1], ret[1], 1, 0, 0);
			break;
	*/	default: stageDelay = -1;
		}

		currStage ++;
		return stageDelay;
	}

}
