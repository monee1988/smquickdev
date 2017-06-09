package com.github.monee1988.base.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.monee1988.mybatis.entity.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(value = { "isNewData" })  
public class BaseBean<T> implements Serializable{

	public static final String row_normal = "1";
	public static final String row_state1 = "2";
	public static final String row_delete = "0";

	@ApiModelProperty(hidden = true)
	protected Long id;

	@ApiModelProperty(hidden = true)
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;

	@ApiModelProperty(hidden = true)
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;

	@ApiModelProperty(hidden = true)
	protected Long createAccount;

	@ApiModelProperty(hidden = true)
	protected Long updateAccount;

	@ApiModelProperty(hidden = true)
	@JSONField(serialize = false)
	protected String rowState = row_normal;

	@ApiModelProperty(hidden = true)
	@JSONField(serialize = false)
	protected boolean isNewData;//是否是新数据

	@ApiModelProperty(hidden = true)
	protected Long adminId; //当前登录账号Id

	@ApiModelProperty(hidden = true)
    private Page<T> page;

	@ApiModelProperty(hidden = true)
	public boolean isNewData(){
       return StringUtils.isEmpty(this.id)||this.isNewData;
    }
	
	public void preInsert(Long operateId){
		this.createDate = new Date();
		this.updateDate =this.createDate;
		this.createAccount = operateId;
		this.updateAccount = operateId;
	}
	
	public void preUpdate(Long operateId){
		this.updateDate  = new Date();
		this.updateAccount = operateId;
	}

	public BaseBean() {
		
	}

	public BaseBean(Long id) {
		this();
		this.id = id;
	}
}
