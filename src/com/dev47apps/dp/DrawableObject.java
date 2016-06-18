package com.dev47apps.dp;

import java.util.Stack;

import android.graphics.Bitmap;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

public class DrawableObject {

	AnimationSet animSet;
	public Bitmap mBmp;
	Transformation mTransform;
	public int idleOffset[];
	Stack<RelativeObjectParams> RelObjs;


	public DrawableObject(){
		mBmp = null;
		idleOffset = new int[2];
	}

	public void Init(){
		RelObjs = new Stack<RelativeObjectParams>();
		mTransform = new Transformation();
	}

	public void SetIdlePos(int x, int y){
		idleOffset[0] = x;
		idleOffset[1] = y;
	}

	void PushRelativeObject(int objIdx, int relOffsetX, int relOffsetY){
		RelObjs.push(new RelativeObjectParams(objIdx, relOffsetX, relOffsetY));
	}
	public void PopRelativeObject(){
		RelObjs.pop();
	}

	// to be called from within onDraw
	void UpdateTransform(long timeStamp){
	//	mTransform.clear(); // TODO: Not needed?
		animSet.getTransformation(timeStamp, mTransform);
	}

	void Initialize(int sw, int sh){
		animSet.setFillEnabled(true);
		animSet.setFillBefore(false);
		animSet.setFillAfter(false);
		animSet.initialize(mBmp.getWidth(), mBmp.getHeight(), sw, sh);
	}

	public class RelativeObjectParams{
		int index, offsetX, offsetY;
		public RelativeObjectParams(int i, int x, int y){
			index = i; offsetX = x; offsetY = y;
		}
	}

}
