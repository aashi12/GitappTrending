package networking;

import java.util.List;

import io.reactivex.Single;
import pojos.GitTrending;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestInterface
{






    @GET("repositories")
    public Single<List<GitTrending>> getTrendeingRepo(@Query("language") String language, @Query("since") String since);
}
