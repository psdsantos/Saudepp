package model.entities;

public class Pessoa {
	
	private String nome;
	private String cpf;
	private String email;
	private Integer idade;
	private String endereco;
	private String cep;
	private String celular;
	private String telefone;
	
	public Pessoa() {
		super();
	}
	public Pessoa(String nome, String cpf, String email, Integer idade, String endereco, String cep, String celular,
			String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.idade = idade;
		this.endereco = endereco;
		this.cep = cep;
		this.celular = celular;
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
