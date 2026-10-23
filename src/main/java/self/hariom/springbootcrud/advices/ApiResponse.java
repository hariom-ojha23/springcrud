package self.hariom.springbootcrud.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    @JsonFormat(pattern = "hh:mm:ss, dd-MM-yyyy")
    private LocalDateTime timestamps;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timestamps = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();                   // calling default constructor to set timestamp
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();                   // calling default constructor to set timestamp
        this.error = error;
    }
}
