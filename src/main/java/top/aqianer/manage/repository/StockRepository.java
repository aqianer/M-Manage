package top.aqianer.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.aqianer.manage.model.Stock;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByLaboratory_Id(Long labId);
}