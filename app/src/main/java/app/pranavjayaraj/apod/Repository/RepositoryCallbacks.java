package app.pranavjayaraj.apod.Repository;

/**
 * Created by kuttanz on 10/9/19.
 */
public interface RepositoryCallbacks {

    void onResponse(String message);

    void onFailure(Throwable throwable);
}
