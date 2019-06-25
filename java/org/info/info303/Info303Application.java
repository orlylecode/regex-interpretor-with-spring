package org.info.info303;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.info.info303.dao.DonneesRepository;
import org.info.info303.dao.RegexRepository;
import org.info.info303.dao.UtilisateurRepository;
import org.info.info303.entities.Regex;
import org.info.info303.entities.Utilisateur;
import org.info.info303.metier.Metier;
import org.info.info303.dao.ConnaissanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Info303Application  implements CommandLineRunner {

	
	@Autowired
	private  RegexRepository regexRepository ;
	@Autowired
	private DonneesRepository donneesRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ConnaissanceRepository connaissanceRepository;
	
	@Autowired
	private Metier metier;
	
	public static void main(String[] args) {
		SpringApplication.run(Info303Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=");
		//PreparedStatement ps = connection.prepareStatement("CREATE DATABASE extraction");
		//int result = ps.executeUpdate();

		Utilisateur utilisateur =new Utilisateur("info301", "info3011@gmail.com", "info301", null, null, null);
		utilisateurRepository.save(utilisateur);
		//metier.extraction(data , com, "public", utilisateur.getEmail() , 0);
		Regex regex = new Regex("(package)([\\s]*[a-z|A-Z|0-9]*[\\s]*([\\.][\\s]*[a-z|A-Z|0-9]*[\\s]*)*)[;]", "Nom package", 2) ;
		Regex regex1 = new Regex("([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(class)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])", "Nom de classes", 4) ;
		Regex regex2 = new Regex("([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(interface)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])", "Nom des interfaces", 4) ;
		Regex regex3 = new Regex("([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(extends)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])", "Nom des extentions", 4) ;
		Regex regex4 = new Regex("([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(implements)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])", "Nom des implementations", 4) ;
		Regex regex5= new Regex("([\\s]*)(([a-z|A-Z|0-9]*)*)([\\s]*)([a-z|A-Z|0-9]*)([\\s]*)(;)", "Nom des variable", 5) ;
		Regex regex6 = new Regex("boolean|byte|case|catch|char|class|do|double|else|enum|extends|false|finally|float|for|if|implements|import|int|interface|long|new|null|package|private|protected|public|return|static|switch|this|true|try|void|while", "coloration syntaxtique", 0) ;
		Regex regex7 = new Regex("NDSC", "nom de package suivi des noms de class ", 0) ;
		Regex regex8 = new Regex("TOUT", "nom de tous ", 0) ;
		
		
		regexRepository.save(regex);
		regexRepository.save(regex1);
		regexRepository.save(regex2);
		regexRepository.save(regex3);
		regexRepository.save(regex4);
		regexRepository.save(regex5);
		regexRepository.save(regex6);		
		regexRepository.save(regex7);
		regexRepository.save(regex8);

	

	}
}
