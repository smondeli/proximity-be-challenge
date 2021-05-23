package tech.proximity;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import tech.proximity.api.*;

public class App extends Application<AppConfiguration> {
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new SearchApi());
        environment.jersey().register(new CourseApi());
        environment.jersey().register(new SubjectApi());
        environment.jersey().register(new TagApi());
        environment.jersey().register(new VideoApi());
    }

    public static void main(String[] args) throws Exception {
        new App().run("server");
    }
}
