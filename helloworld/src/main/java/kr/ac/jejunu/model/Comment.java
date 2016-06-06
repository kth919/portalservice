package kr.ac.jejunu.model;

import javax.persistence.*;

/**
 * Created by admin on 2016-06-06.
 */

    @Entity
    public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private User user;
        private String content;


        public Comment(String content, User user, Integer id) {
            this.content = content;
            this.user = user;
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }

