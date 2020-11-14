package com.prog.payment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.prog.payment.data.vo.SellVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "SELL")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sell implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SELL_ID")
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/YYYY HH:mm:ss")
	@Column(name = "SELL_DATE", nullable = false)
	private LocalDateTime date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.REFRESH)
	private List<ProductSell> productsSell;
	
	@Column(name = "SELL_TOTAL_VALUE", nullable = false)
	private BigDecimal totalValue;

	public static Sell create(SellVO sellVO) {
		return new ModelMapper().map(sellVO, Sell.class);
	}
	
}
