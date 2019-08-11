package adapter.di;


import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class lrumodule
{

    private int sizeofcache;


    public lrumodule(int cache)
    {

        sizeofcache=cache;



    }

    @Provides
    @Singleton
    android.util.LruCache<String, Bitmap> providesApplication()
    {
       return   new android.util.LruCache<String, Bitmap>(sizeofcache)
       {
           @Override
           protected int sizeOf(String key, Bitmap bitmap) {
               // The cache size will be measured in kilobytes rather than
               // number of items.
               return bitmap.getByteCount() / 1024;
           }
       };

    }








}
