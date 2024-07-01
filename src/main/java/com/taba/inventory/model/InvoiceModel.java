package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.InvoiceDao;
import com.taba.inventory.entity.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InvoiceModel implements InvoiceDao {

    @Override
    public ObservableList<Invoice> getInvoices() {
        ObservableList<Invoice> list = FXCollections.observableArrayList();
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            List<Invoice> invoices = session.createQuery("from Invoice", Invoice.class).list();
            invoices.forEach(list::add);
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
    public Invoice getInvoice(String id) {
        Transaction transaction = null;
        Invoice invoice = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            invoice = session.get(Invoice.class, id);
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

        return invoice;
    }

    @Override
    public void saveInvoice(Invoice invoice) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(invoice);
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
    public void deleteCategory(Invoice invoice) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Invoice i = session.get(Invoice.class, invoice.getId());
            session.delete(i);
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
