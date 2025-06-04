
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;

//
//import java.util.List;
//
//@RestController
//@RequestMapping("/grades")
//public class GradeController {
//
//    private final GradeService gradeService;
//
//    @Autowired
//    public GradeController(GradeService gradeService) {
//        this.gradeService = gradeService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Grade>> getAllGrades() {
//        List<Grade> grades = gradeService.getAllGrades();
//        return new ResponseEntity<>(grades, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
//        Grade grade = gradeService.getGradeById(id);
//        return new ResponseEntity<>(grade, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
//        Grade createdGrade = gradeService.createGrade(grade);
//        return new ResponseEntity<>(createdGrade, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody Grade updatedGrade) {
//        Grade grade = gradeService.updateGrade(id, updatedGrade);
//        return new ResponseEntity<>(grade, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
//        gradeService.deleteGrade(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//}
