package top.aqianer.manage.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import top.aqianer.manage.model.HazardClass;

@Data
public class Chemical {

    @NotBlank(message = "名称不能为空")
    private String name;

    @Pattern(regexp = "\\d{2,7}-\\d{2}-\\d", message = "CAS号格式不正确")
    private String casNumber;

    private String englishName;

    private Integer currentStock;

    private Integer minThreshold;

    private Integer maxThreshold;

    private String location;

    private String img_src;

    private String storageCondition;

    /**
     * 根据 MSDS文档地址
     */
    private String safetyDescription;

    private String lastModifiedBy;

    private String lastModifiedByRole;

    private HazardClass hazardClass;

}
