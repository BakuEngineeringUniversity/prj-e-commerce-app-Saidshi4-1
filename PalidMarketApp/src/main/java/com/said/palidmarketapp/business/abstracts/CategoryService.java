package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.entities.Category;

import java.util.List;

public interface CategoryService {
   DataResult<Category> saveCategory(Category category);
   DataResult<List<Category>> getAllCategory();
   Result updateImg(int id, String img);
   Result deleteCategory(Integer id);
}
