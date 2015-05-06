# swagger-bundle

A [Dropwizard](http://dropwizard.io) bundle which provides functionality to expose a [Swagger](http://swagger.org)
specification and a [Swagger UI](https://github.com/swagger-api/swagger-ui) view to interact with Swagger [annotated resources](https://github.com/swagger-api/swagger-core/wiki/JavaDropwizard-Quickstart#annotate-your-resources).

## Quickstart

Add this project to your project's dependencies (this artifact is currently unavailable).

### Specify a Configuration

```java
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
```

### Specify a Configuration File

Reference [SwaggerConfiguration](https://github.com/arnm/swagger-bundle/blob/master/src/main/java/com/alexeinunez/dropwizard/swagger/SwaggerConfiguration.java) and its parent for all the possible fields which can be specified.

```yaml
swaggerConfiguration:
    basePath: http://localhost:8080
    apiVersion: 0.1
```

### Specify a Model

```java
@ApiModel(value = "A test model")
public class TestModel {

    @ApiModelProperty(value = "A test message", required = true)
    private final String message;

    public TestModel(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
```

### Specify a Resource

```java
@Path("/test")
@Api(value = "/test", description = "Test resource")
@Produces({MediaType.APPLICATION_JSON})
public class TestResource {

    @GET
    @Path("/{message}")
    @ApiOperation(
           value = "get a model",
           response = TestModel.class
    )
    public TestModel getModel(
            @ApiParam(value = "Specify a message")
            @PathParam("message") final String message
    ) {
        return new TestModel(message);
    }
}
```

### Add the Bundle to the Application

```java
public class TestApplication extends Application<TestConfiguration> {

    public static void main(String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new SwaggerBundle());
    }

    @Override
    public void run(final TestConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().register(new TestResource());
    }
```

That is all you need to get up and running! Fire up the application and visit
`http://localhost:8080/api-docs` to view the Swagger Spec and visit `http://localhost:8080/swagger` to view the interactive UI.

This sample application can be found in the [test directory](https://github.com/arnm/swagger-bundle/tree/master/src/test).

## Configuring `/swagger`

By default, creating a [SwaggerBundle](https://github.com/arnm/swagger-bundle/blob/master/src/main/java/com/alexeinunez/dropwizard/swagger/SwaggerBundle.java) will expose a [DefaultSwaggerView](https://github.com/arnm/swagger-bundle/blob/master/src/main/java/com/alexeinunez/dropwizard/swagger/DefaultSwaggerView.java) which renders a Swagger UI page for the application's Swagger Spec.

There are two ways to control what the `/swagger` endpoint renders; They are documented below.

### Configuring the Default SwaggerView

This method allows you to continue using Swagger UI with the added ability to modify parts of it. You can specify several things, like the page title or the Swagger Spec it should be using, or even wether or not the UI should be able to display more than one API.

```java
public class TestApplication extends Application<TestConfiguration> {

    public static void main(String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
      final DefaultSwaggerView defaultSwaggerView = new DefaultSwaggerView();
      defaultSwaggerView.setTitle("Test UI");
      defaultSwaggerView.setDisplayApiSelector(false);
      // defaultSwaggerView.setUrl("http://petstore.swagger.io/v2/swagger.json")
      
      bootstrap.addBundle(new ViewBundle());
      bootstrap.addBundle(new SwaggerBundle(defaultSwaggerView));
    }

    @Override
    public void run(final TestConfiguration configuration, final Environment environment) throws Exception {
      environment.jersey().register(new TestResource());
    }
```

### Specify a Custom SwaggerView

This method allows you to specify a custom view for the `/swagger` endpoint. In order to do this a couple of more things are needed.

#### Create a Mustache template

This bundle uses [Mustache templates](https://mustache.github.io/) for displaying views. Create `index.mustache` in the application's `resources`.

```html
<html>
    <head>
    </head>
    <body>
        <!--Values specified in SwaggerView by default-->
        <p>{{swaggerSpecUrl}}</p>
        <p>{{assetsPath}}</p>
    </body>
</html>
```

#### Specify a Custom SwaggerView

```java
public class TestSwaggerView extends SwaggerView {

    protected TestSwaggerView() {
        super("/index.mustache", "/api-docs");
    }

}
```

### Add Bundle to Application

```java
public class TestApplication extends Application<TestConfiguration> {

    public static void main(String[] args) throws Exception {
        new TestApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<TestConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new SwaggerBundle(new TestSwaggerView()));
    }

    @Override
    public void run(final TestConfiguration configuration, final Environment environment) throws Exception {
        environment.jersey().register(new TestResource());
    }
}
```

Visit `http://localhost:8080/swagger` to see your new view in action.
