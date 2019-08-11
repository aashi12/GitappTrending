package viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;


import java.util.List;

import javax.inject.Inject;

import adapter.di.DaggerRetComponent;
import adapter.di.RetComponent;
import adapter.di.lrumodule;
import img.here.lrucache.EspressoIdling;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import networking.RestInterface;
import pojos.GitTrending;

public class MainActivityviewmodel  extends ViewModel
{



    MutableLiveData<List<GitTrending>> gitdata ;





    RetComponent mdaggerRetComponent;

    MainActivityviewmodel(RetComponent mycpom)
    {

        mdaggerRetComponent=mycpom;
        mdaggerRetComponent.inject(this);


    }



    @Inject
    RestInterface httpRest;





    public LiveData<List<GitTrending>> SuscribeGitData(){

       //DaggerRetComponent.builder().build().inject(this);
        //DaggerRetComponent.builder().lrumodule(new lrumodule(cacheSize)).build().inject(this);




       if(gitdata==null)
       {


           gitdata = new MutableLiveData<>();
       }

       return  gitdata;



    }





    public  void  getGitDataFromNetwork()
    {

        EspressoIdling.increment();



        httpRest.getTrendeingRepo("javascript","weekly").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<GitTrending>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<GitTrending>
                                          gitTrending)
            {


                if(gitTrending!=null)
                {


                    gitdata.postValue(gitTrending);
                }

                if (!EspressoIdling.getIdlingResource().isIdleNow())
                {
                    EspressoIdling.decrement(); // Set app as idle.
                }

            }

            @Override
            public void onError(Throwable e) {
                if (!EspressoIdling.getIdlingResource().isIdleNow())
                {
                    EspressoIdling.decrement(); // Set app as idle.
                }
            }
        });



    }



}
