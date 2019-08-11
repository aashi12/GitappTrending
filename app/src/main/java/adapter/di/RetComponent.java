package adapter.di;


import android.graphics.Bitmap;
import android.util.LruCache;

import javax.inject.Singleton;

import adapter.DetailsAdapter;
import adapter.GitDataAdapter;
import dagger.Component;
import img.here.lrucache.DetialsActivity;
import viewmodel.MainActivityviewmodel;


@Singleton
@Component(modules = {Retrofitmodule.class,lrumodule.class})
public interface RetComponent
{

    public  void  inject(MainActivityviewmodel mavm);
    public  void  inject(GitDataAdapter mavm);
    public  void  inject(DetailsAdapter mavm);
    public  void  inject(DetialsActivity mavm);

    public LruCache<String, Bitmap> getlru();
}
