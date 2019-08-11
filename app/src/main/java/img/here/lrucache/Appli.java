package img.here.lrucache;

import android.app.Application;

import adapter.di.DaggerRetComponent;
import adapter.di.RetComponent;
import adapter.di.lrumodule;

public  class Appli extends Application
{



   public RetComponent mycomp;

    @Override
    public void onCreate() {
        super.onCreate();

        cachingSetup();
       mycomp= DaggerRetComponent.builder().lrumodule(new lrumodule(cacheSize)).build();


    }

    int cacheSize;

    private void cachingSetup()
    {  int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        cacheSize = maxMemory / 8;



    }




    public  RetComponent  getComponent()
    {

        return  mycomp;

    }





}
