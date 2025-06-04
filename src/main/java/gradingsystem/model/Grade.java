package gradingsystem.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject")
    private Subject subject;

    private int grade;

    private Date date;

    public Grade() {
    }

    public Grade(Student student, Teacher teacher, Subject subject, int grade, Date date) {
        this.student = student;
        this.teacher = teacher;
        this.subject = subject;
        this.grade = grade;
        this.date = date;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getStudentId() {
        return this.student.getId();
    }

    public Long getSubjectId() {
        return this.subject.getId();
    }
}
