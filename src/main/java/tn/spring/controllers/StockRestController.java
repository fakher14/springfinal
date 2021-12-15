package tn.spring.controllers;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.spring.entities.Produit;
import tn.spring.entities.Stock;
import tn.spring.services.IProduitService;
import tn.spring.services.IStockService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/stocks")
public class StockRestController {

	@Autowired
	IStockService stockService ;
	

	@Autowired 
	IProduitService produitService;
	
	@GetMapping("/get-one/{stock-id}")
	@ResponseBody
	public Stock getStock(@PathVariable("stock-id")Long stockId) {
		return stockService.retrieveStock(stockId);
	}
	
	
	@GetMapping("/get-all")
	@ResponseBody
	public List<Stock> getStocks() {
		return stockService.retrieveAllStocks();
	}
	@PostMapping("/add")
	@ResponseBody
	public Stock addStock(@RequestBody Stock stock) {
		return stockService.addStock(stock);
	}
	@PutMapping("/modify")
	@ResponseBody
	public Stock updateStock(@RequestBody Stock stock) {
		return stockService.updateStock(stock);
	}
	@DeleteMapping("/remove/{stock-id}")
	@ResponseBody
	public void deleteStock(@PathVariable("stock-id")Long stockId)
	{
		stockService.deleteStock(stockId);
	}
	
	@GetMapping("/byStock/{id}")
	@ResponseBody
	public List<Produit> getProduitsByStock(@PathVariable("id")Long id){
		return produitService.findProduitsByStockid(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/revenueBrutact/{id-produit}/{date-debut}")
	@ResponseBody
	public float getRevenueBrutact(@PathVariable("id-produit")Long id,@PathVariable("date-debut") @DateTimeFormat(pattern = "yyyy-MM-dd")Date datestart) {
		return produitService.getRevenueBrutact(id, datestart);
	}

	@GetMapping("/warnings")
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public List<Stock> getStocksWithWarnings(){
		return stockService.getStocksWithWarnings();
	}
}
