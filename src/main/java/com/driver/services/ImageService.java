package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description,dimensions);
        image.setBlog(blog);
        Image newImage = imageRepository2.save(image);
        return newImage;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String imageDimension = image.getDimensions();

        String[] dimension = imageDimension.split(",");
        int dimensionSize = Integer.parseInt(dimension[0]) * Integer.parseInt(dimension[1]);

        dimension = screenDimensions.split(",");
        int screenDimension = Integer.parseInt(dimension[0]) * Integer.parseInt(dimension[1]);

        return screenDimension/dimensionSize;
    }
}
