package com.dev47apps.dp;

import com.dev47apps.dp.anim.*;

public class AnimList {
	Object animList[][];

	AnimList(){
		animList = new Object[][]{
/* GENERIC*/{ DonutEat.class, LookUp.class, TabletLol.class },
/* REACT  */{ LookDown.class, RubStomach.class},
/* ANGRY  */{ ShakeHead.class, TheLook.class, RubEye.class  },
/* LEAVE  */{ Leave1.class },
/* SLEEP  */{ Sleep1.class, Sleep2.class, Sleep3.class },
/* GREET  */{ WaveHello.class, Jump.class, AntWiggle.class, Blink.class, Wink.class },

		};
	}
	DroidPalAnim GetRandomAnim(int cat){
		int i = (int)( Math.random() * 128 ) % animList[cat].length;
		return GetInstance(animList[cat][i]);
	}

	DroidPalAnim GetIndexedAnim(int cat, int index){
		return GetInstance(animList[cat][index]);
	}

	@SuppressWarnings("unchecked")
	DroidPalAnim GetInstance(Object obj){
		Object ret = null;
		try {
			ret =  ((Class<DroidPalAnim>) obj).getDeclaredConstructors()[0].newInstance();
		} catch (Exception e) {
			DroidPalWP.log("Error (GetInstance): " + e.getMessage());
		}
		return (DroidPalAnim)ret;
	}
}
