package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.entities.Category;
import jdk.dynalink.linker.LinkerServices;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
}
