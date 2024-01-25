package com.spring.workshopmongo.resources;

import com.spring.workshopmongo.domain.Post;
import com.spring.workshopmongo.domain.User;
import com.spring.workshopmongo.dto.UserDTO;
import com.spring.workshopmongo.resources.util.URL;
import com.spring.workshopmongo.services.PostService;
import com.spring.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResources {

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
    public ResponseEntity<List<Post>>
    findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        text = URL.decodeParam(text);
        LocalDate min = URL.convertDate(minDate, LocalDate.of(1970, 1, 1));
        LocalDate localDate = URL.convertDateToLocalDate(new Date());
        LocalDate max = URL.convertDate(maxDate, localDate);
        List<Post> posts = service.fullSearch(text, min, max);
        return ResponseEntity.ok().body(posts);
    }

}
