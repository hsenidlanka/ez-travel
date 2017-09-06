package Customer;

import com.sun.javafx.beans.IDProperty;

import java.util.Date;

/**
 * Created by hsenid on 9/4/17.
 *//*
@Entity
@Table("<TableName>")*/
public class User {

   /* @ID
    @GenaratedValue(strategy=GenerationType.Auto)
    */

   private Long id;

   private String firstName;

   private String lastName;

   private String mobileNumber;

   private Date dateOfBirth;

   private String nic;

   private String email;
  // @Column(length=60)
   private String password;

   private String confirmPasword;

}
