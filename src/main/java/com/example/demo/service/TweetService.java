package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.Tweet;
import com.example.demo.model.User;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Tweet saveTweet(Tweet tweet, User currentUser) {

        tweet.setUser(currentUser);

        return tweetRepository.save(tweet);
    }
}
