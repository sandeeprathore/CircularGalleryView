package org.sam.applications.galleryviewdemo;

import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GalleryViewDemo extends Activity {

	public final static int ADDED_EXTRA = Integer.MAX_VALUE;
	Gallery myGallery;
	private TextView tv;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.activity_gallery_view_demo);

		// Bind the gallery defined in the main.xml
		// Apply a new (customized) ImageAdapter to it.

		myGallery = (Gallery) findViewById(R.id.gallery);
		tv = (TextView)findViewById(R.id.textView);
		myGallery.setAdapter(new ImageAdapter(this));
		myGallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				if (position >= 5) {
					position = position % 5;
				}
				tv.setText("Selected Item : " + position);
			}

			public void onNothingSelected(AdapterView<?> parent) {
				//mySelection.setText("Nothing selected");

			}


		});
	}// onCreate

	public class ImageAdapter extends BaseAdapter {
		/** The parent context */
		private Context myContext;
		private Inflater inflater;
		// Put some images to project-folder: /res/drawable/
		// format: jpg, gif, png, bmp, ...
		private int[] myImageIds = { R.drawable.gallery_photo_1, R.drawable.gallery_photo_2,
				R.drawable.gallery_photo_3, R.drawable.gallery_photo_4, R.drawable.gallery_photo_5};

		/** Simple Constructor saving the 'parent' context. */
		public ImageAdapter(Context c) {
			this.myContext = c;
		}

		// inherited abstract methods - must be implemented
		// Returns count of images, and individual IDs
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
		         .from(parent.getContext())
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

			/*if (position < ADDED_EXTRA / 2) {
				position = this.myImageIds.length
						- (ADDED_EXTRA / 2 - position);
			} else if (position >= (this.myImageIds.length + (ADDED_EXTRA / 2))) {
				position = position
						- (this.myImageIds.length + (ADDED_EXTRA / 2));
			} else {
				position -= (ADDED_EXTRA / 2);
			}

			//			if (position >= myImageIds.length) {
			//				position = position % myImageIds.length;
			//			}
			// Get a View to display image data 					
			ImageView iv = new ImageView(this.myContext);
			iv.setImageResource(this.myImageIds[position]);

			// Image should be scaled somehow
			iv.setScaleType(ImageView.ScaleType.CENTER);
			//iv.setScaleType(ImageView.ScaleType.CENTER_CROP);			
			//iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			//iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
			//iv.setScaleType(ImageView.ScaleType.FIT_XY);
			//iv.setScaleType(ImageView.ScaleType.FIT_END);

			// Set the Width & Height of the individual images
			iv.setLayoutParams(new Gallery.LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT));

			return iv;*/
		}
	}// ImageAdapter
}// AndDemoUI


