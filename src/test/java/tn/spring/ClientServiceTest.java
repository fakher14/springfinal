package tn.spring;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import tn.spring.entities.Client;
import tn.spring.entities.Facture;
import tn.spring.services.IClientService;
import tn.spring.services.IFactureService;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	IClientService clientService;
	
	@Autowired
	IFactureService factureService;
	
	 
	
	
}
