package analizador.lectura.facil.mvc;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
	
	//shows the main page of the webapp
	@RequestMapping("/")
	public String showHomePage() {
		return "main-menu";
	}
	
	//takes the values of the form and show us the new page
	@RequestMapping("/processForm")
	public String processForm(@RequestParam("textInput") String text,
			Model model){
		
		//reading from the input
		model.addAttribute("errors", readText(text));
		return "conf-page";
	}
	
	//Method to read the text from the form and check the 'Pautas'
	private String readText(String text) {
		System.out.println(text);
		return text;
		
	}

	//Method to check 'Pauta' that match expressions
	private String checkPautaExp(String str, int linea) {
		String [] expressions = new String []{"sin embargo", "no obstante"};
		String res="";
		for (String expr : expressions) {
			if (str.contains(expr)) {
				res = "Error! Used " + expr + "in line: " + linea + '\n';
			}
		}
		return res;
	}
	
	//Method to check 'Pauta' that must find 'etcetera' in text
	private String checkPautaEtc(String str, int linea) {
		String res ="";
		if (str.contains("etcetera")) {
			res = "Error! Used 'etcetera' in line: "+ linea +'\n';
		}
		
		return res;
	}

	//Method to check 'Pauta' that must find '...' in text
	private String checkPautaPuntos(String str, int linea) {
		String res ="";
		if (str.contains("...")) {
			res = "Error! Used '...' in line: "+ linea +'\n';
		}
		
		return res;
		
	}
}
