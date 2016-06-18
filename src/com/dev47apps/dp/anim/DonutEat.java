package com.dev47apps.dp.anim;

import com.dev47apps.dp.AnimManager;
import com.dev47apps.dp.R;

public class DonutEat extends DroidPalAnim {
	@Override
	public void SetManager(AnimManager ref){
		super.SetManager(ref);
		manager.CreateDrawable(AnimManager.aEXT1);
		manager.LoadBitmap(AnimManager.aEXT1, R.drawable.donut1);
		manager.CreateDrawable(AnimManager.aEXT2);
		manager.LoadBitmap(AnimManager.aEXT2, R.drawable.donut_m);
	}

	int hW, hH, hX, hY, aX, aY, aW;
	@Override
	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0: // Look to the side, move arm out
			aX = manager.aObjs[3].idleOffset[0];
			aY = manager.aObjs[3].idleOffset[1];
			aW = manager.aObjs[3].mBmp.getWidth()/2;
			manager.Rotate(3, 0, -30, aW, 8, 1000, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			hW = manager.aObjs[6].idleOffset[0];// R Eye
			hH = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, hW, hW+16, hH, hH+4, 800, 0, 0);

			hX = manager.aObjs[7].idleOffset[0];// L Eye
			hY = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, hX, hX+16, hY, hY+4, 800, 0, 0);

			manager.Translate(AnimManager.aEXT1, -128, -128, 0, 0, 1, 0, 0);
		break;
		case 1:
			manager.AddRelativeObj(3, AnimManager.aEXT1, -32, manager.aObjs[3].mBmp.getHeight() - 32);
			manager.Translate(6, hW+16, hW, hH+4, hH, 800, 200, 0);
			manager.Translate(7, hX+16, hX, hY+4, hY, 800, 200, 0);
			manager.Rotate(3, -30, 0, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;
		case 2: // Bring arm+donut up to 80deg, to mouth
			manager.Rotate(3, 0, 80, aW, 8, 1000, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			hW = manager.aObjs[0].mBmp.getWidth(); // Move head up
			hH = manager.aObjs[0].mBmp.getHeight();
			hX = manager.aObjs[0].idleOffset[0];
			hY = manager.aObjs[0].idleOffset[1];
			manager.Rotate(0, 0, 5, hW, hH, 600, 500, 0);
			manager.Translate(0, hX, hX, hY, hY-16, 500, 0, 0);
		break;
		case 3: // Move head back down - bite!
			manager.Rotate(0, 5, 0, hW, hH, 600, 0, 0);
			manager.Translate(0, hX, hX, hY-16, hY, 500, 0, 0);
		break;

		case 4: // Repace donut in arm with bitten version; + add mouth donut
			manager.LoadBitmap(AnimManager.aEXT1, R.drawable.donut2);
			manager.AddRelativeObj(0, AnimManager.aEXT2,
					hW/2 - manager.aObjs[AnimManager.aEXT2].mBmp.getWidth()/2, hH - 8);

			manager.Rotate(3, 80, 70, aW, 8, 400, 0, 0); // Move arm down a bit
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;

		case 5: case 7: // Chew :)
			manager.Rotate(0, 0, 5, hW, hH, 300, 0, 0);
			manager.Translate(0, hX, hX, hY, hY-16, 300, 0, 0);
		break;
		case 6: case 8:
			manager.Rotate(0, 5, 0, hW, hH, 300, 0, 0);
			manager.Translate(0, hX, hX, hY-16, hY, 300, 0, 0);
		break;

		case 9: // Arm+Head back up,
			manager.Rotate(3, 70, 80, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			manager.Rotate(0, 0, 5, hW, hH, 500, 100, 0);
			manager.Translate(0, hX, hX, hY, hY-16, 500, 0, 0);
		break;
		case 10: // Move head back down - bite!
			manager.Rotate(0, 5, 0, hW, hH, 600, 0, 0);
			manager.Translate(0, hX, hX, hY-16, hY, 500, 0, 0);
		break;
		case 11: // Repace donut in arm with next version; Move arm down a bit
			manager.LoadBitmap(AnimManager.aEXT1, R.drawable.donut3);
			manager.Rotate(3, 80, 70, aW, 8, 400, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;

		case 12: case 14: case 16: // Chew :)
			manager.Rotate(0, 0, 5, hW, hH, 300, 0, 0);
			manager.Translate(0, hX, hX, hY, hY-16, 300, 0, 0);
		break;
		case 13: case 15: case 17:
			manager.Rotate(0, 5, 0, hW, hH, 300, 0, 0);
			manager.Translate(0, hX, hX, hY-16, hY, 300, 0, 0);
		break;

		case 18: // Arm+Head back up,
			manager.Rotate(3, 70, 80, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			manager.Rotate(0, 0, 5, hW, hH, 500, 100, 0);
			manager.Translate(0, hX, hX, hY, hY-16, 500, 0, 0);
		break;
		case 19: // Move head back down - bite!
			manager.Rotate(0, 5, 0, hW, hH, 600, 0, 0);
			manager.Translate(0, hX, hX, hY-16, hY, 500, 0, 0);
		break;
		case 20: // remove donut from arm; Move arm down a bit
			manager.aObjs[3].PopRelativeObject();
			manager.aObjs[AnimManager.aEXT1] = null;
			manager.Rotate(3, 80, 60, aW, 8, 400, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;

		case 21: case 23: case 25: case 27: // Chew :)
			manager.Rotate(0, 0, 5, hW, hH, 300, 0, 0);
			manager.Translate(0, hX, hX, hY, hY-16, 300, 0, 0);
		break;
		case 22: case 24: case 26: case 28:
			manager.Rotate(0, 5, 0, hW, hH, 300, 0, 0);
			manager.Translate(0, hX, hX, hY-16, hY, 300, 0, 0);
		break;

		case 29: // Look down
			hW = manager.aObjs[6].idleOffset[0];
			hH = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, hW, hW, hH, hH+8, 800, 0, 0);

			hX = manager.aObjs[7].idleOffset[0];
			hY = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, hX, hX, hY, hY+8, 800, 0, 0);
		break;
		case 30: // Arm back up, clean the mouth :)
			manager.Rotate(3, 70, 90, aW, 8, 400, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;
		case 31: // arm down
			stageDelay = 200;
			manager.aObjs[0].PopRelativeObject();
			manager.aObjs[AnimManager.aEXT2] = null;
			manager.Rotate(3, 90, 0, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			manager.Translate(6, hW, hW, hH+8, hH, 400, 0, 0);
			manager.Translate(7, hX, hX, hY+8, hY, 400, 0, 0);
		break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
