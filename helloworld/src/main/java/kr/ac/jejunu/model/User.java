package kr.ac.jejunu.model;

import java.io.Serializable;

/**
 * Created by admin on 2016-06-05.
 */
 public class User implements Serializable {

                 private Integer id;
         private String name;
         private String password;

               public Integer getId() {
                return id;
            }

                public void setId(Integer id) {
                 this.id = id;
             }

                 public String getName() {
                 return name;
             }

                 public void setName(String name) {
                 this.name = name;
             }

                 public String getPassword() {
                 return password;
             }

                 public void setPassword(String password) {
                 this.password = password;
             }
     }

