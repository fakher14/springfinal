package tn.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import tn.spring.entities.Stock;
import tn.spring.repositories.*;
@Service
public class StockService implements IStockService{

	
	@Autowired
	StockRepository stockRepository;
	
	@Override
	public List<Stock> retrieveAllStocks() {	
		return stockRepository.findAll();
	}

	@Override
	public Stock addStock(Stock s) {
		stockRepository.save(s);
		return s ;
	}

	@Override
	public Stock updateStock(Stock s) {
		stockRepository.save(s);
		return s;
	}

	@Override
	public Stock retrieveStock(Long id) {
		return stockRepository.findById(id).get()	;
		}


	@Override
	public void deleteStock(Long id) {
		stockRepository.deleteById(id);
		
	}

	@Override
	public List<Stock> getStocksWithWarnings() {
		return stockRepository.getStocksWithWarnings();

	}
	
	

}
