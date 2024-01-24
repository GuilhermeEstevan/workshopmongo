package com.spring.workshopmongo.repository;

import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {


}
