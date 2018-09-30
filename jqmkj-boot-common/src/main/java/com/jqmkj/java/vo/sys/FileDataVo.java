/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.vo.sys;

import com.jqmkj.java.vo.base.DataEntityVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 文件管理EntityVo 文件
 * @author admin
 * @version 2018-08-06
 */
@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class FileDataVo extends DataEntityVo<String> {

	private static final long serialVersionUID = 1L;
	/** F_NAME name_  :  名称 */
	public static final String F_NAME = "name";
	/** F_PATH path_  :  路径 */
	public static final String F_PATH = "path";
	/** F_SIZE size_  :  大小 */
	public static final String F_SIZE = "size";
	/** F_TYPE type_  :  类型 */
	public static final String F_TYPE = "type";

	//columns START
	/** name 名称 */
 @Length(max=32)
	private String name;
	/** path 路径 */
 @Length(max=255)
	private String path;
	/** size 大小 */
 @NotBlank @Length(max=50)
	private Long size;
	/** type 类型 */
 @Length(max=60)
	private String type;
	//columns END

    public FileDataVo(String id){
	    this.setId(id);
    }

}
