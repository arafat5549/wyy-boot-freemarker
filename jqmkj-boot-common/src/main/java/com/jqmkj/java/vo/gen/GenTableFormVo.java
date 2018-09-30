package com.jqmkj.java.vo.gen;

import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.StringUtil;
import com.jqmkj.java.util.base.Collections3;
import com.jqmkj.java.util.config.SystemConfig;
import com.jqmkj.java.util.exception.RuntimeMsgException;
import com.jqmkj.java.vo.base.DataEntityVo;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 业务表Entity
 *
 * @author wyy
 * @version 2013-10-15
 */
@Data
@ToString
@NoArgsConstructor
public class GenTableFormVo  implements Serializable {

    private static final long serialVersionUID = 1L;
    /*** 编码 */
    private String id;
    // 名称
    /*** 编码 */
    private String name;

}
