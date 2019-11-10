package model.entities;

public class Consulta {
	private String paciente;
	private String medico;
	private String data;
	
	public Consulta(String paciente, String medico, String data) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.data = data;
	}

	public Consulta() {
		super();
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getData() {
		return data;
	}

	public void setData(String date) {
		this.data = date;
	}
	
}
