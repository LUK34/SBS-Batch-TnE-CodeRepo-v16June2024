package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.model.ExamResult;

public interface ExamResultRepo extends JpaRepository<ExamResult, Long> {

}
