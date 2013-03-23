package org.sam.applications.galleryviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class GalleryViewDemo extends Activity {

	public final static int ADDED_EXTRA = Integer.MAX_VALUE;
	private Gallery mGallery;
	private TextView tv;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_gallery_view_demo);

		mGallery = (Gallery) findViewById(R.id.gallery);
		tv = (TextView)findViewById(R.id.textView);
		mGallery.setAdapter(new ImageAdapter(this));
		mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				if (position >= 5) {
					position = position % 5;
				}
				tv.setText("Selected Item : " + position);
			}

			public void onNothingSelected(AdapterView<?> parent) {
				//To-Do
			}


		});
	}

	public class ImageAdapter extends BaseAdapter {
		/** The parent context */
		private Context myContext;
		
		private int[] myImageIds = { R.drawable.gallery_photo_1, R.drawable.gallery_photo_2,
				R.drawable.gallery_photo_3, R.drawable.gallery_photo_4, R.drawable.gallery_photo_5};

		/** Simple Constructor saving the 'parent' context. */
		public ImageAdapter(Context c) {
			this.myContext = c;
		}

		/**
		 * Returns the no. of views to be inflated. Infinite for Circular View
		 */
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}
		// Returns a new ImageView to be displayed,
		public View getView(int position, View convertView, 
				ViewGroup parent) {
			
			if (position >= myImageIds.length) {
				position = position % myImageIds.length;
			}
			if(convertView==null)
		       {
		       convertView = LayoutInflater
		         .from(myContext)
		         .inflate(R.layout.image_view, null);
		       }
			ImageView iv = (ImageView)convertView.findViewById(R.id.imageView); 
			try {
				iv.setImageResource(this.myImageIds[position]);
			} catch (Exception e) {
				Log.i("ARRAY INDEX", "Position" + position, e);
			}
			iv.setScaleType(ImageView.ScaleType.CENTER);
			return convertView;

					}
	}
}

