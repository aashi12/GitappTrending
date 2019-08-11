package pojos;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import img.here.lrucache.MainActivity;

public class ListPojo
{



    String username;

    String giturl;
    String imageurl;

    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    LruCache<String, Bitmap> mcache;
    public ListPojo(String username, String giturl, String imageurl, LruCache<String, Bitmap> cache,int positon) {
        this.username = username;
        this.giturl = giturl;
        this.imageurl = imageurl;
        mcache=cache;
        this.position=positon;
    }

    public LruCache<String, Bitmap> getMcache() {
        return mcache;
    }

    public void setMcache(LruCache<String, Bitmap> mcache) {
        this.mcache = mcache;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGiturl() {
        return giturl;
    }

    public void setGiturl(String giturl) {
        this.giturl = giturl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
