import com.alexeinunez.dropwizard.swagger.SwaggerBundleConfiguration;
import com.alexeinunez.dropwizard.swagger.SwaggerConfiguration;
import com.bazaarvoice.dropwizard.assets.AssetsConfiguration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class TestConfiguration extends Configuration implements SwaggerBundleConfiguration {

    @JsonProperty
    private SwaggerConfiguration swaggerConfiguration = new SwaggerConfiguration();

    @JsonProperty
    private AssetsConfiguration assetsConfiguration = new AssetsConfiguration();

    @Override
    public SwaggerConfiguration getSwaggerConfiguration() {
        return swaggerConfiguration;
    }

    @Override
    public AssetsConfiguration getAssetsConfiguration() {
        return assetsConfiguration;
    }
}
