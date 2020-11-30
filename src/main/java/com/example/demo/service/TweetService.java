package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.Tweet;
import com.example.demo.model.User;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class TweetService {
    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public List<Tweet> findByUserId(int user_id) {
        return tweetRepository.findByUser_Id(user_id);
    }

    public List<Tweet> findAll() { return tweetRepository.findAll(); }

    public List<Tweet> findInReverseOrder() {
        return tweetRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Tweet saveTweet(Tweet tweet, User currentUser) {

        tweet.setUser(currentUser);
        tweet.setPostDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+02:00"))));

        return tweetRepository.save(tweet);
    }
}
