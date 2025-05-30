package top.aqianer.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.aqianer.manage.model.Drug;
import top.aqianer.manage.model.LabMember;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<LabMember, Long> {
    Optional<LabMember> findByPhone(String phone);

    Optional<LabMember> findByEmail(String email);
}