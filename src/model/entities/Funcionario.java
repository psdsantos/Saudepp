package model.entities;

public class Funcionario extends Pessoa {
	private Integer codFuncionario;

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String cpf, String email, Integer idade, String endereco, String cep,
			String celular, String telefone) {
		super(nome, cpf, email, idade, endereco, cep, celular, telefone);
	}

	public Integer getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(Integer codFuncionario) {
		this.codFuncionario = codFuncionario;
	}
	
}
