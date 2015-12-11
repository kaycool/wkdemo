package cn.thunder.imagedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView mImageView01, mImageView02;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupViews();
	}

	private void setupViews() {
		mImageView01 = (ImageView) findViewById(R.id.image01);
		mImageView02 = (ImageView) findViewById(R.id.image02);

		// ��ȡ��ֽ����ֵ��Drawable
	//	Drawable drawable = getWallpaper();
		
		Drawable drawable = getResources().getDrawable(R.drawable.x);
		// ��Drawableת��ΪBitmap
		Bitmap bitmap = ImageUtil.drawableToBitmap(drawable);
		// ����ͼƬ
		Bitmap zoomBitmap = ImageUtil.zoomBitmap(bitmap, 480, 300);
		// ��ȡԲ��ͼƬ
		Bitmap roundBitmap = ImageUtil.getRoundedCornerBitmap(zoomBitmap, 50.0f);
		// ��ȡ��ӰͼƬ
		Bitmap reflectBitmap = ImageUtil.createReflectionImageWithOrigin(zoomBitmap);
		// ���������Bitmap��ת��ΪDrawable
		// Drawable roundDrawable = new BitmapDrawable(roundBitmap);
		// Drawable reflectDrawable = new BitmapDrawable(reflectBitmap);
		// mImageView01.setBackgroundDrawable(roundDrawable);
		// mImageView02.setBackgroundDrawable(reflectDrawable);

		mImageView01.setImageBitmap(roundBitmap);
		mImageView02.setImageBitmap(reflectBitmap);
	}

}
