package model.entities;

import java.util.Date;

public class Funcionario extends Pessoa {
	
	private Integer codFuncionario;
	private String crm;

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
	
	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	
}
