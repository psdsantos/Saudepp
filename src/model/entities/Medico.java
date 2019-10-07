package model.entities;

public class Medico extends Funcionario {
	String crm;
	
	public Medico() {
		super();
	}

	public Medico(String nome, String cpf, String email, Integer idade, String endereco, String cep, String celular,
			String telefone) {
		super(nome, cpf, email, idade, endereco, cep, celular, telefone);
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}
