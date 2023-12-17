package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.CategoryService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.SuccessDataResult;
import com.said.palidmarketapp.dataAccess.abstracts.CategoryDao;
import com.said.palidmarketapp.entities.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryManager implements CategoryService {
    private final CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }
}
