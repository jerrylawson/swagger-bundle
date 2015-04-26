package com.alexeinunez.dropwizard.swagger;

public class DefaultSwaggerView extends SwaggerView {

    private String title = "Swagger UI";
    private boolean displayApiSelector = false;

    public DefaultSwaggerView() {
        super("index.mustache", SwaggerBundle.defaultAssetPath + "/default-swagger-ui");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDisplayApiSelector() {
        return displayApiSelector;
    }

    public void setDisplayApiSelector(boolean displayApiSelector) {
        this.displayApiSelector = displayApiSelector;
    }
}
