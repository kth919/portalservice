package kr.ac.jejunu.repository;

import kr.ac.jejunu.model.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by admin on 2016-06-07.
 */
 public interface CommentRepository extends CrudRepository<Comment, Integer> {

}

