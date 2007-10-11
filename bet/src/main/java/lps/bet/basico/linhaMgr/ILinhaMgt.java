/*
 * ILinhaMgt.java
 *
 * Created on 17 de Junho de 2007, 10:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package lps.bet.basico.linhaMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Corrida;
import lps.bet.basico.tiposDados.Linha;
import lps.bet.basico.tiposDados.Onibus;

/**
 *
 * @author Paula
 */
public interface ILinhaMgt {
    
    public Linha buscarLinhaAtualOnibus(int onibusID); 
    public List buscarLinhas();
    public Linha buscarLinha(int linhaID);
    public Linha buscarLinha(String nomeLinha);
	public void criarLinha(Linha linha);	 
	public void alterarLinha(Linha linha);	 
	public void removerLinha(int linhaID);	 

	public List buscarCorridas();
	public Corrida buscarCorrida(int corridaID);
	public void criarCorrida(Corrida corrida);
	public void alterarCorrida(Corrida corrida);
	public void removerCorrida(int corridaID);
	public boolean verificarPermissaoViagem(int onibusID);

	public List buscarTodosOnibus();
	public Onibus buscarOnibus(int onibusID);
	public void criarOnibus(Onibus onibus);
	public void alterarOnibus(Onibus onibus);	 
	public void removerOnibus(int onibusID);
}