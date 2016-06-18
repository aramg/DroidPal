package com.dev47apps.dp.anim;

import com.dev47apps.dp.AnimManager;
import com.dev47apps.dp.R;

public class TabletLol extends DroidPalAnim {
	@Override
	public void SetManager(AnimManager ref){
		super.SetManager(ref);
		manager.CreateDrawable(AnimManager.aEXT1);
		manager.LoadBitmap(AnimManager.aEXT1, R.drawable.tablet_back);
	}

	int hW, hH, hX, hY, aX, aY, aW,  a2X, a2Y, a2W;
	public int LoadNextStage() {
		int stageDelay = 0;
		switch(currStage){
		case 0: // Look to the side, move arm out
			aX = manager.aObjs[3].idleOffset[0];
			aY = manager.aObjs[3].idleOffset[1];
			aW = manager.aObjs[3].mBmp.getWidth()/2;
			manager.Rotate(3, 0, -60, aW, 8, 1000, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			hW = manager.aObjs[6].idleOffset[0];// R Eye
			hH = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, hW, hW+16, hH, hH+4, 800, 0, 0);

			hX = manager.aObjs[7].idleOffset[0];// L Eye
			hY = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, hX, hX+16, hY, hY+4, 800, 0, 0);

			manager.Translate(AnimManager.aEXT1, -128, -128, 0, 0, 1, 0, 0);
			manager.Rotate(AnimManager.aEXT1, 0, -10, 0, 0, 1, 1, 0);
		case 1:
			manager.AddRelativeObj(3, AnimManager.aEXT1, 32-manager.aObjs[AnimManager.aEXT1].mBmp.getWidth(),
					manager.aObjs[3].mBmp.getHeight() - manager.aObjs[AnimManager.aEXT1].mBmp.getHeight()*3/4);
			manager.Translate(6, hW+16, hW+8, hH+4, hH+4, 800, 200, 0);
			manager.Translate(7, hX+16, hX+8, hY+4, hY+4, 800, 200, 0);
			manager.Rotate(3, -60, 40, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;
		case 2: // Move the other hand over
			a2X = manager.aObjs[2].idleOffset[0];
			a2Y = manager.aObjs[2].idleOffset[1];
			a2W = manager.aObjs[2].mBmp.getWidth()/2;
			manager.Rotate(2, 0, -80, a2W, 8, 1000, 0, 0);
			manager.Translate(2, a2X, a2X, a2Y, a2Y, 1, 0, 0);
		break;
		case 3: case 5: case 7: // Squiggle Squiggle
			manager.Rotate(2, -80, -60, a2W, 8, 500, 0, 0);
			manager.Translate(2, a2X, a2X, a2Y, a2Y, 1, 0, 0);
		break;
		case 4: case 6: case 8:
			manager.Rotate(2, -60, -80, a2W, 8, 600, 0, 0);
			manager.Translate(2, a2X, a2X, a2Y, a2Y, 1, 0, 0);
		break;

		case 9: // Move the other hand
			manager.Rotate(2, -80, 0, a2W, 8, 800, 0, 0);
			manager.Translate(2, a2X, a2X, a2Y, a2Y, 1, 0, 0);
		break;
		case 10: // Flip tablet
			manager.LoadBitmap(AnimManager.aEXT1, R.drawable.tablet_side);
			manager.Translate(2, a2X, a2X, a2Y, a2Y, 50, 0, 0);
		break;
		case 11:
			manager.LoadBitmap(AnimManager.aEXT1, R.drawable.tablet_front);
			manager.Translate(2, a2X, a2X, a2Y, a2Y, 50, 0, 0);
		break;

		case 12: // Eyes back and scale
			stageDelay = 500;
			manager.Translate(6, hW+8, hW, hH+4, hH, 500, 0, 0);
			manager.Translate(7, hX+8, hX, hY+4, hY, 500, 0, 0);
		break;
		case 13: case 15:
			manager.Scale(6, 1f, 1f, 1f, 1.4f, 0.5f, 0.5f, 200, 0, 2);
			manager.Translate(6, hW, hW, hH, hH, 1, 0, 0);
			manager.Scale(7, 1f, 1f, 1f, 1.4f, 0.5f, 0.5f, 200, 0, 2);
			manager.Translate(7, hX, hX, hY, hY, 1, 0, 0);
		break;
		case 14: case 16:
			manager.Scale(6, 1f, 1f, 1.4f, 1f, 0.5f, 0.5f, 300, 0, 2);
			manager.Translate(6, hW, hW, hH, hH, 1, 0, 0);
			manager.Scale(7, 1f, 1f, 1.4f, 1f, 0.5f, 0.5f, 300, 0, 2);
			manager.Translate(7, hX, hX, hY, hY, 1, 0, 0);
		break;

		case 17: // put tablet away
			stageDelay = 1000;
			manager.Rotate(3, 40, -60, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);
		break;
		case 18:
			manager.aObjs[3].PopRelativeObject();
			manager.aObjs[AnimManager.aEXT1] = null;
			manager.Rotate(3, -60, 0, aW, 8, 500, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 1, 0, 0);
		break;
		default: stageDelay = -1;
		}
		currStage++;
		return stageDelay;
	}
}
