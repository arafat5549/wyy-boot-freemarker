/**
 * Copyright &copy; 2018 <a href="https://github.com/somewhereMrli/jqmkj-boot">jqmkj-boot</a> All rights reserved.
 */
package com.jqmkj.java.modules.sys.domain.vo;

import com.jqmkj.java.vo.base.DataEntityVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 行政区域管理EntityVo 行政区域
 * @author admin
 * @version 2018-09-30
 */
@Data @ToString @NoArgsConstructor @AllArgsConstructor
public class RegionVo extends DataEntityVo<Long> {

	private static final long serialVersionUID = 1L;
	/** F_NAME name  :  区域名称 */
	public static final String F_NAME = "name";
	/** F_PARENTID parent_id  :  父节点id */
	public static final String F_PARENTID = "parentId";
	/** F_LONGITUDE longitude  :  经度 */
	public static final String F_LONGITUDE = "longitude";
	/** F_LATITUDE latitude  :  纬度 */
	public static final String F_LATITUDE = "latitude";
	/** F_CREATEDATE create_date  :  创建时间 */
	public static final String F_CREATEDATE = "createDate";
	/** F_UPDATEDATE update_date  :  更新时间 */
	public static final String F_UPDATEDATE = "updateDate";

	//columns START
	/** name 区域名称 */
 @NotBlank @Length(max=50)
	private String name;
	/** parentId 父节点id */
 @NotNull 
	private Long parentId;
	/** longitude 经度 */
 @NotBlank @Length(max=50)
	private String longitude;
	/** latitude 纬度 */
 @NotBlank @Length(max=50)
	private String latitude;
	/** createDate 创建时间 */
 @NotNull 
	private Date createDate;
	/** updateDate 更新时间 */
 
	private Date updateDate;
	//columns END

    public RegionVo(Long id){
	    this.setId(id);
    }

}
