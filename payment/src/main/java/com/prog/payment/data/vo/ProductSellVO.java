package com.prog.payment.data.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.prog.payment.entity.ProductSell;

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
public class ProductSellVO extends RepresentationModel<ProductSellVO> implements Serializable{
	
	private static final long serialVersionUID = 928603669323620810L;

	private Long id;
	
	private Long idProduct;
	
	private Integer qty;
	
	public static ProductSellVO create(ProductSell productSell) {
		return new ModelMapper().map(productSell, ProductSellVO.class);
	}
}
