package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.PurchaseDao;
import com.taba.inventory.entity.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PurchaseModel implements PurchaseDao {

    @Override
    public ObservableList<Purchase> getPurchases() {
        ObservableList<Purchase> list = FXCollections.observableArrayList();
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<Purchase> purchases = session.createQuery("from Purchase", Purchase.class).list();
            purchases.forEach(list::add);
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
    public Purchase getPurchase(long id) {
        Transaction transaction = null;
        Purchase purchase = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            purchase = session.get(Purchase.class, id);
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

        return purchase;
    }

    @Override
    public void savePurchase(Purchase purchase) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(purchase);
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
    public void updatePurchase(Purchase purchase) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Purchase p = session.get(Purchase.class, purchase.getId());
            if (p != null) {
                p.setProduct(purchase.getProduct());
                p.setSupplier(purchase.getSupplier());
                p.setQuantity(purchase.getQuantity());
                p.setDate(purchase.getDate());
                session.update(p);
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
    public void deletePurchase(Purchase purchase) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Purchase p = session.get(Purchase.class, purchase.getId());
            if (p != null) {
                session.delete(p);
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
