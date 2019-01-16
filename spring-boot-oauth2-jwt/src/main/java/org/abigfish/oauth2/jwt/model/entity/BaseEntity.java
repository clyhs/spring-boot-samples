package org.abigfish.oauth2.jwt.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;



@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	protected static final String NOT_DELETED = "deleted_on > CURRENT_TIMESTAMP OR deleted_on IS NULL";

	@Version
	protected Long version;

	protected LocalDateTime createdOn;

	protected LocalDateTime updatedOn;

	protected LocalDateTime deletedOn;

	protected Long createdBy;

	protected Long updatedBy;

}
