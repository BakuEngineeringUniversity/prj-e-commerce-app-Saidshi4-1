package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.entities.Category;

import java.util.List;

public interface CategoryService {
   DataResult<List<Category>> getAllCategory();
}
