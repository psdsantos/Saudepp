package model.entities;

import java.util.Date;

public class Funcionario extends Pessoa {
	
	private Integer codFuncionario;

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String cpf, String email, Date dataNasc, String endereco, String cep,
			String celular, String telefone, Integer codFuncionario) {
		super(nome, cpf, email, dataNasc, endereco, cep, celular, telefone);
		this.codFuncionario = codFuncionario;
	}

	public Integer getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(Integer codFuncionario) {
		this.codFuncionario = codFuncionario;
	}
	
}
