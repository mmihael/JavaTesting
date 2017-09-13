package test.hibernate;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by mmamula on 10.12.2016.
 */
public class Hibernate {

    public static void test() {



    }

}

@Entity
@Table(name = "test")
class Test {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "string")
    private String string;

    @Column(name = "number")
    private Integer number;

    @Column(name = "date")
    private Date date;

    public Test() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}