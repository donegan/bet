package lps.bet.basico.tiposDados;

public class Usuario {

	protected int usuarioID;
	protected String login;
	protected String senha;
	protected int nivelAcesso;
	
	public int getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	public int getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(int usuarioID) {
		this.usuarioID = usuarioID;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (this == arg0)
			return true;
		
		if (arg0 == null || arg0.getClass() != this.getClass()){
			return false;
		}
		return this.usuarioID == ((Usuario) arg0).usuarioID;
	}

	@Override
	public int hashCode() {
		return usuarioID;
	}
	
}
