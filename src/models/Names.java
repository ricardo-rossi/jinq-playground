package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Names {
   private int id;
   private String firstName;
   private String lastName;

   @Id
   @Column(name = "ID")
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Basic
   @Column(name = "FIRST_NAME")
   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   @Basic
   @Column(name = "LAST_NAME")
   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Names names = (Names) o;

      if (id != names.id) return false;
      if (firstName != null ? !firstName.equals(names.firstName) : names.firstName != null) return false;
      if (lastName != null ? !lastName.equals(names.lastName) : names.lastName != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = id;
      result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
      result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
      return result;
   }
}
