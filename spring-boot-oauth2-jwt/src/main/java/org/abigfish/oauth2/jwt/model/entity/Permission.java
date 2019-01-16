package org.abigfish.oauth2.jwt.model.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author admin
 *
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Permission extends BaseIdEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 60)
	private String name;

}
