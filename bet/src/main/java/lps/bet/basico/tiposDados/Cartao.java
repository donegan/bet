package lps.bet.basico.tiposDados ;

import java.util.Calendar;
import java.util.Collection;

public class Cartao {

	private Calendar dtAquisicao;
	private Calendar dtValidade;
	private float saldo;
	private int cartaoID;
	private TipoPassageiro tipoPassageiro;
	private Collection viagems;
	private Collection pagamentos;
	private Passageiro passageiro;

	public Calendar getDtAquisicao() {
		return dtAquisicao;
	} 
	public void setDtAquisicao(Calendar dtAquisicao) {
		this.dtAquisicao = dtAquisicao;
	}

	public Calendar getDtValidade () {
		return dtValidade;
	} 

	public void setDtValidade (Calendar dtValidade) {
		this.dtValidade = dtValidade;
	}

	public float getSaldo () {
		return saldo;
	} 
	public void setSaldo (float saldo) {
		this.saldo = saldo;
	}

	public int getCartaoID () {
		return cartaoID;
	} 
	public void setCartaoID (int cartaoID) {
		this.cartaoID = cartaoID;
	}

	public TipoPassageiro getTipoPassageiro() {
		return this.tipoPassageiro;
	}

	public void setTipoPassageiro(TipoPassageiro tipoPassageiro) {
		this.tipoPassageiro = tipoPassageiro;
	}

	public Collection getViagems() {
		return this.viagems;
	}

	public void setViagems(Collection viagems) {
		this.viagems = viagems;
	}

	public Collection getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(java.util.Collection pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Passageiro getPassageiro() {
		return this.passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		
		Cartao cartao = (Cartao) obj;
		return (this.cartaoID == cartao.cartaoID);		
	}	

	public int hashCode() {
		return cartaoID;
	}
	
}