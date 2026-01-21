package com.learn.SpareZone.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.learn.SpareZone.Entities.Category;

@RepositoryRestResource(path = "categorys")
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
