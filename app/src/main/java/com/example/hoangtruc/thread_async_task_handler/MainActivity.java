package com.example.hoangtruc.thread_async_task_handler;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.print.PrinterId;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageAdapter mAdapter;
    private Uri mUri;
    private ArrayList<Bitmap> mListImages;
    private Cursor mCursor;
    private int mColumnIndex;
    private String mPath = null;
    private static final int ROW_COUNT = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    class MyAsynTask extends AsyncTask<Void,Void ,ArrayList<Bitmap>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<Bitmap> doInBackground(Void... voids) {
            mUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
            mCursor = getApplicationContext().getContentResolver().query(mUri, projection, null,
                    null, null);
            mListImages=new ArrayList<>();
            mColumnIndex = mCursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (mCursor.moveToNext()) {
                mPath = mCursor.getString(mColumnIndex);
                 mListImages.add(BitmapFactory.decodeFile(mPath));
            }
            return mListImages;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(ArrayList<Bitmap> bitmaps) {
            super.onPostExecute(bitmaps);
            mAdapter =new ImageAdapter(mListImages);
            mLayoutManager = new GridLayoutManager(MainActivity.this, ROW_COUNT);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
