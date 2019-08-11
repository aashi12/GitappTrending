package adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import adapter.di.RetComponent;
import img.here.lrucache.Appli;
import img.here.lrucache.R;
import img.here.lrucache.databinding.RowDetailsBinding;
import img.here.lrucache.databinding.RowGitBinding;
import pojos.GitTrending;
import pojos.ListPojo;

public class DetailsAdapter  extends  RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>
{

    private Context mcon;
    private GitTrending mdata;




    RetComponent mDaggerRetComponent;



    @Inject
    LruCache<String, Bitmap> memoryCache;


    public DetailsAdapter(Context con, GitTrending alldata)
    {
        mcon=con;
        mdata=alldata;

       ((Appli)con.getApplicationContext()).mycomp.inject(this);



    }

    @NonNull
    @Override
    public DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RowDetailsBinding mybinding = DataBindingUtil.inflate(LayoutInflater.from(mcon), R.layout.row_details, viewGroup, false);
        return new DetailsViewHolder(mybinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsViewHolder detailsViewHolder, int i)
    {



        detailsViewHolder.mybinding.setData(new ListPojo(mdata.getBuiltBy().get(i).getUsername(),mdata.getBuiltBy().get(i).getHref(),mdata.getBuiltBy().get(i).getAvatar(),memoryCache,0));

    }

    @Override
    public int getItemCount() {
        return mdata.getBuiltBy().size();
    }

    class  DetailsViewHolder extends RecyclerView.ViewHolder
    {

        RowDetailsBinding   mybinding;

        DetailsViewHolder(@NonNull RowDetailsBinding itemView) {
            super(itemView.getRoot());
            mybinding=itemView;
        }
    }
}
