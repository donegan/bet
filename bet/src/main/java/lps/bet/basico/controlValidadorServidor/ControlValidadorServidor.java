package lps.bet.basico.controlValidadorServidor;

import lps.bet.basico.cartaoMgr.ICartaoMgt;
import lps.bet.basico.controlarCorrida.IRegistrarCorrida;
import lps.bet.basico.controlarViagem.IProcessarViagem;

public class ControlValidadorServidor implements IProcessarTransacao{

	IRegistrarCorrida interfaceRegistrarCorrida;
	IProcessarViagem interfaceProcessarViagem;
	ICartaoMgt interfaceCartaoMgt;

	public ControlValidadorServidor() {		
	}

	public String processarTransacao(int cod, int cartaoID, int onibusID){		
		
		String resposta = new String();
		
		//Se cart�o � v�lido, pode processar a transa��o:
		if (interfaceCartaoMgt.validarCartao(cartaoID)){
			
			//Codigo 2 corresponde a registro de corrida
			if (cod==2){
				System.out.println("Vai chamar registrar corrida");
				resposta = interfaceRegistrarCorrida.registrarCorrida(onibusID);
			}
			
			//Codigo 3 corresponde a uma viagem sendo realizada
			else if (cod==3){
				resposta = interfaceProcessarViagem.processarViagem(cartaoID, onibusID);
				float saldo = interfaceCartaoMgt.buscarCartao(cartaoID).getSaldo();
				String strSaldo = Float.toString(saldo);
				if (resposta.equals("PD-OK")){
					resposta = resposta + " " + strSaldo;
				}
			}
			return resposta;
		}
		//Sen�o cart�o � inv�lido e n�o deve ser processado
		else { 
			System.out.println("Cart�o � inv�lido!");
			return resposta="NOK";
		}
		
	}

	public IProcessarViagem getInterfaceProcessarViagem() {
		return interfaceProcessarViagem;
	}

	public void setInterfaceProcessarViagem(IProcessarViagem processarViagem) {
		interfaceProcessarViagem = processarViagem;
	}

	public IRegistrarCorrida getInterfaceRegistrarCorrida() {
		return interfaceRegistrarCorrida;
	}

	public void setInterfaceRegistrarCorrida(IRegistrarCorrida registrarCorrida) {
		interfaceRegistrarCorrida = registrarCorrida;
	}
	
	public ICartaoMgt getInterfaceCartaoMgt() {
		return interfaceCartaoMgt;
	}

	public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
		this.interfaceCartaoMgt = interfaceCartaoMgt;
	}
}