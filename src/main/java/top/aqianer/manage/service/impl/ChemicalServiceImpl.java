package top.aqianer.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aqianer.manage.convert.ChemicalConvertor;
import top.aqianer.manage.model.Drug;
import top.aqianer.manage.model.Stock;
import top.aqianer.manage.repository.DrugRepository;
import top.aqianer.manage.repository.StockRepository;
import top.aqianer.manage.service.ChemicalService;
import top.aqianer.manage.vo.Chemical;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChemicalServiceImpl implements ChemicalService {

    private final DrugRepository drugRepository;

    private final StockRepository stockRepository;

    @Autowired
    ChemicalConvertor chemicalConvertor;

    @Autowired
    public ChemicalServiceImpl(DrugRepository chemicalRepository, StockRepository stockRepository) {
        this.drugRepository = chemicalRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Chemical> findAll(Long labId) {
        // 根据实验室ID查询库存记录
        List<Stock> stocks = stockRepository.findByLaboratory_Id(labId);

        return stocks.stream()
                .map(stock -> {
                    // 获取关联的药品信息
                    Drug drug = drugRepository.findById(stock.getDrug().getDrugId())
                            .orElseThrow(() -> new RuntimeException("药品不存在"));

                    // 转换合并数据
                    return chemicalConvertor.convert(stock, drug);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Drug save(Drug chemical) {
        return drugRepository.save(chemical);
    }
}
