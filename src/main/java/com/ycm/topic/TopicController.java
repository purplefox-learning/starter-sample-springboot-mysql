package com.ycm.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

@Slf4j
@RestController
public class TopicController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/topics")
    public Iterable<Topic> getAllTopics() {
        log.info("getAllTopics begins");
        log.info("getAllTopics testing logging system");
        log.info("getAllTopics ends");
        return topicService.findAll();
    }

    //@PathVariable establishes the mapping from the requested url to the method argument
    @RequestMapping("/topics/{id}")
    public Optional<Topic> getTopic(@PathVariable Integer id) {
        return topicService.findById(id);
    }

    //@RequestBody take the http payload (rest data) of the incoming request
    //then convert it to a topic object
    @RequestMapping(method=RequestMethod.POST, value="/topics")
    public void addTopicViaHttpPost(@RequestBody Topic topic) {
        topicService.add(topic);
    }

    //note that this is not a standard approach, just to illustrate the usage of "RequestParam"
    @RequestMapping(method=RequestMethod.GET, value="/topics/new")
    public void addTopicViaHttpGet (@RequestParam("name") String topicName, @RequestParam("desc") String topicDescription) {
        Topic t = new Topic();
        t.setName(topicName);
        t.setDescription(topicDescription);
        topicService.add(t);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/topics")
    public void updateTopic(@RequestBody Topic topic) {
        topicService.update(topic);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
    public void deleteTopic(@PathVariable Integer id) {
        topicService.deleteById(id);
    }
}

