package com.dev47apps.dp;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.dev47apps.dp.anim.DroidPalAnim;

public class AnimManager
{
	public static final int aEXT1 = 11;
	public static final int aEXT2 = 12;
	final int EXTRA_OBJS = 2; // 2

	public DrawableObject aObjs[] = new DrawableObject[11 + EXTRA_OBJS];
	Interpolator aIprs[] = new Interpolator[2];
	AnimationListener aListener;
	Context context;
	public DrawableObject wallPaper;

	DroidPalAnim currentAnim;
	public int mScreenWidth, mScreenHeight;
	int bgColor = 0xFF000000;
	public boolean useBgImage, headPhOn;

	public AnimManager(AnimationListener l, Context c){
		aListener = l;
		context = c;
		mScreenWidth=mScreenHeight=0;
		useBgImage = headPhOn = false;

		// Load the bitmaps
		for (int i = 0; i < aObjs.length-EXTRA_OBJS; i++)
		{
			CreateDrawable(i);
			LoadBitmap(i, R.drawable.a01head + i);
		}

		aIprs[0] = new DecelerateInterpolator();
		aIprs[1] = new AccelerateInterpolator();

		wallPaper = new DrawableObject();
	}

	void LoadAnim(DroidPalAnim anim){
		anim.SetManager(this);
		currentAnim = anim;
	}

	int LoadNextStage(){
		long longestAnim = 0;
		int longestAnimIndex = 0, delay;

		// Clear all the animation sets
		for (int i = 0; i < aObjs.length; i++)
			if (aObjs[i] != null) aObjs[i].animSet = null;

		if ((delay = currentAnim.LoadNextStage()) >= 0)
		{
			for (int i = 0; i < aObjs.length; i++)
			{
				if (aObjs[i] != null && aObjs[i].animSet != null)
				{
					if (aObjs[i].animSet.getDuration() > longestAnim){
						longestAnim = aObjs[i].animSet.getDuration();
						longestAnimIndex = i;
					}
					aObjs[i].Initialize(mScreenWidth, mScreenHeight);
				}
			}

			// So we can catch the very end of this stage
			aObjs[longestAnimIndex].animSet.setAnimationListener(aListener);
		} else {
			currentAnim = null; // done; free up memory
		}
		return delay;
	}

	void SetScreenSize(int w, int h) {
		mScreenWidth = w;
		mScreenHeight = h;
		// Set the idle position of the body manually
		// Everything else is relative to the body!
		int offsetX = w - aObjs[1].mBmp.getWidth() - aObjs[2].mBmp.getWidth() - 2;
		int offsetY = h - aObjs[1].mBmp.getHeight() - aObjs[4].mBmp.getHeight()*2/3;
	//	DroidPalWP.log("Body Idle @: " + offsetX + ", " + offsetY);
		aObjs[1].SetIdlePos(offsetX, offsetY); // BODY
		aObjs[1].RelObjs.clear();
		AddRelativeObj(1, 0, 0, -aObjs[0].mBmp.getHeight()-5); // HEAD
		AddRelativeObj(1, 2, -aObjs[2].mBmp.getWidth(), 1); // R ARM
		AddRelativeObj(1, 3, aObjs[1].mBmp.getWidth(), 1); // L ARM
		AddRelativeObj(1, 4, aObjs[1].mBmp.getWidth()  /4 -aObjs[4].mBmp.getWidth()/2, aObjs[1].mBmp.getHeight() - 2);// R LEG
		AddRelativeObj(1, 5, aObjs[1].mBmp.getWidth()*3/4 -aObjs[5].mBmp.getWidth()/2, aObjs[1].mBmp.getHeight() - 2);//L LEG

		// Relative to the head
		aObjs[0].RelObjs.clear();
		AddRelativeObj(0, 6, aObjs[0].mBmp.getWidth()  /4                           , aObjs[0].mBmp.getHeight()/2);
		AddRelativeObj(0, 7, aObjs[0].mBmp.getWidth()*3/4 - aObjs[7].mBmp.getWidth(), aObjs[0].mBmp.getHeight()/2);

		AddRelativeObj(0, 8, aObjs[0].mBmp.getWidth()  /4 - aObjs[8].mBmp.getWidth(), -aObjs[8].mBmp.getHeight()/4);
		AddRelativeObj(0, 9, aObjs[0].mBmp.getWidth()*3/4                           , -aObjs[9].mBmp.getHeight()/4);
	}

	public void AddRelativeObj(int dst, int rel, int offsetX, int offsetY)
	{
		aObjs[rel].idleOffset[0] = aObjs[dst].idleOffset[0] + offsetX;
		aObjs[rel].idleOffset[1] = aObjs[dst].idleOffset[1] + offsetY;
		aObjs[dst].PushRelativeObject(rel, offsetX, offsetY);
	}

	public void CreateDrawable(int index){
		aObjs[index]= new DrawableObject();
		aObjs[index].Init();

	}

	public void LoadBitmap(int dstIdx, int resid){//, int scaleW, int scaleH){
		aObjs[dstIdx].mBmp = BitmapFactory.decodeResource(context.getResources(), resid);
	//	if (scaleW != 0){
	//		dst.mBmp = Bitmap.createScaledBitmap(dst.mBmp, scaleW, scaleH, false);
	//	}
	}
	public void DeleteDrawable(int i){
		aObjs[i]=null;
	}

	public int[] GetCurrentPosition(int obj){
		float values[] = new float[9];
		aObjs[obj].mTransform.getMatrix().getValues(values);
	//	DroidPalWP.log("$Obj " + i + " X=" + values[2] + " Y=" + values[5]);

		int ret[] = { (int)values[2], (int) values[5] };
		return ret;
	}

	public int getRand(int max){
		return (int) ((Math.random() * 4096) % max);
	}

	// Tests if given coords are within/on top of the bitmap
	boolean IsWithinObj(int obj, float x, float y){
		int val = aObjs[obj].idleOffset[0];
		if (x >= val && x <= (val + aObjs[obj].mBmp.getWidth()))
		{
			val = aObjs[obj].idleOffset[1];
			if (y >= val && y <= val + aObjs[obj].mBmp.getHeight()){
				return true;
			}
		}
		return false;
	}

	// NOT on any bitmap
	boolean IsOnScreen(float x, float y){
		boolean ret = false;

		if (y < aObjs[8].idleOffset[1])      ret = true; // anywhere above the antena
		else if (x < aObjs[2].idleOffset[0]) ret = true; // or left of right hand

		return ret;
	}

	void drawFrame(Canvas c){
		long time = System.currentTimeMillis();
		if (useBgImage){
			c.drawBitmap(wallPaper.mBmp, wallPaper.idleOffset[0], wallPaper.idleOffset[1], null);
		}
		else c.drawColor(bgColor);
		for (DrawableObject obj : aObjs){
			if (obj != null){
				//c.drawBitmap(aObjs[i].mBmp, aObjs[i].idleOffset[0], aObjs[i].idleOffset[1], null);
				if (obj.animSet != null)
					obj.UpdateTransform(time);
				c.drawBitmap(obj.mBmp, obj.mTransform.getMatrix(), null);
			}
		}
	}

	/* Apply Animations */
	public void Translate(int what, int fromX, int toX, int fromY, int toY, int len, int delay, int ipr){
	//	DroidPalWP.log("Translate what="+what+" fromX="+fromX + " toX=" + toX +
	//			" fromY=" + fromY + " toY=" + toY);

		ApplyAnimation(what, len, delay, ipr, new TranslateAnimation(
			Animation.ABSOLUTE, fromX, Animation.ABSOLUTE, toX,
			Animation.ABSOLUTE, fromY, Animation.ABSOLUTE, toY));

		if (len > 1) // Otherwise we're just pinning the object
		for (int i = 0; i < aObjs[what].RelObjs.size(); i++ )
		{
			DrawableObject.RelativeObjectParams p = aObjs[what].RelObjs.get(i);
		//	DroidPalWP.log("* rel obj " + p.index);
			// Dig in recursively
			Translate(p.index, fromX + p.offsetX, toX + p.offsetX,
								fromY + p.offsetY, toY + p.offsetY, len, delay, ipr);
		}
	}

	public void Rotate(int what, float fromDeg, float toDeg, float pivotX, float pivotY,
			int len, int delay, int ipr)
	{
	//	DroidPalWP.log("Rotate fromDeg="+fromDeg + " toDeg=" + toDeg +
	//			" pivotX=" + pivotX + " pivotY="+pivotY);
		ApplyAnimation(what, len, delay, ipr, new RotateAnimation(
				fromDeg, toDeg, pivotX, pivotY));

		for (int i = 0; i < aObjs[what].RelObjs.size(); i++ )
		{
			DrawableObject.RelativeObjectParams p = aObjs[what].RelObjs.get(i);
		//	DroidPalWP.log("* rel obj " + p.index);
			Rotate(p.index, fromDeg, toDeg,
					pivotX - p.offsetX,	pivotY - p.offsetY, len, delay, ipr);
		}
	}

	public void Scale(int what, float fromX, float toX, float fromY, float toY, float pivotX, float pivotY,
			int len, int delay, int ipr)
	{
	//	DroidPalWP.log("Scale what="+what+" fromX="+fromX + " toX=" + toX +
	//			" fromY=" + fromY + " toY=" + toY +
	//			" pivotX=" + pivotX + " pivotY="+pivotY);

		ApplyAnimation(what, len, delay, ipr, new ScaleAnimation(
				fromX, toX, fromY, toY,
				ScaleAnimation.RELATIVE_TO_SELF, pivotX,
				ScaleAnimation.RELATIVE_TO_SELF, pivotY));

		// No relative object support
	}

	private void ApplyAnimation(int what, int len, int delay, int ipr, Animation anim)
	{
		anim.setDuration(len);
		anim.setStartOffset(delay);
		if (ipr > 0){
			anim.setInterpolator(aIprs[ipr-1]);
		}
		if (aObjs[what].animSet == null)
			aObjs[what].animSet = new AnimationSet(false);

		aObjs[what].animSet.addAnimation(anim);
	}
}
