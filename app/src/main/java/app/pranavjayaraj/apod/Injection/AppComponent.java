package app.pranavjayaraj.apod.Injection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pranav on 10/9/19.
 */


@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent
{

}