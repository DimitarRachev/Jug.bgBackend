package bg.jug.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class TopicResponse {

   private Integer id;
    private String title;
    private List<SimpleEventDto> events;
}
