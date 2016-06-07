package kr.ac.jejunu.spring;

import kr.ac.jejunu.model.Comment;
import kr.ac.jejunu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by admin on 2016-06-07.
 */
 @Controller
 @RequestMapping("/data")
 public class SpringDataJpaController {

                 @Autowired
                 CommentRepository commentRepository;

                @RequestMapping("/list")
         public List<Comment> list() {
                 return (List<Comment>) commentRepository.findAll();
            }
     }

