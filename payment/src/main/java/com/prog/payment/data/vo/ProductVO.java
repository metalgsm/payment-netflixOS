package com.prog.payment.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prog.payment.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProductVO extends RepresentationModel<ProductVO> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3214355935459141707L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("stock")
	private Integer stock;
	
	public static ProductVO create(Product product) {
		return new ModelMapper().map(product, ProductVO.class);
	}
}
