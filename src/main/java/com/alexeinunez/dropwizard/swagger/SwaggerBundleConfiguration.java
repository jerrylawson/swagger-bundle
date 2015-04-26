package com.alexeinunez.dropwizard.swagger;

import com.bazaarvoice.dropwizard.assets.AssetsBundleConfiguration;

public interface SwaggerBundleConfiguration extends AssetsBundleConfiguration {
    SwaggerConfiguration getSwaggerConfiguration();
}
