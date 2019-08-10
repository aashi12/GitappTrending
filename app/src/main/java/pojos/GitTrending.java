
package pojos;

import java.util.List;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class GitTrending {

    @SerializedName("author")
    private String mAuthor;
    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("builtBy")
    private List<BuiltBy> mBuiltBy;
    @SerializedName("currentPeriodStars")
    private Long mCurrentPeriodStars;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("forks")
    private Long mForks;
    @SerializedName("language")
    private String mLanguage;
    @SerializedName("languageColor")
    private String mLanguageColor;
    @SerializedName("name")
    private String mName;
    @SerializedName("stars")
    private Long mStars;
    @SerializedName("url")
    private String mUrl;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public List<BuiltBy> getBuiltBy() {
        return mBuiltBy;
    }

    public void setBuiltBy(List<BuiltBy> builtBy) {
        mBuiltBy = builtBy;
    }

    public Long getCurrentPeriodStars() {
        return mCurrentPeriodStars;
    }

    public void setCurrentPeriodStars(Long currentPeriodStars) {
        mCurrentPeriodStars = currentPeriodStars;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Long getForks() {
        return mForks;
    }

    public void setForks(Long forks) {
        mForks = forks;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public String getLanguageColor() {
        return mLanguageColor;
    }

    public void setLanguageColor(String languageColor) {
        mLanguageColor = languageColor;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getStars() {
        return mStars;
    }

    public void setStars(Long stars) {
        mStars = stars;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
