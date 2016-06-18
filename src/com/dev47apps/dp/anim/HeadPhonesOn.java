package com.dev47apps.dp.anim;

public class HeadPhonesOn extends DroidPalAnim {
	public int putOn = 1;

	public HeadPhonesOn(int putOn){
		super();
		this.putOn = putOn;
	}

	public int LoadNextStage(){
		int stageDelay = 0;

		if (putOn == 1){
			if(currStage == 0) {
				manager.AddRelativeObj(0, 10, -50,
						(manager.aObjs[0].mBmp.getHeight() - manager.aObjs[10].mBmp.getHeight() - 32)/2);

				int x = manager.aObjs[10].idleOffset[0];
				int y = manager.aObjs[10].idleOffset[1];
				manager.Translate(10, x, x, y-64, y, 300, 0, 1);
				manager.headPhOn = true;
			}
			else stageDelay = -1;
		}
		else {
			if(currStage == 0 && manager.headPhOn) {
				int x = manager.aObjs[10].idleOffset[0];
				int y = manager.aObjs[10].idleOffset[1];
				manager.Translate(10, x, x, y, y-64, 300, 0, 1);
			} else if(currStage == 1 && manager.headPhOn){
				manager.aObjs[0].PopRelativeObject();
				manager.Translate(10, -1024,-1024, -1024, -1024, 1, 0, 0);
				manager.headPhOn = false;
			}
			else stageDelay = -1;
		}

		currStage++;
		return stageDelay;
	}
}
