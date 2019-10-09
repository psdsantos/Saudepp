package model.entities;

public class Medico extends Funcionario {
	
	private String crm;
	
	public Medico() {
		super();
	}

	public Medico(String nome, String cpf, String email, Integer idade, String endereco, String cep, String celular,
			String telefone, Integer codFuncionario, String crm) {
		super(nome, cpf, email, idade, endereco, cep, celular, telefone, codFuncionario);
		this.crm = crm;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
}
