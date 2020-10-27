package analizador.lectura.facil.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FormController {
	
	//shows the main page of the webapp
	@RequestMapping("/")
	public String showHomePage() {
		return "main-menu";
	}
	
	//takes the values of the form and show us the new page
	@RequestMapping("/processForm")
	public String processForm(@RequestParam("fileUpload") File file,
			Model model) throws IOException {
		//Retrieve the file from the form
		String name = file.getName();
		String type = null;
		//getting the type of the file we retrieved
		try {
			type = Files.probeContentType(file.getAbsoluteFile().toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//adding the name and the type to the model
		model.addAttribute("fileName", name);
		model.addAttribute("fileType", type);
		model.addAttribute("errors", readFile(file));
		return "conf-page";
	}
	
	//Method to read the file from the form and check the 'Pautas'
	private String readFile(File file) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st; 
		String res="";
		
		int linea = 1;
		  while ((st = br.readLine()) != null) {
			  if(!st.equals("")) {
			  String str = st;
			  res += "Errors found: \n" + checkPautaEtc(str,linea) + checkPautaPuntos(str,linea) + checkPautaExp(str,linea);
			  linea++;
			  }
		  } 
		
		return res;
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
