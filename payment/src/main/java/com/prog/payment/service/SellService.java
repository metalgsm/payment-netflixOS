package com.prog.payment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prog.payment.data.vo.SellVO;
import com.prog.payment.entity.ProductSell;
import com.prog.payment.entity.Sell;
import com.prog.payment.exception.NotFoundException;
import com.prog.payment.repository.ProductSellRepository;
import com.prog.payment.repository.SellRepository;

@Service
public class SellService {

	private final SellRepository sellRepository;
	private final ProductSellRepository productSellRepository;
	
	private static final String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "The product(%s) could not be found";
	
	public SellVO create(SellVO sellVO) {
		Sell sell = sellRepository.save(Sell.create(sellVO));
		
		List<ProductSell> productSells = new ArrayList<>();
		sellVO.getProductsSell().stream().forEach(p -> {
			ProductSell productSell = ProductSell.create(p);
			productSell.setSell(sell);
			productSells.add(productSellRepository.save(productSell));
		});
		
		sell.setProductsSell(productSells);
		
		return SellVO.create(sell);
	}
	
	public Page<SellVO> findAll(Pageable pageable){
		var page = sellRepository.findAll(pageable);
		return page.map(this::convertToSellVO);
	}
	
	private SellVO convertToSellVO(Sell product) {
		return SellVO.create(product);
	}
	
	public SellVO findById(Long id) {
		var entity = sellRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(String.format(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE, id)));
		
		return SellVO.create(entity);
	}
	
	public SellVO update(SellVO sellVO) {
		final SellVO currentSell = findById(sellVO.getId());
		
		Optional.ofNullable(sellVO.getProductsSell()).ifPresent(val -> currentSell.setProductsSell(val));
		Optional.ofNullable(sellVO.getTotalValue()).ifPresent(val -> currentSell.setTotalValue(val));
		Optional.ofNullable(sellVO.getDate()).ifPresent(val -> currentSell.setDate(val));
		
		return SellVO.create(sellRepository.save(Sell.create(currentSell)));
	}
	
	public void delete(Long id) {
		final SellVO currentProduct = findById(id);
		sellRepository.delete(Sell.create(currentProduct));
	}
	
	@Autowired
	public SellService(SellRepository sellRepository, ProductSellRepository productSellRepository) {
		this.sellRepository = sellRepository;
		this.productSellRepository = productSellRepository;
	}
}
