package pojos;

import android.content.Context;

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

    MainActivity con;
    public ListPojo(String username, String giturl, String imageurl, MainActivity context,int positon) {
        this.username = username;
        this.giturl = giturl;
        this.imageurl = imageurl;
        con=context;
        this.position=positon;
    }


    public MainActivity getCon() {
        return con;
    }

    public void setCon(MainActivity con) {
        this.con = con;
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
