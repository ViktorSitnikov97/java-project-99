package hexlet.code.dto.taskStatuses;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskStatusCreateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String slug;
}
