package model.entities;

import java.sql.Date;

public class Consulta {
	private Paciente paciente;
	private Funcionario medico;
	private Date data;
	
	public Consulta(Paciente paciente, Funcionario medico, Date data) {
		super();
		this.paciente = paciente;
		this.medico = medico;
		this.data = data;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Funcionario getMedico() {
		return medico;
	}

	public void setMedico(Funcionario medico) {
		this.medico = medico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
