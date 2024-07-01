package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.SaleDao;
import com.taba.inventory.entity.Sale;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class SalesModel implements SaleDao {

    private static Session session;

    @Override
    public ObservableList<Sale> getSales() {

        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Sale> products = session.createQuery("from Sale").list();
        session.beginTransaction().commit();
        products.stream().forEach(list::add);

        return list;
    }

//    @Override
//    public ObservableList<Sale> getSaleByProductId(long id) {
//
//        ObservableList<Sale> list = FXCollections.observableArrayList();
//
//        session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        List<Sale> products = (List<Sale>) session.createCriteria(Sale.class)
//                .add(Restrictions.eq("product.id", id)).list();
//
//        session.beginTransaction().commit();
//        products.stream().forEach(list::add);
//
//        return list;
//    }

    @Override
    public ObservableList<Sale> getSaleByProductId(long id) {

        ObservableList<Sale> list = FXCollections.observableArrayList();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Usando CriteriaBuilder
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Sale> criteriaQuery = criteriaBuilder.createQuery(Sale.class);
        Root<Sale> root = criteriaQuery.from(Sale.class);

        // Agregando restricción para filtrar por id del producto
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("product").get("id"), id));

        List<Sale> products = session.createQuery(criteriaQuery).getResultList();

        session.getTransaction().commit();
        products.forEach(list::add);

        return list;
    }


    @Override
    public Sale getSale(long id) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale sale = session.get(Sale.class, id);
        session.getTransaction().commit();

        return sale;
    }

    @Override
    public void saveSale(Sale sale) {

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(sale);
        session.getTransaction().commit();
    }

    @Override
    public void updateSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        s.setProduct(sale.getProduct());
        s.setQuantity(sale.getQuantity());
        s.setPrice(sale.getPrice());
        s.setTotal(sale.getTotal());
        s.setDate(sale.getDate());
        session.getTransaction().commit();
    }

    @Override
    public void deleteSale(Sale sale) {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Sale s = session.get(Sale.class, sale.getId());
        session.delete(s);
        session.getTransaction().commit();
    }

}
