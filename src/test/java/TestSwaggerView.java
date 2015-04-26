import com.alexeinunez.dropwizard.swagger.SwaggerView;

public class TestSwaggerView extends SwaggerView {

    protected TestSwaggerView() {
        super("/index.mustache", "/api-docs");
    }

}
