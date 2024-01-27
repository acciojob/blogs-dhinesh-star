package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    UserRepository userRepository1;

    public Blog createAndReturnBlog(Integer userId, String title, String content){
        //create a blog at the current time
        Optional<User> userList = userRepository1.findById(userId);
//        if(userList==null){
//            throw new Exception("User will given Id not found");
//        }
        User user = userList.get();
        Blog blog = new Blog(title,content);
        blog.setUser(user);
        user.getBlogList().add(blog);
        Blog newBlogAdded = blogRepository1.save(blog);
        return newBlogAdded;
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        blogRepository1.deleteById(blogId);
    }
}
