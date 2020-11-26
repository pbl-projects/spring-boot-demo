package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @Length(min = 5, max = 140, message = "*Tweets must have 5 to 140 characters")
    @NotEmpty(message = "*Please type your tweet")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
}
