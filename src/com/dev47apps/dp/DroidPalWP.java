package com.dev47apps.dp;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.animation.Animation;

import com.dev47apps.dp.anim.DonutEat;
import com.dev47apps.dp.anim.DroidPalAnim;
import com.dev47apps.dp.anim.Enter;
import com.dev47apps.dp.anim.HeadPhonesOn;
import com.dev47apps.dp.anim.Idle;
import com.dev47apps.dp.anim.LookUp;
import com.dev47apps.dp.anim.Peek;

// TODO yougli.net
// Remove unused library/xml stuff from the initial attempt

public class DroidPalWP extends WallpaperService {
	final Handler mHandler = new Handler();

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public Engine onCreateEngine() {
		return new WPEngine(this.getApplicationContext());
	}

	public class WPEngine extends Engine  {
		Vibrator vibrator ;

		AnimManager animManager;
		AnimList animList;
		boolean animRunning;
		int droidState, droidStateCount;
		long invisibleTimestamp;

		final int IDLE   = 0;
		final int REACT  = 1;
		final int ANGRY  = 2;
		final int LEAVE  = 3;
		final int SLEEP  = 4;

		WPEngine(Context ctx)
		{
			animList = new AnimList();
			animManager = new AnimManager(new AnimListen(), ctx);
			droidState = LEAVE;
			droidStateCount = 0;
			invisibleTimestamp = 0;
			animRunning = false;
			vibrator = (Vibrator)ctx.getSystemService(Context.VIBRATOR_SERVICE);

			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
			ctx.registerReceiver(mIntentReceiver, intentFilter);
		}
		final Runnable mDrawFrame = new Runnable() {
	        @Override
			public void run() {
	        	final SurfaceHolder holder = getSurfaceHolder();
				Canvas c = null;
				log("mDrawFrame");
				try {
					c = holder.lockCanvas();
				//	if (c != null) { // draw something
					animManager.drawFrame(c);
				} finally {
				//	if (c != null)
					holder.unlockCanvasAndPost(c);
				}

				mHandler.removeCallbacks(this);
				if (animRunning)
					mHandler.postDelayed(this, 1000 / 25);// Reschedule the next redraw
	        }
	    };

	    private final class AnimListen implements Animation.AnimationListener {
			@Override
			public void onAnimationStart(Animation animation){}
			@Override
			public void onAnimationRepeat(Animation animation){}
			@Override
			public void onAnimationEnd(Animation animation){
				animRunning = false;
				mHandler.post(mPlayAnim); // load next stage
			}
		}

		final Runnable mPlayAnim = new Runnable() {
			@Override
			public void run() {
				int delay;
				if ((delay = animManager.LoadNextStage()) >= 0) {
					animRunning = true;
					mHandler.postDelayed(mDrawFrame, delay);
				} else {
					animRunning = false;
				}
				log("mPlayAnim.. delay=" + delay);
			}
		};

		final Runnable mVibrate = new Runnable() {
			@Override
			public void run() {
				if (vibrator != null) {

				long fun[][] = {
					{0,50,100,50,100,50,100,400,100,300,100,350,50,200 ,100,100,50,600 },
					{0, 250, 200, 250, 150, 150, 75, 150, 75, 150 },
					{0,100,200,100,100,100,100,100,200,100,500,100,225, 100},
					{0, 400, 50, 100, 50, 100, 50, 100, 200, 400, 50, 100, 50, 100, 50}
				};
				for(int i=0; i < 2; i++){
					vibrator.vibrate(fun[ animManager.getRand(fun.length) ], -1);
					try {
						Thread.sleep(animManager.getRand(4096)+4096);
					} catch (InterruptedException e) {
						break;
					}
				}

				} //vibrator != null
				BeginAnimation(new Peek(), animManager.getRand(2000)+3000);
			}
		};

		final Runnable mPlayGenericAnim = new Runnable() {
			@Override
			public void run() {
				if (!animRunning && droidState == IDLE){
					animManager.LoadAnim(animList.GetRandomAnim(IDLE));
					mPlayAnim.run();

					mHandler.postDelayed(mPlayGenericAnim, animManager.getRand(0x4000)+0x6000);
				}
			}
		};

		final BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent){
				if (animRunning == false && droidState == IDLE){
					BeginAnimation(new HeadPhonesOn(intent.getIntExtra("state", 0)), 0);
				}
			}
		};

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
			setTouchEventsEnabled(true);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			animRunning = false;
			mHandler.removeCallbacks(mDrawFrame);
			try {
				unregisterReceiver(mIntentReceiver);
			} catch (IllegalArgumentException e ){}
		}

		@Override
		public void onVisibilityChanged(boolean visible)
		{
			log("onVisibilityChanged->visible="+visible);
			if (visible) {
				log("invisibleTimestamp=" + invisibleTimestamp +", animRunning=" + animRunning);
				if (invisibleTimestamp == 0){ // First time?
					BeginAnimation(new Enter(true), 2);
					droidState = IDLE;
					return;
				}
				if (animRunning)return;

				long sleepTimeSecs = (System.currentTimeMillis() - invisibleTimestamp) / 1000;
				log("Idle length (s) "+sleepTimeSecs + " state="+droidState);

				if (droidState == LEAVE)
				{
					if (sleepTimeSecs > 10*60){
						BeginAnimation(new Enter(false), 512);
						droidState = droidStateCount = IDLE;
					} else if (sleepTimeSecs > 64) {
						BeginAnimation(new Peek(), 512);
					}
				} else if (droidState == SLEEP) {
					if (sleepTimeSecs > 20*60){ // 20 min
						DroidReact(false); // slowly wake up
					}
				} else {
					int prob = 128; // ie. < zero chance
					if (sleepTimeSecs < 10) { BeginAnimation(new Enter(true), 2); return; }
					else if (sleepTimeSecs < (5 * 60))   prob = 80;// 5 min
					else if (sleepTimeSecs < (10 * 60))  prob = 25;//
					else if (sleepTimeSecs < (30 * 60))  prob = 10;//
					else if (sleepTimeSecs < (150 * 60)) prob =  0;// 2.5 hrs
					else {
						droidState = SLEEP;//Go to SLEEP
						droidStateCount = 1;// must be +1 from curr anim
						BeginAnimation(animList.GetIndexedAnim(SLEEP, 0), 0);
					}
					DroidGreet(prob, 1024);
					mHandler.postDelayed(mPlayGenericAnim, animManager.getRand(0x4000)+0x4000);
				}
			} else {
				invisibleTimestamp = System.currentTimeMillis();
				mHandler.removeCallbacks(mPlayAnim);
				mHandler.removeCallbacks(mPlayGenericAnim);
			}
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			log("onSurfaceDestroyed");
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			animRunning = false;
			mHandler.removeCallbacks(mPlayAnim);
			mHandler.removeCallbacks(mDrawFrame);
			mHandler.removeCallbacks(mPlayGenericAnim);

			super.onSurfaceChanged(holder, format, width, height);
			log("onSurfaceChanged->w="+width+" h="+height);
			animManager.SetScreenSize(width, height);
			if (droidState == LEAVE)
				return;

			DroidPalAnim anim =
				(droidState == SLEEP) ?	animList.GetIndexedAnim(SLEEP, 0) : new Idle();
			BeginAnimation(anim, 0);
		}

		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
			log("onSurfaceCreated");
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset, float xStep, float yStep, int xPixels, int yPixels)
		{
		//	log("onOffsetsChanged(xOffset="+xOffset+" yOffset="+yOffset+" xStep="+xStep
		//			+" yStep="+yStep+" xPixels="+xPixels+" yPixels="+yPixels);
			if (animManager.wallPaper.mBmp != null){
				animManager.wallPaper.SetIdlePos(
						(int) (xOffset * (animManager.mScreenWidth  - animManager.wallPaper.mBmp.getWidth())),
						(int) (yOffset * (animManager.mScreenHeight - animManager.wallPaper.mBmp.getHeight())));
				if (animRunning == false) mHandler.post(mDrawFrame);
				//If stageDelay > 0, the background won't update while swiping
			}
		}

		@Override
		public Bundle onCommand(String action, int x, int y, int z, Bundle extras, boolean resultRequested) {
			if (animRunning == true || droidState == LEAVE)
				return null;

		    if (action.equals(WallpaperManager.COMMAND_TAP))
		    {
		    	if (droidState != SLEEP && animManager.IsOnScreen(x,y)){
					DroidGreet(70, 0);
				}
				else if (animManager.IsWithinObj(0, x, y)) // head
				{
					DroidReact(true);
				}
				else if (animManager.IsWithinObj(1, x, y))// body
				{
					DroidReact(false);
				}
				// FIXME - Testing
				else if (animManager.IsWithinObj(3,  x, y)){
					animManager.LoadAnim(new DonutEat());
					mHandler.post(mPlayAnim);
				}
				else if (animManager.IsWithinObj(2,  x, y)){
				//	animManager.LoadAnim(new QRFlag());
				//	mHandler.post(mPlayAnim); XXX CONT HERE
				}
				// ^^^
		    }
		    else if (action.equals(WallpaperManager.COMMAND_DROP) && animManager.getRand(3) == 0
		    		 && y < animManager.aObjs[8].idleOffset[1]) // Look up every now and then ;)
		    {	BeginAnimation(new LookUp(), 0);
		    	if (droidStateCount > 0)  droidStateCount --; //less irritated
		    }
		    return null;
		}

		void BeginAnimation(DroidPalAnim anim, int delay){
			if (animRunning == false) {
				log("calling LoadAnim");
				animManager.LoadAnim(anim);
				mHandler.postDelayed(mPlayAnim, delay);
			}
		}

//		@Override
//		public void onTouchEvent(MotionEvent event) {
//			super.onTouchEvent(event);
//		}

		// Play a GREET animation with a certain probability
		// inverseProb is the chance an anim is NOT played.
		// I have a hunch the Math.random function favours numbers close to 0
		// So we play an anim if the returned number is close to 1
		// thus reducing the probability a bit - a desired effect
		void DroidGreet(int inverseProb, int delay){
			log("DroidGreet x"+(100-inverseProb));
			if (animManager.getRand(100) > inverseProb){
				BeginAnimation(animList.GetRandomAnim(5), delay);
				if (droidStateCount > 0)  droidStateCount --; //less irritated
			}
		}

		void DroidReact(boolean isHead){

			DroidPalAnim anim = null;

			switch (droidState){
			case IDLE:
				droidState = (isHead) ? ANGRY : REACT;
				droidStateCount = 1;
			break;
			case REACT:
				if (isHead || droidStateCount == 3){
					droidState = ANGRY;
				} else {
					droidStateCount ++;
				}
			break;
			case ANGRY:
				if (isHead){
					if (droidStateCount >= 3)
						droidState = LEAVE;
					else
						droidStateCount ++;
				} else {
					if (droidStateCount < 3)
						droidState = REACT;
					else if (droidStateCount > 3)
						droidState = LEAVE;
					// else XXX: ANGRY 4 ?
					droidStateCount ++;
				}
			break;
			case SLEEP:
				log("Playing sleep anim "+droidStateCount);
				anim = animList.GetIndexedAnim(SLEEP, droidStateCount);
				droidStateCount ++ ;
				if (droidStateCount == 3){//animList.animList[SLEEP].length){
					droidState = IDLE;
					droidStateCount = 0;
				}
			break;
			default:
				log("WARN: invalid droid_state: "+droidState);
				return;
			} // switch

			if (anim == null) anim = animList.GetRandomAnim(droidState);

			BeginAnimation(anim, 0);

			if (droidState == LEAVE){
				mHandler.postDelayed(mVibrate, 2500);
				droidStateCount = 0;
			}
		}
	}

	public static void log(String s){
		Log.i("47", s);
	}
}
