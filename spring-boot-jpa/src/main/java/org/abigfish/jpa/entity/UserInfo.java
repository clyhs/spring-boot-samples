package org.abigfish.jpa.entity;
 
import javax.persistence.*;
 

 
@Entity
public class UserInfo {
 
    private Integer id;
    private String name;
    private String sex;
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
    @Column
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getSex() {
        return sex;
    }
 
    public void setSex(String sex) {
        this.sex = sex;
    }
 
    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
