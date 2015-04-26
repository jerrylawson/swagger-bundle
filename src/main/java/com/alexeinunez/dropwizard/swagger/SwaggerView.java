package com.alexeinunez.dropwizard.swagger;

import com.google.common.base.Charsets;
import io.dropwizard.views.View;

public abstract class SwaggerView extends View {

    private final String assetPath;

    protected SwaggerView(final String template, final String assetPath) {
        super(template, Charsets.UTF_8);
        this.assetPath = assetPath;
    }

    public String getAssetPath() {
        return this.assetPath;
    }
}
