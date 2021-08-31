package com.example.competition.repositories;

import com.example.competition.models.entity.Student;
import com.example.competition.models.entity.enums.Iteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE s.iteration= :phase  order by s.panel asc, s.averageGrade desc ")
    List<Student> findAllByPhaseByTotalGradeDesc(@Param("phase") Iteration phase );

    @Query("SELECT s FROM Student s WHERE s.iteration= :phase order by s.orderOfPitch asc")
    LinkedList<Student> findAllByOrderByPhase(@Param("phase") Iteration phase );

    Optional<Student> findByName (String studentName);
}
