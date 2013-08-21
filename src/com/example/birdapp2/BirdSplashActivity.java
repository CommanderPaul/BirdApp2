package com.example.birdapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class BirdSplashActivity extends BirdActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bird_splash);
		
		
		// run animation
		runTextAnimation();
		runBirdAnimation();
		// run sound
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bird_splash, menu);
		return true;
	}
	
	private void runTextAnimation(){
		
		// grab a reference to the sub-view
		TextView animatedText = (TextView) findViewById(R.id.TextViewAnimatedSplash);
		
		// get a reference to the animation
		Animation splashMovement = AnimationUtils.loadAnimation(this, R.anim.text_splash_anim);
		
		// start animation
		animatedText.startAnimation(splashMovement);
		
	}
	private void runBirdAnimation(){
		
		// grab a reference to the sub-view
		ImageView animatedImage = (ImageView) findViewById(R.id.ImageViewAnimatedSplash);
		
		// get a reference to the animation
		Animation splashMovement = AnimationUtils.loadAnimation(this, R.anim.bird_splash_anim);
		
		// start animation
		animatedImage.startAnimation(splashMovement);
		
		
		// start main menu after bird animation has run
		// listener for animation
		splashMovement.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation animation) {
				startActivity(new Intent(BirdSplashActivity.this,
						MenuActivity.class));
				BirdSplashActivity.this.finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

		});
	
	}
}
