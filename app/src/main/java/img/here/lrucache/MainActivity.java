package img.here.lrucache;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
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
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import adapter.GitDataAdapter;
import img.here.lrucache.databinding.ActivityMainBinding;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pojos.GitTrending;
import viewmodel.MainActivityviewmodel;
import viewmodel.MainActivityviewmodelFactory;

public class MainActivity extends AppCompatActivity {


    public MainActivityviewmodel lvm;
    ImageView mainimage;
    Bitmap image;

    ActivityMainBinding mainactivty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainactivty = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainactivty.setHandler(this);





        MainActivityviewmodelFactory factory = new MainActivityviewmodelFactory(((Appli)getApplication()).mycomp);

        lvm = ViewModelProviders.of(this,factory).get(MainActivityviewmodel.class);

        lvm.SuscribeGitData().observe(this, new Observer<List<GitTrending>>() {
            @Override
            public void onChanged(List<GitTrending> gitTrending) {


                mainactivty.progressBar.setVisibility(View.INVISIBLE);

                if (gitTrending != null) {


                    mainactivty.setAlldata(gitTrending);


                }
            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        lvm.getGitDataFromNetwork();

    }



    public static LruCache<String, Bitmap> memoryCache;




    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null)
        { memoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }


    @BindingAdapter({"myalldata", "conte"})
    public static void ShowImage(RecyclerView recy, List<GitTrending> mallData, MainActivity handler) {

        LinearLayoutManager lmr = new LinearLayoutManager(handler);

        lmr.setOrientation(LinearLayoutManager.VERTICAL);

        recy.setLayoutManager(lmr);

        recy.setAdapter(new GitDataAdapter(handler, mallData));


    }


    @BindingAdapter({"contt", "setimage", "pos"})
    public static void setIMage(final ImageView myimage, final LruCache<String, Bitmap> cache, final String url, final int pos)
    {
        memoryCache=cache;

        if (getBitmapFromMemCache(url) == null)


            Observable.create(new ObservableOnSubscribe<Bitmap>()
            {
                @Override
                public void subscribe(ObservableEmitter<Bitmap> emitter) throws Exception {


                    URL urlll = new URL(url);
                    Bitmap image = BitmapFactory.decodeStream(urlll.openConnection().getInputStream());
                    addBitmapToMemoryCache(url + "", image);
                    emitter.onNext(image);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.Observer<Bitmap>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Bitmap s) {
                    myimage.setImageBitmap(s);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });









        else {
            myimage.setImageBitmap(getBitmapFromMemCache(url + ""));

        }










    }



}




