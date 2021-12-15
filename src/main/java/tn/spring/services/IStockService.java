package tn.spring.services;

import java.util.List;

import tn.spring.entities.Stock;

public interface IStockService {

	List<Stock> retrieveAllStocks();

	Stock addStock(Stock s);

	Stock updateStock(Stock u);

	Stock retrieveStock(Long id);
	
	void deleteStock(Long id) ;
	
	List<Stock> getStocksWithWarnings();
	
}
