package gradingsystem.model;

import jakarta.persistence.*;

@Entity
@Table(name="subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @ManyToOne
//    @JoinColumn(name = "class")
    private StudentClass studentClass;


    public Subject() {
    }

    public Subject(String subjectName, Teacher teacher, StudentClass studentClass) {
        this.subjectName = subjectName;
        this.teacher = teacher;
        this.studentClass = studentClass;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }
}



