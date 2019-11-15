package model.entities;

import java.util.Date;

public class Consulta {
	
	private Integer codConsulta;
	private Integer codpaciente;
	private Integer codmedico;
	private String paciente;
	private String medico;
	private Date data;

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
	
	public Integer getCodConsulta() {
		return codConsulta;
	}

	public void setCodConsulta(Integer codConsulta) {
		this.codConsulta = codConsulta;
	}

	public Integer getcodpaciente() {
		return codpaciente;
	}

	public void setcodpaciente(Integer codpaciente) {
		this.codpaciente = codpaciente;
	}

	public Integer getcodmedico() {
		return codmedico;
	}

	public void setcodmedico(Integer codmedico) {
		this.codmedico = codmedico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date date) {
		this.data = date;
	}
	
}
