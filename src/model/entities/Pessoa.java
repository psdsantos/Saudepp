package model.entities;

import java.util.Date;

public class Pessoa {
	
	private String nome;
	private String cpf;
	private String email;
	private Date dataNasc;
	private String endereco;
	private String cep;
	private String celular;
	private String telefone;
	
	public Pessoa() {
		super();
	}
	public Pessoa(String nome, String cpf, String email, Date dataNasc, String endereco, String cep, String celular,
			String telefone) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNasc = dataNasc;
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
	public Date getdataNasc() {
		return dataNasc;
	}
	public void setdataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
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
