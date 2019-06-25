package org.info.info303.web;

import org.info.info303.dao.ConnaissanceRepository;
import org.info.info303.dao.DonneesRepository;
import org.info.info303.dao.RegexRepository;
import org.info.info303.dao.UtilisateurRepository;
import org.info.info303.entities.Connaissance;
import org.info.info303.entities.Regex;
import org.info.info303.entities.Utilisateur;
import org.info.info303.metier.Metier;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExtractionController {

	@Autowired
	private RegexRepository regexRepository;
	@Autowired
	private DonneesRepository donneesRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ConnaissanceRepository connaissanceRepository;

	@Autowired
	Metier metier;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String utilisateur(Model model) {
// ici on revoie le formulaire d'authentification 
		return "utilisateur";
	}
//le formulaire qui envoie les info a l'adresse connect 
	@PostMapping(value = "/connect")
	public String saveUser(String username, String password, Model model) {
		Utilisateur utilisateur = utilisateurRepository.findOne(username);
		if (utilisateur != null) {
			if (utilisateur.getPass().equals(password)) {
				model.addAttribute("username", username);
				// model.addAttribute("regex", regexRepository.findAll());
				return "extraction";
			}
		}
		model.addAttribute("error", " erreur d'authentification");
		return "utilisateur";
	}

	@RequestMapping(value = "/extraction", method = RequestMethod.GET)
	public String extraction(Model model, String username) {
		// model.addAttribute("regex", regexRepository.findAll());
		model.addAttribute("username", username);
		return "extraction";
	}

	/*
	 * @RequestMapping(value = "/charger",method = RequestMethod.GET) public String
	 * charger(Model model ,File file, String username ) {
	 * model.addAttribute("username", username); model.addAttribute("data", new
	 * Metier().lireFichier(file)); //model.addAttribute("regex",
	 * regexRepository.findAll()); return "extraction"; }
	 */

	// [A-Z][a-z|A-Z]*+[\s][a-z|A-Z]*+[\s]*;|[a-z][a-z|A-Z]*+[\s][a-z|A-Z]*+[\s]*;
	// nom de variable
	public String connaissances;
	public String data1;

	@PostMapping(value = "/extraire")
	public String extraire(Model model, Long codeRegex, String username, String descriptionDetaille, String data,
			String regex, String description, int group) {
		data1 = data;
		try {
			Connaissance connaissance = new Connaissance();
			if (codeRegex != null) {
				Regex regex2 = regexRepository.findOne(codeRegex);
				if (regex2.getChaine().equals("NDSC")) {
					Regex regexA = new Regex(
							"(package)([\\s]*[a-z|A-Z|0-9]*[\\s]*([\\.][\\s]*[a-z|A-Z|0-9]*[\\s]*)*)[;]", "Nom package",
							2);
					Regex regexB = new Regex(
							"([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(class)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])",
							"Nom de classes", 4);

					Connaissance A = metier.extraction(data, regexA.getChaine(), regexA.getDescription(), username,
							regexA.getG());
					Connaissance B = metier.extraction(data, regexB.getChaine(), regexB.getDescription(), username,
							regexB.getG());

					connaissances = "=====>" + A.getDescription() + "\n";
					A.getValeurs().forEach(e -> {
						connaissances = connaissances + e.getValeur() + "\n";
						data1 = metier.coloriageData(data1, e.getValeur(), 0);
					});

					connaissances = connaissances + "=====>" + B.getDescription() + "\n";
					B.getValeurs().forEach(e -> {
						connaissances = connaissances + e.getValeur() + "\n";
						data1 = metier.coloriageData(data1, e.getValeur(), 0);
					});

					group = regex2.getG();
					regex = "complique";
					description = "nom des package suivi des nom de classes";

				} else {

					if (regex2.getChaine().equals("TOUT")) {
						Regex regexA = new Regex(
								"(package)([\\s]*[a-z|A-Z|0-9]*[\\s]*([\\.][\\s]*[a-z|A-Z|0-9]*[\\s]*)*)[;]",
								"Nom package", 2);
						Regex regexB = new Regex(
								"([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(class)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])",
								"Nom de classes", 4);
						Regex regexC = new Regex(
								"([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(interface)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])",
								"Nom des interfaces", 4);
						Regex regexD = new Regex(
								"([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(extends)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])",
								"Nom des extentions", 4);
						Regex regexE = new Regex(
								"([\\s]*+([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)(implements)([\\s]*+([a-z|A-Z|0-9]*)+[\\s]*)(([a-z|A-Z|0-9]*+[\\s]*)*[\\s]*)([{])",
								"Nom des implementations", 4);
						Regex regexF = new Regex("([\\s]*)(([a-z|A-Z|0-9]*)*)([\\s]*)([a-z|A-Z|0-9]*)([\\s]*)(;)",
								"Nom des variable", 5);

						Connaissance A = metier.extraction(data, regexA.getChaine(), regexA.getDescription(), username,
								regexA.getG());
						Connaissance B = metier.extraction(data, regexB.getChaine(), regexB.getDescription(), username,
								regexB.getG());
						Connaissance C = metier.extraction(data, regexC.getChaine(), regexC.getDescription(), username,
								regexC.getG());
						Connaissance D = metier.extraction(data, regexD.getChaine(), regexD.getDescription(), username,
								regexD.getG());
						Connaissance E = metier.extraction(data, regexE.getChaine(), regexE.getDescription(), username,
								regexE.getG());
						Connaissance F = metier.extraction(data, regexF.getChaine(), regexF.getDescription(), username,
								regexF.getG());

						connaissances = "=====>" + A.getDescription() + "\n";
						A.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
						});

						connaissances = connaissances + "=====>" + B.getDescription() + "\n";
						B.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
						});

						connaissances = connaissances + "=====>" + C.getDescription() + "\n";
						C.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
						});

						connaissances = connaissances + "=====>" + D.getDescription() + "\n";
						D.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
						});

						connaissances = connaissances + "=====>" + E.getDescription() + "\n";
						E.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
						});

						connaissances = connaissances + "=====>" + F.getDescription() + "\n";
						F.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
						});
						group = 0;
						regex = "complique";
						description = "tout";
					} else {
						connaissance = metier.extraction(data, regex2.getChaine(), regex2.getDescription(), username,
								regex2.getG());
						group = regex2.getG();
						regex = regex2.getChaine();
						description = regex2.getDescription();

						connaissances = connaissance.getDescription() + "\n";
						connaissance.getValeurs().forEach(e -> {
							connaissances = connaissances + e.getValeur() + "\n";
							data1 = metier.coloriageData(data1, e.getValeur(), 0);
						});
					}
					// data1 = metier.coloriageData(data, regex2.getChaine(), regex2.getG());
				}

			} else {
				
				//si la regex n'existe pas en basse de donne , on extraie les connaissances et puis on l'enregistre 
				connaissance = metier.extraction(data, regex, description, username, group);

				regexRepository.save(new Regex(regex, description, group));
// ce block permet en generale de mettre dans une chaine la liste des valeurs d'une connaissance .
				connaissances = connaissance.getDescription() + "\n";
				connaissance.getValeurs().forEach(e -> {
					connaissances = connaissances + e.getValeur() + "\n";
					data1 = metier.coloriageData(data1, e.getValeur(), 0);
				});
			}

			model.addAttribute("connaissance", connaissances);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("error", e.getLocalizedMessage());

		}
		model.addAttribute("regexs", regexRepository.findAll());

		model.addAttribute("group", group);
		model.addAttribute("data1", data1);
		model.addAttribute("data", data);
		model.addAttribute("description", description);
		model.addAttribute("regex", regex);

		model.addAttribute("username", username);
		return "extraction";
	}
}
