package img.here.lrucache;

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

import adapter.DetailsAdapter;
import img.here.lrucache.databinding.ActivityDetialsBinding;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pojos.GitTrending;

import static img.here.lrucache.MainActivity.addBitmapToMemoryCache;
import static img.here.lrucache.MainActivity.getBitmapFromMemCache;

public class DetialsActivity extends AppCompatActivity {
    ActivityDetialsBinding mybinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mybinding = DataBindingUtil.setContentView(this, R.layout.activity_detials);
        getAllIntent();

    }


    GitTrending toshow;

    private void getAllIntent() {
        toshow = (GitTrending) getIntent().getParcelableExtra("detailsdata");
        mybinding.setAlldata(toshow);


        mybinding.setCache(((Appli) getApplication()).mycomp.getlru()) ;
    }


    @BindingAdapter({"myallda"})
    public static void ShowfilledData(RecyclerView recy, GitTrending mallData) {

        LinearLayoutManager lmr = new LinearLayoutManager(recy.getContext());

        lmr.setOrientation(LinearLayoutManager.VERTICAL);

        recy.setLayoutManager(lmr);

        recy.setAdapter(new DetailsAdapter(recy.getContext(), mallData));


    }

    public  static  LruCache<String, Bitmap> memoryCache;


    @BindingAdapter({"seturl", "cache"})
    public static void setIMage(final ImageView myimage, final String url, final LruCache<String, Bitmap> cache) {

        memoryCache = cache;

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
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Bitmap>() {
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
