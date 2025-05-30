package top.aqianer.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.aqianer.manage.model.LabMember;
import top.aqianer.manage.model.Laboratory;

public interface LabRepository extends JpaRepository<Laboratory, Long> {
}