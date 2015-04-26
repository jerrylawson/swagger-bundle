package com.alexeinunez.dropwizard.swagger;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class SwaggerBundle implements ConfiguredBundle<SwaggerBundleConfiguration> {

    public static final String defaultAssetPath = "/assets/";

    private final SwaggerView view;
    private final String assetPath;

    public SwaggerBundle() {
        this(new DefaultSwaggerView(), defaultAssetPath);
    }

    public SwaggerBundle(SwaggerView view) {
        this(view, defaultAssetPath);
    }

    public SwaggerBundle(SwaggerView view, String assetPath) {
        this.view = view;
        this.assetPath = assetPath;
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        //noinspection unchecked
        bootstrap.addBundle((ConfiguredBundle) new ConfiguredAssetsBundle(this.assetPath));
    }

    @Override
    public void run(SwaggerBundleConfiguration configuration, Environment environment) throws Exception {

        environment.jersey().register(new SwaggerResource(view));
        environment.jersey().register(new ApiListingResourceJSON());
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        ClassReaders.setReader(new DefaultJaxrsApiReader());
    }

}
