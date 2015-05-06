import com.alexeinunez.dropwizard.swagger.SwaggerView;

public class TestSwaggerView extends SwaggerView {

    protected TestSwaggerView() {
        super("/templates/index.mustache", "/api-docs");
    }

}
