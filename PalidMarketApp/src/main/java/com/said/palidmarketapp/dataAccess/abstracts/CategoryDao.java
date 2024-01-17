package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {

}
