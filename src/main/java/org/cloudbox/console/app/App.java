package org.cloudbox.console.app;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.cloudbox.console.utils.DB;

/**
 * Created by kevin on 7/27/16.
 */
public class App extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "adcenter";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/webapp/", "/", "index.html", "static"));
    }

    @Override
    public void run(AppConfiguration conf, Environment env) throws Exception {
        DB.init(conf);

        //env.jersey().register(new UserResource());
    }
}