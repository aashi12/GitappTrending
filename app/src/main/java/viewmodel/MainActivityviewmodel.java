package viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import networking.RestConfig;
import networking.RestInterface;
import pojos.GitTrending;

public class MainActivityviewmodel  extends ViewModel
{



    MutableLiveData<List<GitTrending>> gitdata ;






   public LiveData<List<GitTrending>> SuscribeGitData(){



       if(gitdata==null)
       {


           gitdata = new MutableLiveData<>();
       }

       return  gitdata;



    }





    public  void  getGitDataFromNetwork()
    {


        RestConfig.getRetrofitInstance().create(RestInterface.class).getTrendeingRepo("javascript","weekly").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<GitTrending>>() {
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

            }

            @Override
            public void onError(Throwable e) {

            }
        });



    }


}
