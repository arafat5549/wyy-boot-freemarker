package com.jqmkj.java.modules.sys.domain;

import com.jqmkj.java.common.persistence.domain.TreeEntity;
import com.jqmkj.java.util.PublicUtil;
import com.jqmkj.java.util.annotation.DictType;
import com.jqmkj.java.util.annotation.SearchField;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Copyright 2013 jqmkj All right reserved Author somewhere Created on 2013-10-23
 * 下午1:55:43
 */
@Entity
@Table(name = "sys_dict_t")
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dict extends TreeEntity<Dict> {

    /**
     * 叶子节点
     */
    public static final String FLAG_LEAF = "0";
    /**
     * 非叶子节点
     */
    public static final String FLAG_UNLEAF = "1";
    public static final String F_CODE = "code";
    public static final String F_VAL = "val";
    private static final long serialVersionUID = 1L;
    /*** 编码 */
    @SearchField
    @Column(name = "code_")
    private String code;
    /*** 字典值 */
    @Column(name = "val_")
    private String val;
    /*** 字典值 */
    @Column(name = "key_")
    private String key;
    /*** 资源文件key */
    @Column(name = "show_name")
    private String showName;
    @NotNull
    @Column(name = "is_show", nullable = false)
    @DictType(name = "sys_yes_no")
    private Integer isShow = 1;
    @Transient
    private String parentCode;

    public Dict() {
    }

    public Dict(String id) {
        this.id = id;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @XmlAttribute
    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    @XmlAttribute
    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getParentCode() {
        if (PublicUtil.isEmpty(parentCode) && parent != null) {
            parentCode = parent.getCode();
        }
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
