package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.ProductDao;
import com.taba.inventory.entity.Product;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProductModel implements ProductDao {

    private static Session session;

    @Override
    public ObservableList<Product> getProducts() {

        ObservableList<Product> list = FXCollections.observableArrayList();
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);

        return list;
    }

    @Override
    public Product getProduct(long id) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();

        return product;
    }

    @Override
    public Product getProductByName(String productName) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product where productName=:name");
        query.setParameter("name", productName);
        Product product = (Product) query.uniqueResult();
        
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    @Override
    public void updateProduct(Product product) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setProductName(product.getProductName());
        p.setCategory(product.getCategory());
        p.setQuantity(product.getQuantity());
        p.setPrice(product.getPrice());
        p.setDescription(product.getDescription());
        session.getTransaction().commit();
    }
    
    @Override
    public void increaseProduct(Product product){
    
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setQuantity(product.getQuantity());
        session.getTransaction().commit();
    }
    
    @Override
    public void decreaseProduct(Product product){
    
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        p.setQuantity(product.getQuantity());
        session.getTransaction().commit();
    }

    @Override
    public void deleteProduct(Product product) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product p = session.get(Product.class, product.getId());
        session.delete(p);
        session.getTransaction().commit();
    }
    
//    @Override
//    public ObservableList<String> getProductNames2(){
//
//        session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        Criteria criteria = session.createCriteria(Product.class);
//        criteria.setProjection(Projections.property("productName"));
//        ObservableList<String> list = FXCollections.observableArrayList(criteria.list());
//        session.getTransaction().commit();
//
//        return list;
//    }

    @Override
    public ObservableList<String> getProductNames() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Usando CriteriaBuilder
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root.get("productName"));

        List<String> productList = session.createQuery(criteriaQuery).getResultList();
        ObservableList<String> list = FXCollections.observableArrayList(productList);

        session.getTransaction().commit();

        return list;
    }
}
