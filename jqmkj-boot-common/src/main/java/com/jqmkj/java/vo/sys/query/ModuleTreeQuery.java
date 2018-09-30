package com.jqmkj.java.vo.sys.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

/**
 * Created by somewhere on 2017/3/2.
 */
@Data
@ApiModel
@ToString
public class ModuleTreeQuery {

    private String type;
    private String all;
    private Boolean root = false;

}
