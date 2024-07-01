package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.SupplierDao;
import com.taba.inventory.entity.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class SupplierModel implements SupplierDao {

    @Override
    public ObservableList<Supplier> getSuppliers() {
        ObservableList<Supplier> list = FXCollections.observableArrayList();
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<Supplier> suppliers = session.createQuery("from Supplier", Supplier.class).list();
            suppliers.forEach(list::add);
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
    public Supplier getSupplier(long id) {
        Transaction transaction = null;
        Supplier supplier = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            supplier = session.get(Supplier.class, id);
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

        return supplier;
    }

    @Override
    public void saveSuplier(Supplier supplier) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(supplier);
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
    public void updateSuplier(Supplier supplier) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Supplier s = session.get(Supplier.class, supplier.getId());
            if (s != null) {
                s.setName(supplier.getName());
                s.setPhone(supplier.getPhone());
                s.setAddress(supplier.getAddress());
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
    public void deleteSuplier(Supplier supplier) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Supplier s = session.get(Supplier.class, supplier.getId());
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

    @Override
    public ObservableList<String> getNames() {
        ObservableList<String> list = FXCollections.observableArrayList();
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
            Root<Supplier> root = criteriaQuery.from(Supplier.class);
            criteriaQuery.select(root.get("name"));
            List<String> names = session.createQuery(criteriaQuery).getResultList();
            list.addAll(names);
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
}
