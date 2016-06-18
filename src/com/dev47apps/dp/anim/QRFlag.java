package com.dev47apps.dp.anim;

import com.dev47apps.dp.AnimManager;
import com.dev47apps.dp.R;

public class QRFlag extends DroidPalAnim {
	@Override
	public void SetManager(AnimManager ref){
		super.SetManager(ref);
		manager.CreateDrawable(AnimManager.aEXT1);
		manager.LoadBitmap(AnimManager.aEXT1, R.drawable.xflag);
	}

	int aX, aY, aW, hW, hH, hX, hY;
	public int LoadNextStage(){
		int stageDelay = 0;
		switch(currStage){
		case 0: // Look to the side, move arm out
	manager.AddRelativeObj(3, AnimManager.aEXT1, -manager.aObjs[11].mBmp.getWidth(),
				manager.aObjs[3].mBmp.getHeight() - manager.aObjs[11].mBmp.getHeight()*3/4);

			aX = manager.aObjs[3].idleOffset[0];
			aY = manager.aObjs[3].idleOffset[1];
			aW = manager.aObjs[3].mBmp.getWidth()/2;
			manager.Rotate(3, 0, 60, aW, 8, 1000, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);

			/*			hW = manager.aObjs[6].idleOffset[0];// R Eye
			hH = manager.aObjs[6].idleOffset[1];
			manager.Translate(6, hW, hW+16, hH, hH+4, 800, 0, 0);

			hX = manager.aObjs[7].idleOffset[0];// L Eye
			hY = manager.aObjs[7].idleOffset[1];
			manager.Translate(7, hX, hX+16, hY, hY+4, 800, 0, 0);

			manager.Translate(AnimManager.aEXT1, -128, -128, 0, 0, 1, 0, 0);
			manager.Rotate(AnimManager.aEXT1, 0, -10, 0, 0, 1, 1, 0);
		break;
		case 1:
			manager.AddRelativeObj(3, AnimManager.aEXT1, 32-manager.aObjs[AnimManager.aEXT1].mBmp.getWidth(),
					manager.aObjs[3].mBmp.getHeight() - manager.aObjs[11].mBmp.getHeight()*3/4);
			manager.Translate(6, hW+16, hW+8, hH+4, hH+4, 800, 200, 0);
			manager.Translate(7, hX+16, hX+8, hY+4, hY+4, 800, 200, 0);
			manager.Rotate(3, -60, 40, aW, 8, 800, 0, 0);
			manager.Translate(3, aX, aX, aY, aY, 2, 0, 0);*/
		break;
		default: stageDelay = -1;
		}

		currStage++;
		return stageDelay;
	}
}
