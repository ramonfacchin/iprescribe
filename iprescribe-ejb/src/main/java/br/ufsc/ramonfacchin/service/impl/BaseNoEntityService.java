package br.ufsc.ramonfacchin.service.impl;

import java.util.List;

import javax.jws.WebMethod;

import br.ufsc.ramonfacchin.dao.IDAO;
import br.ufsc.ramonfacchin.dao.impl.BaseDAO;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.IServiceLocal;

/**
 * Basic implementation for Service Beans not linked to an Entity.
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 *
 */
public abstract class BaseNoEntityService extends BaseService<BaseEntity> implements IServiceLocal<BaseEntity> {

	private static final long serialVersionUID = 8505097282242510141L;

	@WebMethod(exclude=true)
	@Override
	public void save(BaseEntity entity) {
		throw new RuntimeException("error.non.supported.operation");
	}

	@WebMethod(exclude=true)
	@Override
	public void remove(BaseEntity entity) {
		throw new RuntimeException("error.non.supported.operation");
	}

	@WebMethod(exclude=true)
	@Override
	public BaseEntity findById(Long id) {
		throw new RuntimeException("error.non.supported.operation");
	}

	@WebMethod(exclude=true)
	@Override
	public Long listCount(Boolean listOnlyActive) {
		throw new RuntimeException("error.non.supported.operation");
	}

	@WebMethod(exclude=true)
	@Override
	public List<BaseEntity> listAll() {
		throw new RuntimeException("error.non.supported.operation");
	}

	@WebMethod(exclude=true)
	@Override
	public List<BaseEntity> listActive() {
		throw new RuntimeException("error.non.supported.operation");
	}

	@WebMethod(exclude=true)
	@Override
	public List<BaseEntity> listInactive() {
		throw new RuntimeException("error.non.supported.operation");
	}
	
	protected <D extends BaseDAO<BaseEntity>> IDAO<BaseEntity> getDAO() {
		throw new RuntimeException("error.non.supported.operation");
	}

}
