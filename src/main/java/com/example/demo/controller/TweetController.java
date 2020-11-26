package com.example.demo.controller;


import com.example.demo.model.Tweet;
import com.example.demo.model.User;
import com.example.demo.service.TweetService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class TweetController {

    @Autowired
    private TweetService tweetService;
    @Autowired
    private UserService userService;

    @RequestMapping(value="/feed", method = RequestMethod.GET)
    public ModelAndView feed(){
        ModelAndView modelAndView = new ModelAndView();
        List<Tweet> tweets = tweetService.findAll();
        modelAndView.addObject("tweets", tweets);
        modelAndView.setViewName("feed");
        return modelAndView;
    }

    @RequestMapping(value = "/tweet", method = RequestMethod.POST)
    public RedirectView createNewTweet(@Valid Tweet tweet, BindingResult bindingResult, Principal principal, HttpSession session) {
        if (bindingResult.hasErrors()) {
            session.setAttribute("binding",bindingResult);
        }
        else {
            User currentUser = userService.findUserByUserName(principal.getName());
            tweetService.saveTweet(tweet, currentUser);
        }
        return new RedirectView("feed");
    }

    @RequestMapping(value="/profile", method = RequestMethod.GET)
    public ModelAndView profile(Principal principal){

        System.err.println(principal.getName());
        User currentUser = userService.findUserByUserName(principal.getName());
        if(currentUser == null) {
            System.err.println("Not found");
        }
        else {
            System.err.println("OK");
        }


        ModelAndView modelAndView = new ModelAndView();
        // List<Tweet> tweets = tweetService.findAll();
        Set<Tweet> tweets = currentUser.getTweets();
        modelAndView.addObject("tweets", tweets);
        modelAndView.setViewName("profile");
        return modelAndView;
    }

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public ModelAndView user(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        List<Tweet> tweets = tweetService.findByUserId(id);
        modelAndView.addObject("tweets", tweets);
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}
