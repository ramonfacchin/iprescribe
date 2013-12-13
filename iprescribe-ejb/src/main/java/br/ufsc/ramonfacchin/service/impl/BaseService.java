package br.ufsc.ramonfacchin.service.impl;

import java.util.List;

import javax.jws.WebMethod;

import br.ufsc.ramonfacchin.dao.IDAO;
import br.ufsc.ramonfacchin.dao.impl.BaseDAO;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.IServiceLocal;

/**
 * Basic implementation for Service Beans.
 * Every DAO should extend this class and implement {@link IDAO}.
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 *
 * @param <T>
 */
public abstract class BaseService<T extends BaseEntity> implements IServiceLocal<T> {

	private static final long serialVersionUID = 8505097282242510141L;

	@WebMethod(exclude=true)
	@Override
	public void save(T entity) {
		if (entity.getId() != null) {
			getDAO().update(entity);
		} else {
			getDAO().persist(entity);
		}
	}

	@WebMethod(exclude=true)
	@Override
	public void remove(T entity) {
		getDAO().remove(findById(entity.getId()));
	}

	@WebMethod(exclude=true)
	@Override
	public T findById(Long id) {
		return getDAO().findById(id);
	}

	@WebMethod(exclude=true)
	@Override
	public Long listCount(Boolean listOnlyActive) {
		return getDAO().listCount(listOnlyActive);
	}

	@WebMethod(exclude=true)
	@Override
	public List<T> listAll() {
		return getDAO().listAll();
	}

	@WebMethod(exclude=true)
	@Override
	public List<T> listActive() {
		return getDAO().listActive();
	}

	@WebMethod(exclude=true)
	@Override
	public List<T> listInactive() {
		return getDAO().listInactive();
	}
	
	protected abstract <D extends BaseDAO<T>> IDAO<T> getDAO();

}
