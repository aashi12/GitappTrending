package adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.List;

import img.here.lrucache.MainActivity;
import img.here.lrucache.R;
import img.here.lrucache.databinding.RowGitBinding;
import pojos.GitTrending;
import pojos.ListPojo;

public class GitDataAdapter  extends RecyclerView.Adapter<GitDataAdapter.GitDataViewholder>
{








    List<GitTrending>  mAlldata;



    MainActivity mcon;

    public  GitDataAdapter(MainActivity con, List<GitTrending> alldata)
    {
        mAlldata=alldata;
        mcon=con;


    }

    @NonNull
    @Override
    public GitDataAdapter.GitDataViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        RowGitBinding mybinding = DataBindingUtil.inflate(LayoutInflater.from(mcon), R.layout.row_git, parent, false);
        return new GitDataViewholder(mybinding);

    }

    @Override
    public void onBindViewHolder(@NonNull GitDataAdapter.GitDataViewholder holder, int position)
    {


        holder.mybinfing.setData(new ListPojo(mAlldata.get(position).getName(),mAlldata.get(position).getUrl(),mAlldata.get(position).getAvatar(),mcon,position));


    }

    @Override
    public int getItemCount()
    {
        if(mAlldata!=null)
        return mAlldata.size();
        else return  0;
    }

    public  class  GitDataViewholder extends  RecyclerView.ViewHolder
    {

        public  RowGitBinding  mybinfing;



        public GitDataViewholder(@NonNull RowGitBinding itemView) {
            super(itemView.getRoot());

            mybinfing =itemView;



        }
    }
}
