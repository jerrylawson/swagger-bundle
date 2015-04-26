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

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import io.dropwizard.views.View;

/**
 * The base class a view must implement for use with the {@link com.alexeinunez.dropwizard.swagger.SwaggerBundle}.
 */
public abstract class SwaggerView extends View {

    private String assetsPath;
    private String url;

    /**
     * Creates a new view. The mustache template used to render this view has available all of the public "get" methods
     * in this class. Defaults assetPath to "/assets/".
     *
     * @param template the mustache template to render the view with
     * @param url the URL used to obtain a Swagger Spec
     */
    protected SwaggerView(final String template, final String url) {
        this(template, "", url);
    }

    /**
     * Creates a new view. The mustache template used to render this view has available all of the public "get" methods
     * in this class.
     *
     * @param template the mustache template to render the view with
     * @param assetsPath the location of the assets, relative to the application's assets directory, to be used
     *                         by the rendered view
     * @param url the URL used to obtain a Swagger Spec
     */
    protected SwaggerView(final String template, final String assetsPath, final String url) {
        super(template, Charsets.UTF_8);

        Preconditions.checkNotNull(assetsPath, "An asset path must be defined");
        Preconditions.checkNotNull(url, "A URL must be defined");

        this.assetsPath = SwaggerBundle.ROOT_ASSETS_PATH + assetsPath;
        this.url = url;
    }

    public String getAssetsPath() {
        return assetsPath;
    }

    public void setAssetsPath(final String assetsPath) {
        Preconditions.checkNotNull(assetsPath, "An asset path must be defined");
        this.assetsPath = assetsPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        Preconditions.checkNotNull(url, "A URL must be defined");
        this.url = url;
    }

}
