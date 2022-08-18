package bg.jug.service.impl;

import bg.jug.model.dto.TopicDto;
import bg.jug.model.entity.Topic;
import bg.jug.repository.TopicRepository;
import bg.jug.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
private final TopicRepository topicRepository;
private final ModelMapper mapper;

    @Override
    public List<TopicDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        return topics.stream()
                .map(t -> mapper.map(t, TopicDto.class))
                .collect(Collectors.toList());
    }
}
