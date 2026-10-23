package self.hariom.springbootcrud.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private Integer statusCode;
    private String message;
    private List<String> errors;
    private String path;
}
