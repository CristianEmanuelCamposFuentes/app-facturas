package com.taba.inventory.model;

import com.taba.inventory.HibernateUtil;
import com.taba.inventory.dao.EmployeeDao;
import com.taba.inventory.entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeModel implements EmployeeDao {

    @Override
    public ObservableList<Employee> getEmployees() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            List<Employee> employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(list::add);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Employee getEmployee(long id) {
        Transaction transaction = null;
        Employee employee = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = session.get(Employee.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public String getEmployeeType(String username) {
        Transaction transaction = null;
        String type = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.createQuery("from Employee where userName = :username", Employee.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if (employee != null) {
                type = employee.getType();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return type;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee e = session.get(Employee.class, employee.getId());
            if (e != null) {
                e.setFirstName(employee.getFirstName());
                e.setLastName(employee.getLastName());
                e.setUserName(employee.getUserName());
                e.setPassword(employee.getPassword());
                e.setPhone(employee.getPhone());
                session.update(e);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee e = session.get(Employee.class, employee.getId());
            if (e != null) {
                session.delete(e);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkUser(String username) {
        Transaction transaction = null;
        boolean exists = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.createQuery("from Employee where userName = :username", Employee.class)
                    .setParameter("username", username)
                    .uniqueResult();
            exists = employee != null;
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        Transaction transaction = null;
        boolean matches = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.createQuery("from Employee where userName = :username", Employee.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if (employee != null) {
                matches = employee.getPassword().equals(password);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return matches;
    }
}
