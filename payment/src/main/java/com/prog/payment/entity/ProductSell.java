package com.prog.payment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.prog.payment.data.vo.ProductSellVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "PRODUCT_SELL")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductSell implements Serializable{
	
	private static final long serialVersionUID = 3893396777739440585L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PRSE_ID")
	private Long id;
	
	@Column(name = "PRSE_PROD_ID_FK", nullable = false)
	private Long idProduct;
	
	@Column(name = "PRSE_QTY", nullable = false)
	private Integer qty;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Sell sell;
	
	public static ProductSell create(ProductSellVO productSellVO) {
		return new ModelMapper().map(productSellVO, ProductSell.class);
	}
	
}
