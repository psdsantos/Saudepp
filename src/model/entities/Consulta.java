package model.entities;

import java.util.Date;

public class Consulta {
	private String paciente;
	private String medico;
	private Date data;
	
	public Consulta(String paciente, String medico, Date data) {
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

	public Date getData() {
		return data;
	}

	public void setData(Date date) {
		this.data = date;
	}
	
}
