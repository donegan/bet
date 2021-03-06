package lps.bet.basico.cartaoMgr;

import java.util.List;

import lps.bet.basico.tiposDados.Cartao;
import lps.bet.basico.tiposDados.TipoPassageiro;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TipoPassageiroDAO extends HibernateDaoSupport {

    String hqlBuscarTipoPassagPorCartao;
    	
	public String getHqlBuscarTipoPassagPorCartao() {
		return hqlBuscarTipoPassagPorCartao;
	}

	public void setHqlBuscarTipoPassagPorCartao(String hqlBuscarTipoPassagPorCartao) {
		this.hqlBuscarTipoPassagPorCartao = hqlBuscarTipoPassagPorCartao;
	}

	public void salvarTipoPassageiro(TipoPassageiro tipo) {
        getHibernateTemplate().saveOrUpdate(tipo);
    }

    public void criarTipoPassageiro(TipoPassageiro tipo) {
        salvarTipoPassageiro(tipo);
    }

    public void alterarTipoPassageiro(TipoPassageiro tipo) {
        salvarTipoPassageiro(tipo);
    }

    public TipoPassageiro buscarTipoPassageiro(int tipoID) {
        return (TipoPassageiro) getHibernateTemplate().get(TipoPassageiro.class, tipoID);
    }

    public List buscarTiposPassageiros() {
        return getHibernateTemplate().loadAll(TipoPassageiro.class);
    }

    public TipoPassageiro buscarTipoPassageiro(String nomeTipo) {
        DetachedCriteria tipoPorNome = DetachedCriteria.forClass(TipoPassageiro.class);
        tipoPorNome.add(Restrictions.eq("nomeTipo", nomeTipo));
        List tipos = getHibernateTemplate().findByCriteria(tipoPorNome);
        return (TipoPassageiro) tipos.get(0);
    }

    public void removerTipoPassageiro(TipoPassageiro tipo) {
        getHibernateTemplate().delete(tipo);
    }
 
	public TipoPassageiro buscarTipoPassagPorCartao(Cartao cartao){
		List tipos = getHibernateTemplate().find(hqlBuscarTipoPassagPorCartao, cartao);
		if (tipos.isEmpty()) 
			return null;
		else 
			return (TipoPassageiro) tipos.get(0);	
	}

    public List buscarTodosTipos() {
        return getHibernateTemplate().loadAll(TipoPassageiro.class);
    }

    public List buscarTiposPermitidos(List tiposJaAdquiridos) {
        List tiposPermitidos = buscarTodosTipos();
        tiposPermitidos.removeAll(tiposJaAdquiridos);
        return tiposPermitidos;
    }

    public void removerTipoPassageiro(int tipoID) {
        TipoPassageiro tipo = buscarTipoPassageiro(tipoID);
        removerTipoPassageiro(tipo);
    }

}
