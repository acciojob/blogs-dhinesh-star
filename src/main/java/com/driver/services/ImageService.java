package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Optional<Blog> optionalBlog = blogRepository2.findById(blogId);
//        if(optionalBlog.isEmpty()){
//            throw new Exception("Blog with given id not found");
//        }
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description,dimensions);

        blog.getImageList().add(image);
        image.setBlog(blog);
        blogRepository2.save(blog);
//        Image newImage = imageRepository2.save(image);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String imageDimension = image.getDimensions();

        String[] dimension = imageDimension.split("X");
        int dimensionSize = Integer.parseInt(dimension[0]) * Integer.parseInt(dimension[1]);

        dimension = screenDimensions.split("X");
        int screenDimension = Integer.parseInt(dimension[0]) * Integer.parseInt(dimension[1]);

        return screenDimension/dimensionSize;
    }
}
