package lps.bet.variabilidades.viagemTempoNumViagensCtrl;

import lps.bet.basico.linhaMgr.IRegistrarArrecadacao;
import lps.bet.basico.tiposDados.Viagem;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.interfaces.IProcessarViagem;
import lps.bet.variabilidades.numViagensMgr.INumViagensMgt;
import lps.bet.variabilidades.tempoMgr.ITempoMgt;

import java.util.Calendar;

public class ViagemTempoNumViagensCtrl implements IProcessarViagem {

    ITempoMgt interfaceTempoMgt;
    INumViagensMgt interfaceNumViagensMgt;
    ICartaoMgt interfaceCartaoMgt;
    IRegistrarArrecadacao interfaceRegistrarArrecadacao;
    IProcessarViagem interfaceProcessarViagem;

    public ITempoMgt getInterfaceTempoMgt() {
        return interfaceTempoMgt;
    }

    public void setInterfaceTempoMgt(ITempoMgt interfaceTempoMgt) {
        this.interfaceTempoMgt = interfaceTempoMgt;
    }

    public INumViagensMgt getInterfaceNumViagensMgt() {
        return interfaceNumViagensMgt;
    }

    public void setInterfaceNumViagensMgt(INumViagensMgt interfaceNumViagensMgt) {
        this.interfaceNumViagensMgt = interfaceNumViagensMgt;
    }

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }

    public IRegistrarArrecadacao getInterfaceRegistrarArrecadacao() {
        return interfaceRegistrarArrecadacao;
    }

    public void setInterfaceRegistrarArrecadacao(IRegistrarArrecadacao interfaceRegistrarArrecadacao) {
        this.interfaceRegistrarArrecadacao = interfaceRegistrarArrecadacao;
    }

    public IProcessarViagem getInterfaceProcessarViagem() {
        return interfaceProcessarViagem;
    }

    public void setInterfaceProcessarViagem(
            IProcessarViagem interfaceProcessarViagem) {
        this.interfaceProcessarViagem = interfaceProcessarViagem;
    }


    public String processarViagem(int cartaoID, int onibusID) {

        String estado = "INT-NOK";

        estado = verificarIntegracao(cartaoID, onibusID);

        //Viagem fora do tempo de integra��o: nova viagem
        if (!estado.equals("INT-OK")) {
            return interfaceProcessarViagem.processarViagem(cartaoID, onibusID);
        } else {
            return estado;
        }
    }

    public String verificarIntegracao(int cartaoID, int onibusID) {
        String estado = "INT-NOK";

        //Verificar se est� dentro do tempo para realizar integra��o
        int tempoMaxIntegracao = interfaceTempoMgt.buscarTempo();
        //De in�cio considera-se que n�o haver� integra��o, ent�o o tempo passou do que poderia ser:
        long tempoDecorrido = tempoMaxIntegracao + 1;

        int numMaxViagens = interfaceNumViagensMgt.buscarMaxNumViagens();
        int numViagem = 0;

        Viagem viagem = interfaceCartaoMgt.buscarUltimaViagem(cartaoID);

        //Viagem dentro do tempo de integra��o
        if (viagem != null) {
            Calendar horaUltimaViagem = viagem.getHora();
            tempoDecorrido = Calendar.getInstance().getTimeInMillis() - horaUltimaViagem.getTimeInMillis();
            numViagem = viagem.getNumViagens();

            //Integra��o
            if ((tempoDecorrido <= tempoMaxIntegracao * 1000) && (numViagem < numMaxViagens)) {
                estado = processarIntegracao(onibusID, viagem);
            }
        }
        return estado;
    }


    public String processarIntegracao(int onibusID, Viagem viagem) {
        //N�o recebe dinheiro, mas incrementa o numero de passageiros para a corrida
        interfaceRegistrarArrecadacao.registrarArrecadacao(onibusID, 0);

        //Uma integra��o � feita para a viagem
        viagem.setNumViagens(viagem.getNumViagens() + 1);
        interfaceCartaoMgt.alterarViagem(viagem);
        String estado = "INT-OK";
        System.out.println("ESTADO: INT-OK");
        return estado;
    }
}
