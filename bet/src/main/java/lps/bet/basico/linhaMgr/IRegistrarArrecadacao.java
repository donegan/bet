/*
 * IRegistrarArrecadacao.java
 *
 * Created on 17 de Junho de 2007, 10:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.linhaMgr;

/**
 *
 * @author Paula
 */
public interface IRegistrarArrecadacao {
    
	//Arrecada��o manual � registrada:
	public void registrarArrecadacao(int onibusID, float valor);
	
	//Arrecada��o por meio de d�bito em cart�o � registrada
    public void registrarCredito(int onibusID, float valor);
}
