package org.info.info303.metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.info.info303.dao.ConnaissanceRepository;
import org.info.info303.dao.DonneesRepository;
import org.info.info303.dao.RegexRepository;
import org.info.info303.dao.UtilisateurRepository;
import org.info.info303.dao.ValeurRepository;
import org.info.info303.entities.Connaissance;
import org.info.info303.entities.Regex;
import org.info.info303.entities.Utilisateur;
import org.info.info303.entities.Valeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class Metier {
	@Autowired
	private ValeurRepository valeurRepository ;
	@Autowired
	private  RegexRepository regexRepository ;
	@Autowired
	private DonneesRepository donneesRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ConnaissanceRepository connaissanceRepository;
	
/*	
	public String lireFichier(File file) {
		String resultat = "";
		String ligne = "";
		try{
		InputStream flux=new FileInputStream(file); 
		InputStreamReader lecture=new InputStreamReader(flux);
		BufferedReader buff=new BufferedReader(lecture);
		while ((ligne=buff.readLine())!=null){
			resultat=resultat+ligne;
		}
		buff.close(); 
		}		
		catch (Exception e){
		System.out.println(e.toString());
		}
		return resultat ;
	}
*/	
	public Connaissance extraction(String data , String regex ,String description , String email , int n){
		Utilisateur utilisateur = utilisateurRepository.findOne(email);
		
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        
        
		List<Valeur> valeurs = new ArrayList<Valeur>();
//matcher.fin recherche le motif regex dans data .
        while(matcher.find()) {
        	//on stoke les valeurs trouvees dans une liste 
			valeurs.add(new Valeur(matcher.group(n)));
        }
        
	//	regexRepository.save(new Regex(regex, description));
        //puis on enregistre cet ensemble de valeurs dans la bd 
        valeurRepository.save(valeurs);
        // on construi l'objet connaissance puis on l'enregistre 
		Connaissance connaissance=new Connaissance(description, valeurs, null , utilisateur);
		connaissanceRepository.save(connaissance); 

		return connaissance ;
	}
	
	
	public String coloriageData(String data ,String regex, int n) {
		String data1 = data;
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(data);
        while(matcher.find()) {  
        data1 = matcher.replaceAll(" <strong><mark>"+ matcher.group(n)+"</mark></strong>");
             }
		return data1;
	}

}
