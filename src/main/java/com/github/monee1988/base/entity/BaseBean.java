package com.github.monee1988.base.entity;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.monee1988.mybatis.entity.Page;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = { "isNewData" })  
public class BaseBean<T>{

	/**
	 * 
	 */
	public static final String row_normal = "1";
	public static final String row_state1 = "2";
	public static final String row_delete = "0";

	protected Long id;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;
	
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;
	
	protected Long createAccount;
	
	protected Long updateAccount;
	
	@JSONField(serialize = false)
	protected String rowState = row_normal;

	@JSONField(serialize = false)
	protected boolean isNewData;//是否是新数据
	
	protected Long adminId; //当前登录账号Id
    
    private Page<T> page;
	
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

	/**
	 *默认的构造
	 */
	public BaseBean() {
		
	}

	/**
	 * 带ID的构造
	 * @param id
	 */
	public BaseBean(Long id) {
		this();
		this.id = id;
	}
}
