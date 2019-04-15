package com.ycm.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Iterable<Topic> findAll() {
        return topicRepository.findAll();
    }

    public Optional<Topic> findById(Integer id) {
        return topicRepository.findById(id);
    }

    public void add(Topic t) {
        topicRepository.save(t);
    }

    public void update(Topic t) {
        topicRepository.save(t);
    }

    public void delete(Topic t) {
        topicRepository.delete(t);
    }

    public void deleteById(Integer id) {
        topicRepository.deleteById(id);
    }
}
