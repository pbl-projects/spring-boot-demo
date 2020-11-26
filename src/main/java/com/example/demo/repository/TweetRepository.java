package com.example.demo.repository;

import com.example.demo.model.Tweet;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByUser_Id(int user_id);
    List<Tweet> findAll();
}
