import com.alexeinunez.dropwizard.swagger.DefaultSwaggerView;
import com.alexeinunez.dropwizard.swagger.SwaggerBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TestApplication extends Application<TestConfiguration> {

    public static void main(String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        final DefaultSwaggerView defaultSwaggerView = new DefaultSwaggerView();
        defaultSwaggerView.setTitle("Test UI");

        bootstrap.addBundle(new SwaggerBundle(defaultSwaggerView));

//        bootstrap.addBundle(new SwaggerBundle(new TestSwaggerView()));
    }

    @Override
    public void run(final TestConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().register(new TestResource());
    }
}
