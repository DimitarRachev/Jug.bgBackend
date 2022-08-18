package bg.jug.controller;

import bg.jug.model.dto.TopicDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics() {


    }
}
