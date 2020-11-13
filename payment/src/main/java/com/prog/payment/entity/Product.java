package com.prog.payment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "PRODUCT")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3156278693068634845L;

	@Id
	@Column(name = "PROD_ID")
	private Long id;
	
	@Column(name = "PROD_STOCK", nullable = false)
	private Integer stock;
	
}
