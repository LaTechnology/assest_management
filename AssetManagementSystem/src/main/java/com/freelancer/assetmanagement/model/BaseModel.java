package com.freelancer.assetmanagement.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
//@Data
public abstract class BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(updatable = false)
	@CreatedBy
	private String createdBy;
	
	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	
//	@Column(insertable = false)
	@LastModifiedBy
	private String modifiedBy;
	
//	@Column(insertable = false)
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
