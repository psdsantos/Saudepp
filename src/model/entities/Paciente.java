package model.entities;

public class Paciente extends Pessoa{
	private Integer codPaciente;

	public Paciente(Integer codPaciente) {
		super();
		this.codPaciente = codPaciente;
	}

	public Integer getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(Integer codPaciente) {
		this.codPaciente = codPaciente;
	}
	
}
