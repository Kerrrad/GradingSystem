package gradingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name="class")
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String name;


    public StudentClass() {
    }

    public StudentClass(String name) {

        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
