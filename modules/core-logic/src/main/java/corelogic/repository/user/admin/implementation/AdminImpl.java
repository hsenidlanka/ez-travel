package corelogic.repository.user.admin.implementation;

import corelogic.repository.user.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.Date;

/**
 * Created by Menuka on 12/20/17.
 */
@Repository
public class AdminImpl implements AdminRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public boolean registerAdmin(String email, String password, String first_name, String last_name, Date birthday, String gender, String nic, String contact_number, String super_admin_email) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sqlForGetAdminId = "SELECT admin_id FROM admin WHERE email = ?";
            Object[] args = new Object[]{super_admin_email};
            Integer admin_id = jdbcTemplate.queryForObject(sqlForGetAdminId, args, Integer.class);

            if (admin_id == 0) {
                throw new Exception("No admin found in that email");
            }

            String sqlForInsertAdmin = "INSERT INTO admin (email, password, first_name, last_name, birthday, contact_number, nic, gender, added_by,admin_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,1)";
            Object[] argsForAdminAdd = new Object[]{email, password, first_name, last_name, birthday, contact_number, nic, gender, admin_id};

            boolean isAdminAdded = jdbcTemplate.update(sqlForInsertAdmin, argsForAdminAdd) == 1;

            transactionManager.commit(status);
            return isAdminAdded;

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }

    @Override
    public boolean banAdmin(String super_admin_email, int admin_id) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sqlForGetAdminId = "SELECT admin_id FROM admin WHERE email = ?";
            Object[] args = new Object[]{super_admin_email};
            Integer super_admin_id = jdbcTemplate.queryForObject(sqlForGetAdminId, args, Integer.class);

            if (super_admin_id == 0) {
                throw new Exception("No admin found in that email");
            }

            String sqlForBanAdmin = "UPDATE admin set admin_status=0 where admin_id = ?";
            Object[] argsForAdminBan = new Object[]{admin_id};

            boolean isHirePlacementConfirmed = (jdbcTemplate.update(sqlForBanAdmin, argsForAdminBan) == 1);
            transactionManager.commit(status);

            return isHirePlacementConfirmed;
        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }
        return false;
    }

    @Override
    public boolean confirmDriver(String admin_email, String driver_id) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sqlForGetAdminId = "SELECT admin_id FROM admin WHERE email = ?";
            Object[] args = new Object[]{admin_email};
            Integer super_admin_id = jdbcTemplate.queryForObject(sqlForGetAdminId, args, Integer.class);

            if (super_admin_id == 0) {
                throw new Exception("No admin found in that email");
            }

            String sqlForConfirmDriver = "UPDATE driver set driver_status=1, confirmed_by=? where driver_id = ?";
            Object[] argsForDriverConfirm = new Object[]{super_admin_id, driver_id};

            boolean isDriverConfirmed = (jdbcTemplate.update(sqlForConfirmDriver, argsForDriverConfirm) == 1);
            transactionManager.commit(status);

            return isDriverConfirmed;

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }
}
