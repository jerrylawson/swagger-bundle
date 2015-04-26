/*
 * Copyright (C) 2015 Alexei Nunez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alexeinunez.dropwizard.swagger;

import com.bazaarvoice.dropwizard.assets.ConfiguredAssetsBundle;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import lombok.Getter;

/**
 * A Bundle which adds functionality for automatically finding and processing resources annotated with
 * <a href="https://swagger.io">Swagger</a> to expose a
 * <a href="https://github.com/swagger-api/swagger-spec">Swagger Spec</a>. Additionally,
 * a view is exposed for displaying the Swagger Spec with
 * <a href="https://github.com/swagger-api/swagger-ui">Swagger UI</a>, a UI able to interact with any API exposing
 * a Swagger Spec.
 *
 * <p>
 * The Swagger Spec endpoint is "/api-docs" and the Swagger UI endpoing is "/swagger".
 * </p>
 */
public class SwaggerBundle<T extends Configuration & SwaggerBundleConfiguration> implements ConfiguredBundle<T> {

    public static final String ROOT_ASSETS_PATH = "/assets/";

    @Getter
    private final SwaggerView view;

    /**
     * Renders a {@link com.alexeinunez.dropwizard.swagger.DefaultSwaggerView}
     */
    public SwaggerBundle() {
        this(new DefaultSwaggerView());
    }

    /**
     * @param view the view to be rendered at "/swagger"
     */
    public SwaggerBundle(final SwaggerView view) {
        this.view = view;
    }

    @Override
    public void initialize(final Bootstrap<?> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        //noinspection unchecked
        bootstrap.addBundle((ConfiguredBundle) new ConfiguredAssetsBundle(ROOT_ASSETS_PATH));
    }

    @Override
    public void run(final T configuration, final Environment environment) throws Exception {

        environment.jersey().register(new SwaggerResource(view));
        environment.jersey().register(new ApiListingResourceJSON());
        environment.jersey().register(new ApiDeclarationProvider());
        environment.jersey().register(new ResourceListingProvider());

        ConfigFactory.setConfig(configuration.getSwaggerConfiguration());

        ScannerFactory.setScanner(new DefaultJaxrsScanner());
        ClassReaders.setReader(new DefaultJaxrsApiReader());
    }

}
