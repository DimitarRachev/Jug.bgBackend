package bg.jug.service;

import bg.jug.model.dto.TopicResponse;

import java.util.List;

public interface TopicService {
    List<TopicResponse> getAllTopics();
}
