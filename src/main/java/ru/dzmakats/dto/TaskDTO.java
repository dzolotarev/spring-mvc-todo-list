package ru.dzmakats.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.dzmakats.entity.Status;

/**
 * Created by Denis Zolotarev on 12.02.2024
 */

@Getter
@Setter
public class TaskDTO {

    private String description;
    private Status status;
}
