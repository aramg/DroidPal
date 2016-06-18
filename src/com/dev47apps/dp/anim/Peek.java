package com.dev47apps.dp.anim;

public class Peek extends DroidPalAnim {

	/* Peek (head) from a random side
	 * 0 = bottom; 1 = left; 2 = up; 3 = right
	 */
	int randSide, outX, inX, outY, inY;

	public int LoadNextStage(){
		int stageDelay = 0;
		switch(currStage)
		{
		case 0:
			randSide = manager.getRand(4);
			manager.Rotate(1, 0, randSide*90, 0, 0, 1, 0, 0);

			int a = manager.aObjs[1].idleOffset[0]; // Body Idle
			int b = manager.aObjs[0].mBmp.getHeight() + 10; // Head size

			switch (randSide){
			case 0: // bottom
				inX = outX = manager.getRand(manager.mScreenWidth - a);
				inY = manager.mScreenHeight - b;
				outY = manager.mScreenHeight + b + 8;
				break;
			case 1:// left
				inY = outY = manager.getRand(manager.mScreenHeight - a);
				inX = 0 + b;
				outX = 0 - b - 8;
				break;
			case 2: // top
				inX = outX = manager.getRand(manager.mScreenWidth) + a;
				inY = 0 + b;
				outY = 0 - b - 8;
				break;
			case 3:  // right
				inY = outY = manager.getRand(manager.mScreenHeight) + a;
				inX = manager.mScreenWidth - b;
				outX = manager.mScreenWidth + b + 8;
				break;
			}
		/*	DroidPalWP.log("randSide=" + randSide
					+ " rotate to" + randSide*90
					+ " inX="+inX+", outX="+outX
					+ " inY="+inY+", outY="+outY);*/
			manager.Translate(1, outX, inX, outY, inY, 500, 0, 2);
			break;
		case 1:
			stageDelay = 1500;
			manager.Rotate(1, randSide*90, randSide*90, 0, 0, 2, 0, 0);
			manager.Translate(1, inX, outX, inY, outY, 300, 0, 2);
		break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
