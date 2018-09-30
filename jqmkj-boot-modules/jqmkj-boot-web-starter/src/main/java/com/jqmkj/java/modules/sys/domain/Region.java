/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.jqmkj.java.util.PublicUtil;
import com.baomidou.mybatisplus.annotations.*;
import com.jqmkj.java.util.annotation.SearchField;
import com.jqmkj.java.common.persistence.domain.IdEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jqmkj.java.util.annotation.DictType;


import org.hibernate.validator.constraints.*;

/**
 * 行政区域管理Entity 行政区域
 * @author admin
 * @version 2018-09-30
 */
@TableName(value = "c_region")
@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class Region extends IdEntity<Region, Long> {

	private static final long serialVersionUID = 1L;
	/** F_NAME name  :  区域名称 */
	public static final String F_NAME = "name";
	/** F_SQL_NAME name  :  区域名称 */
	public static final String F_SQL_NAME = "name";
	/** F_PARENTID parent_id  :  父节点id */
	public static final String F_PARENTID = "parentId";
	/** F_SQL_PARENTID parent_id  :  父节点id */
	public static final String F_SQL_PARENTID = "parent_id";
	/** F_LONGITUDE longitude  :  经度 */
	public static final String F_LONGITUDE = "longitude";
	/** F_SQL_LONGITUDE longitude  :  经度 */
	public static final String F_SQL_LONGITUDE = "longitude";
	/** F_LATITUDE latitude  :  纬度 */
	public static final String F_LATITUDE = "latitude";
	/** F_SQL_LATITUDE latitude  :  纬度 */
	public static final String F_SQL_LATITUDE = "latitude";
	/** F_CREATEDATE create_date  :  创建时间 */
	public static final String F_CREATEDATE = "createDate";
	/** F_SQL_CREATEDATE create_date  :  创建时间 */
	public static final String F_SQL_CREATEDATE = "create_date";
	/** F_UPDATEDATE update_date  :  更新时间 */
	public static final String F_UPDATEDATE = "updateDate";
	/** F_SQL_UPDATEDATE update_date  :  更新时间 */
	public static final String F_SQL_UPDATEDATE = "update_date";

	//columns START
	/** name 区域名称 */@NotBlank @Length(max=50)@TableField(F_SQL_NAME)
	private String name;
	/** parentId 父节点id */@NotNull @TableField(F_SQL_PARENTID)
	private Long parentId;
	/** longitude 经度 */@NotBlank @Length(max=50)@TableField(F_SQL_LONGITUDE)
	private String longitude;
	/** latitude 纬度 */@NotBlank @Length(max=50)@TableField(F_SQL_LATITUDE)
	private String latitude;
	/** createDate 创建时间 */@NotNull @TableField(F_SQL_CREATEDATE)
	private Date createDate;
	/** updateDate 更新时间 */@TableField(F_SQL_UPDATEDATE)
	private Date updateDate;
	//columns END

	@Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
