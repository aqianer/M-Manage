package top.aqianer.manage.service;

import top.aqianer.manage.model.Drug;
import top.aqianer.manage.vo.Chemical;

import java.util.List;


public interface ChemicalService {
    List<Chemical> findAll(Long labId);

    Drug save(Drug chemical);
}
