package img.here.lrucache;




import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import adapter.GitDataAdapter;
import img.here.lrucache.databinding.ActivityMainBinding;
import pojos.GitTrending;
import viewmodel.MainActivityviewmodel;

public class MainActivity extends AppCompatActivity {



    MainActivityviewmodel  lvm;
    ImageView mainimage;
    Bitmap image;

    ActivityMainBinding mainactivty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainactivty = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainactivty.setHandler(this);




        chacingSetup();



        lvm = ViewModelProviders.of(this).get(MainActivityviewmodel.class);


        lvm.SuscribeGitData().observe(this, new Observer<List<GitTrending>>() {
            @Override
            public void onChanged(List<GitTrending> gitTrending) {

                if(gitTrending!=null)
                {



                  //  if(mainactivty.recyy.getAdapter()!=null)


                    mainactivty.setAlldata(gitTrending);



                }
            }
        });


        lvm.getGitDataFromNetwork();













      //   saveBitmap();





    }

    private static  LruCache<String, Bitmap> memoryCache;

    private void chacingSetup()
    {

        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;



        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };

    }



    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null)
        {





            memoryCache.put(key, bitmap);
        }
    }

    public static  Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }


    @BindingAdapter({"myalldata", "conte"})
    public  static  void ShowImage(RecyclerView recy, List<GitTrending> mallData, MainActivity handler)
    {

        RecyclerView.LayoutManager lmr = new LinearLayoutManager(handler);

        ((LinearLayoutManager) lmr).setOrientation(LinearLayoutManager.VERTICAL);

        recy.setLayoutManager(lmr);

        recy.setAdapter(new GitDataAdapter(handler, mallData));



    }



    @BindingAdapter({"contt","setimage","pos"})
    public  static void setIMage(final ImageView myimage, final Context context, final String url, final int pos)
    {



        if(getBitmapFromMemCache(pos+"")==null)


        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    URL urlll = new URL(url);
                   final Bitmap image = BitmapFactory.decodeStream(urlll.openConnection().getInputStream());
                   addBitmapToMemoryCache(pos+"",image);
                   ((MainActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run()
                        {
                            myimage.setImageBitmap(image);
                        }
                    });


                } catch(IOException e) {

                }
            }
        }).start();


        else {
            myimage.setImageBitmap(getBitmapFromMemCache(pos+""));

        }
    }




    private void saveBitmap()
    {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    URL url = new URL("http://52.172.194.248/proof-pics/123_2019_8_8_670-1565260253.jpg");
                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainimage.setImageBitmap(image);
                        }
                    });


                } catch(IOException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }

}




