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
    public void initialize(Bootstrap<TestConfiguration> bootstrap) {
        DefaultSwaggerView defaultSwaggerView = new DefaultSwaggerView();
        defaultSwaggerView.setTitle("Test UI");
        defaultSwaggerView.setDisplayApiSelector(false);

        bootstrap.addBundle(new SwaggerBundle(defaultSwaggerView));

//        bootstrap.addBundle(new SwaggerBundle(new TestSwaggerView("/index.mustache", "/assets/")));
    }

    @Override
    public void run(TestConfiguration configuration, Environment environment) throws Exception {

    }
}
