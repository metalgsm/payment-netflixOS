package com.prog.payment.data.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.prog.payment.entity.Sell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Data
public class SellVO extends RepresentationModel<SellVO> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1171410271528637912L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("date")
	private LocalDateTime date;
	
	@JsonProperty("productsSell")
	private List<ProductSellVO> productsSell;
	
	@JsonProperty("totalValue")
	private BigDecimal totalValue;
	
	public static SellVO create(Sell sell) {
		return new ModelMapper().map(sell, SellVO.class);
	}
	
}
