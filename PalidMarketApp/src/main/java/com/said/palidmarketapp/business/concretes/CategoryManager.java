package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.CategoryService;
import com.said.palidmarketapp.core.utilities.results.*;
import com.said.palidmarketapp.dataAccess.abstracts.CategoryDao;
import com.said.palidmarketapp.entities.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public DataResult<Category> saveCategory(Category category) {
        log.info("application.saveCategory");
        return new SuccessDataResult<>(categoryDao.save(category), "Category is saved successfully");
    }

    @Override
    public DataResult<List<Category>> getAllCategory() {
        log.info("application.getAllCategory.start");
        List<Category> categories = categoryDao.findAll();
        log.info("application.getAllCategory.end.successfully");
        return new SuccessDataResult<>(categories, "Getting categories is successfully");

    }

    @Override
    public Result updateImg(int id, String img) {
        log.info("updateImg.start");

        Optional<Category> optionalCategory = categoryDao.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setImage(img);
            categoryDao.saveAndFlush(category);
            log.info("updateImg.end.successfully");
            return new SuccessResult("Image update successfully");
        } else {
            log.info("updateImg.end.unsuccessfully");
            return new ErrorResult("Category not found with id: " + id);
        }
    }

    @Override
    public Result updateName(int id, String name) {
        log.info("updateName.start");

        Optional<Category> optionalCategory = categoryDao.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(name);
            categoryDao.saveAndFlush(category);
            log.info("updateName.end.successfully");
            return new SuccessResult("Name update successfully");
        } else {
            log.info("update.end.unsuccessfully");
            return new ErrorResult("Category not found with id: " + id);
        }
    }


    @Override
    public Result deleteCategory(Integer id) {
        log.info("application.deleteCategory.start");
        if(categoryDao.existsById(id)){
            categoryDao.deleteById(id);
            log.info("application.deleteCategory.end.successfully");
            return new SuccessResult("Category is deleted successfully");
        }
        else {
            log.info("application.deleteCategory.end.unsuccessfully");
            return new ErrorResult("Category is not found with the given id");
        }
    }

}
