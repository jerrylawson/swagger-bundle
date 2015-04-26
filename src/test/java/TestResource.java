import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
