package bg.jug.model.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Data
public class SimpleEventDto {
    private Integer id;

    private String name;

    private Date date;
}
