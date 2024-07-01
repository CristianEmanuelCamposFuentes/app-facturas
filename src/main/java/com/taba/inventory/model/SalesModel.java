package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.SaleDao;
import com.taba.inventory.entity.Sale;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SalesModel implements SaleDao {

    @Override
    public ObservableList<Sale> getSales() {
        ObservableList<Sale> list = FXCollections.observableArrayList();
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<Sale> sales = session.createQuery("from Sale", Sale.class).list();
            sales.forEach(list::add);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return list;
    }

    @Override
    public ObservableList<Sale> getSaleByProductId(long id) {
        ObservableList<Sale> list = FXCollections.observableArrayList();
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Sale> criteriaQuery = criteriaBuilder.createQuery(Sale.class);
            Root<Sale> root = criteriaQuery.from(Sale.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("product").get("id"), id));
            List<Sale> sales = session.createQuery(criteriaQuery).getResultList();
            sales.forEach(list::add);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return list;
    }

    @Override
    public Sale getSale(long id) {
        Transaction transaction = null;
        Sale sale = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            sale = session.get(Sale.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return sale;
    }

    @Override
    public void saveSale(Sale sale) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(sale);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateSale(Sale sale) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Sale s = session.get(Sale.class, sale.getId());
            if (s != null) {
                s.setProduct(sale.getProduct());
                s.setQuantity(sale.getQuantity());
                s.setPrice(sale.getPrice());
                s.setTotal(sale.getTotal());
                s.setDate(sale.getDate());
                session.update(s);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteSale(Sale sale) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Sale s = session.get(Sale.class, sale.getId());
            if (s != null) {
                session.delete(s);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
