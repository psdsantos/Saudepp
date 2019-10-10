package model.entities;

import java.util.Date;

public class Paciente extends Pessoa{
	
	private Integer codPaciente;

	public Paciente() {
		super();
	}

	public Paciente(String nome, String cpf, String email, Date dataNasc, String endereco, String cep, String celular,
			String telefone, Integer codPaciente) {
		super(nome, cpf, email, dataNasc, endereco, cep, celular, telefone);
		this.codPaciente = codPaciente;
	}



	public Integer getCodPaciente() {
		return codPaciente;
	}

	public void setCodPaciente(Integer codPaciente) {
		this.codPaciente = codPaciente;
	}
	
}
