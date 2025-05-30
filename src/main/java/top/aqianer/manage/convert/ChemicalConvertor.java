package top.aqianer.manage.convert;

import org.springframework.stereotype.Component;
import top.aqianer.manage.model.Drug;
import top.aqianer.manage.model.HazardClass;
import top.aqianer.manage.model.Stock;
import top.aqianer.manage.vo.Chemical;

@Component
public class ChemicalConvertor {

    public Chemical convert(Stock stock, Drug drug){
        Chemical chemical = new Chemical();
        chemical.setName(null != drug.getName() ? drug.getName() : "");
        chemical.setCasNumber(null != drug.getCasNumber() ? drug.getCasNumber() : "");
        chemical.setEnglishName(null != drug.getEnglishName() ? drug.getEnglishName() : "");
        chemical.setCurrentStock(null != stock.getCurrentStock() ? stock.getCurrentStock() : 0);
        chemical.setMinThreshold(null!= stock.getMinThreshold()? stock.getMinThreshold() : 0);
        chemical.setMaxThreshold(null!= stock.getMaxThreshold()? stock.getMaxThreshold() : 0);
        chemical.setLocation(null!= stock.getLocation()? stock.getLocation() : ""); 
        chemical.setImg_src("");
        chemical.setSafetyDescription("");
        chemical.setLastModifiedBy(null!= stock.getLastModifiedBy().getMemberName()? stock.getLastModifiedBy().getMemberName() : "");
        chemical.setStorageCondition(null!= drug.getStorageConditions().getDescription()? drug.getStorageConditions().getDescription() : "");
        chemical.setHazardClass(null!= drug.getHazardClass()? drug.getHazardClass() : new HazardClass());
        chemical.setLastModifiedByRole( stock.getLastModifiedBy().getPermission().name());
        return chemical;
    }

}
