package lps.bet.variabilidades.web.limitePassagens;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.web.ControladorBet;
import lps.bet.interfaces.ICartaoMgt;
import lps.bet.variabilidades.limitePassagensMgr.ILimitePassagensMgt;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CargaCartaoLimPassagens extends ControladorBet{

    ICartaoMgt interfaceCartaoMgt;
    ILimitePassagensMgt interfaceLimitePassagensMgt;

    SimpleDateFormat sdf;

    public CargaCartaoLimPassagens() {
    	sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public void solicitarCarga(int cartaoID, float valor) {
        //if (interfaceLimitePassagensMgt.verificarPossibilidadeCarga(cartaoID, valor)){
        	interfaceCartaoMgt.carregarCartao(cartaoID, valor);	
        //}
        //else {}
    	
    }

    protected ModelAndView buscarCartoes() {
        List cartoes = interfaceCartaoMgt.buscarCartoes();

        Calendar data = Calendar.getInstance();

        ModelAndView mav = new ModelAndView("cargaCartao");
        mav.addObject("cartoes", cartoes);
        mav.addObject("sdf", sdf);
        mav.addObject("data", data);
        return mav;
    }

    protected ModelAndView buscarCartao(int cartaoID) {
        Cartao cartao = interfaceCartaoMgt.buscarCartao(cartaoID);
        ModelAndView mav = new ModelAndView("cargaCartao");
        if (cartao == null) {
            mav.addObject("mensagem", "Cart�o n�o encontrado.");
        } else {
            List cartoes = new ArrayList();
            cartoes.add(cartao);
            Calendar data = Calendar.getInstance();
            mav.addObject("cartoes", cartoes);
            mav.addObject("sdf", sdf);
            mav.addObject("data", data);
        }
        return mav;
    }

    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String operacao = request.getParameter("operacao");
        Cartao cartao;

        if (operacao == null) {
            return buscarCartoes();
        }

        if (operacao.equals("carregar")) {

            int cartaoID = Integer.parseInt(request.getParameter("cartaoID"));

            String strValor = request.getParameter("valor");
            float valor = strValor.trim().matches("[0-9]*\\.?[0-9]+") ? Float.parseFloat(strValor) : 0;

            if (interfaceLimitePassagensMgt.verificarPossibilidadeCarga(cartaoID, valor)){
            	solicitarCarga(cartaoID, valor);
            }
            else {
            	ModelAndView mav = new ModelAndView("cargaCartao");
            	mav.addObject("mensagem", "Carga ultrapassa limite mensal permitido.");
                return mav; 	
            }           
        }

        //Mostrando um cart�o ou todos, dependendo da operacao requisitada
        if (operacao.equals("buscar")) {
            return buscarCartao(Integer.parseInt(request.getParameter("cartaoID")));
        } else {
            return buscarCartoes();
        }
    }

    public ICartaoMgt getInterfaceCartaoMgt() {
        return interfaceCartaoMgt;
    }

    public void setInterfaceCartaoMgt(ICartaoMgt interfaceCartaoMgt) {
        this.interfaceCartaoMgt = interfaceCartaoMgt;
    }

	public ILimitePassagensMgt getInterfaceLimitePassagensMgt() {
		return interfaceLimitePassagensMgt;
	}

	public void setInterfaceLimitePassagensMgt(
			ILimitePassagensMgt interfaceLimitePassagensMgt) {
		this.interfaceLimitePassagensMgt = interfaceLimitePassagensMgt;
	}    

}
