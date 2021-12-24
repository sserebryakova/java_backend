package com.geekbrains.backend.test.db;

import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.CategoriesExample;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;

import java.util.List;

public class TestDb {

    public static void main(String[] args) {
        DbService dbService = new DbService();
        ProductsMapper productsMapper = dbService.getProductsMapper();
        CategoriesMapper categoriesMapper = dbService.getCategoriesMapper();
        Categories category = categoriesMapper.selectByPrimaryKey(1L);
        System.out.println(category);

        CategoriesExample categoriesFilter = new CategoriesExample();
        Categories mensClothes = new Categories();
        mensClothes.setTitle("men`s сlothes");
        categoriesMapper.insert(mensClothes);
        categoriesFilter.createCriteria().andTitleEqualTo("men`s сlothes");
        mensClothes = categoriesMapper.selectByExample(categoriesFilter).get(0);
        categoriesFilter.clear();

        Categories womensClothes = new Categories();
        womensClothes.setTitle("women`s сlothes");
        categoriesMapper.insert(womensClothes);
        categoriesFilter.createCriteria().andTitleEqualTo("women`s сlothes");
        womensClothes = categoriesMapper.selectByExample(categoriesFilter).get(0);
        categoriesFilter.clear();

        Categories babyClothes = new Categories();
        babyClothes.setTitle("baby сlothes");
        categoriesMapper.insert(babyClothes);
        categoriesFilter.createCriteria().andTitleEqualTo("baby сlothes");
        babyClothes = categoriesMapper.selectByExample(categoriesFilter).get(0);
        categoriesFilter.clear();

        Categories cosmetics = new Categories();
        cosmetics.setTitle("cosmetics");
        categoriesMapper.insert(cosmetics);
        categoriesFilter.createCriteria().andTitleEqualTo("cosmetics");
        cosmetics = categoriesMapper.selectByExample(categoriesFilter).get(0);
        categoriesFilter.clear();

        categoriesFilter.createCriteria();

        System.out.println(categoriesMapper.selectByExample(categoriesFilter));

        Products downJacket = new Products();
        downJacket.setTitle("down jacket");
        downJacket.setPrice(15000);
        downJacket.setCategoryId(mensClothes.getId());

        Products trousers = new Products();
        trousers.setTitle("trousers");
        trousers.setPrice(3500);
        trousers.setCategoryId(mensClothes.getId());

        Products shirt = new Products();
        shirt.setTitle("shirt");
        shirt.setPrice(2500);
        shirt.setCategoryId(mensClothes.getId());

        Products furСoat = new Products();
        furСoat.setTitle("fur coat");
        furСoat.setPrice(150000);
        furСoat.setCategoryId(womensClothes.getId());

        Products dress = new Products();
        dress.setTitle("dress");
        dress.setPrice(9500);
        dress.setCategoryId(womensClothes.getId());

        Products hat = new Products();
        hat.setTitle("hat");
        hat.setPrice(3000);
        hat.setCategoryId(womensClothes.getId());

        Products jacket = new Products();
        jacket.setTitle("jacket");
        jacket.setPrice(4500);
        jacket.setCategoryId(babyClothes.getId());

        Products suit = new Products();
        suit.setTitle("suit");
        suit.setPrice(2500);
        suit.setCategoryId(babyClothes.getId());

        Products cap = new Products();
        cap.setTitle("cap");
        cap.setPrice(1000);
        cap.setCategoryId(babyClothes.getId());

        Products cream = new Products();
        cream.setTitle("cream");
        cream.setPrice(5500);
        cream.setCategoryId(cosmetics.getId());

        Products perfume = new Products();
        perfume.setTitle("perfume");
        perfume.setPrice(6500);
        perfume.setCategoryId(cosmetics.getId());

        Products shampoo = new Products();
        shampoo.setTitle("shampoo");
        shampoo.setPrice(800);
        shampoo.setCategoryId(cosmetics.getId());

        productsMapper.insert(downJacket);
        productsMapper.insert(trousers);
        productsMapper.insert(shirt);
        productsMapper.insert(furСoat);
        productsMapper.insert(dress);
        productsMapper.insert(hat);
        productsMapper.insert(jacket);
        productsMapper.insert(suit);
        productsMapper.insert(cap);
        productsMapper.insert(cream);
        productsMapper.insert(perfume);
        productsMapper.insert(shampoo);

        ProductsExample filter = new ProductsExample();
        List<Products> products = productsMapper.selectByExample(filter);
        System.out.println(products);

        filter.createCriteria()
                .andCategoryIdEqualTo(mensClothes.getId());

        System.out.println(productsMapper.selectByExample(filter));
        filter.clear();

        filter.createCriteria()
                .andCategoryIdEqualTo(womensClothes.getId());

        System.out.println(productsMapper.selectByExample(filter));
        filter.clear();

        filter.createCriteria()
                .andCategoryIdEqualTo(babyClothes.getId());

        System.out.println(productsMapper.selectByExample(filter));
        filter.clear();

        filter.createCriteria()
                .andCategoryIdEqualTo(cosmetics.getId());

        System.out.println(productsMapper.selectByExample(filter));
        filter.clear();

        filter.setOrderByClause("Price" + " DESC");

        System.out.println(productsMapper.selectByExample(filter));

        dbService.closeSession();
    }

}