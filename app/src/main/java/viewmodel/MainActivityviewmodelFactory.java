package viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import adapter.di.DaggerRetComponent;
import adapter.di.RetComponent;

public class MainActivityviewmodelFactory  implements ViewModelProvider.Factory
{


    RetComponent mDaggerRetComponent;

    public MainActivityviewmodelFactory(RetComponent daggerRetComponent)
    {

        mDaggerRetComponent=daggerRetComponent;
    }







    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityviewmodel(mDaggerRetComponent);
    }
}
