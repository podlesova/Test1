package main.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private  String id;
    @Column(nullable = false)
    private String email;
    private String name;
    @Column(name = "is_admin",nullable = false)
    private boolean isAdmin;
    @Column(nullable = false)
    private String password;

    public Role getRole(){
        return isAdmin ? Role.ADMIN : Role.USER;
    }
}
