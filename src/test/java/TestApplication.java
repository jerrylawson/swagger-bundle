import com.alexeinunez.dropwizard.swagger.DefaultSwaggerView;
import com.alexeinunez.dropwizard.swagger.SwaggerBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class TestApplication extends Application<TestConfiguration> {

    public static void main(String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        final DefaultSwaggerView defaultSwaggerView = new DefaultSwaggerView();
        defaultSwaggerView.setTitle("Test UI");

        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new SwaggerBundle<TestConfiguration>(defaultSwaggerView));

//        bootstrap.addBundle(new SwaggerBundle(new TestSwaggerView()));
    }

    @Override
    public void run(final TestConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().register(new TestResource());
    }
}
