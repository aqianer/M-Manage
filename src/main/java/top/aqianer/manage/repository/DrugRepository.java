package top.aqianer.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.aqianer.manage.model.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}