import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

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
