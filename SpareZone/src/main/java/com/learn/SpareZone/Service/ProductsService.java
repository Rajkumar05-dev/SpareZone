package com.learn.SpareZone.Service;

import java.util.List;

import com.learn.SpareZone.Dtos.ProductsDto;

public interface ProductsService {
List<ProductsDto> serachProduct(String name);
}
