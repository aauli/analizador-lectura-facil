package analizador.lectura.facil.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.validation.constraints.NotNull;

public class UploadedFile {
	
	@NotNull(message="is required")
	private File file;
	
	private String name;
	
	private String type;
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return file.getName();
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() throws IOException {
		return Files.probeContentType(file.getAbsoluteFile().toPath());
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
