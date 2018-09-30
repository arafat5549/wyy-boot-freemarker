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
import com.jqmkj.java.util.annotation.DictType;


import org.hibernate.validator.constraints.*;

/**
 * 文件管理Entity 文件
 * @author admin
 * @version 2018-09-30
 */
@TableName(value = "sys_file_data_t")
@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class FileData extends IdEntity<FileData, String> {

	private static final long serialVersionUID = 1L;
	/** F_NAME name_  :  名称 */
	public static final String F_NAME = "name";
	/** F_SQL_NAME name_  :  名称 */
	public static final String F_SQL_NAME = "name_";
	/** F_PATH path_  :  路径 */
	public static final String F_PATH = "path";
	/** F_SQL_PATH path_  :  路径 */
	public static final String F_SQL_PATH = "path_";
	/** F_SIZE size_  :  大小 */
	public static final String F_SIZE = "size";
	/** F_SQL_SIZE size_  :  大小 */
	public static final String F_SQL_SIZE = "size_";
	/** F_TYPE type_  :  类型 */
	public static final String F_TYPE = "type";
	/** F_SQL_TYPE type_  :  类型 */
	public static final String F_SQL_TYPE = "type_";

	//columns START
	/** name 名称 */@Length(max=32)@TableField(F_SQL_NAME)
	private String name;
	/** path 路径 */@Length(max=255)@TableField(F_SQL_PATH)
	private String path;
	/** size 大小 */@NotBlank @Length(max=50)@TableField(F_SQL_SIZE)
	private String size;
	/** type 类型 */@Length(max=60)@TableField(F_SQL_TYPE)
	private String type;
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
