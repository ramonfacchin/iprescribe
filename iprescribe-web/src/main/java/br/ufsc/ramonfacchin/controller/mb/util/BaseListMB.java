package br.ufsc.ramonfacchin.controller.mb.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.IService;
import br.ufsc.ramonfacchin.service.impl.BaseService;

/**
 * Base Managed Bean for Listing and Pagination purposes.
 * Every Managed Bean that extends {@link BaseListMB} should be {@link SessionScoped}.
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 *
 * @param <T>
 */
public abstract class BaseListMB<T extends BaseEntity> extends BaseMB {

	private static final long serialVersionUID = 8172186243633071279L;
	
	private int count;
	private int resultsPerPage;
	private int firstResultIndex;
	private List<T> list;
	
	@PostConstruct
	private void postConstruct() {
		setResultsPerPage(10);
		executeQuery();
	}
	
	/**
	 * Executes the query and fills attribute 'list' with the 
	 * results and 'count' with the number of matches.
	 */
	public void executeQuery() {
		Long countBD = getService().listCount(true);
		count = countBD != null ? countBD.intValue() : 0;
		list = getService().list(getResultsPerPage(), getFirstResultIndex(), true);
	}
	
	/**
	 * Performs the query and returns the number of results found.
	 * @return
	 */
	public Long listCount() {
		return getService().listCount(true);
	}
	
	/**
	 * Performs the query and returns its results.
	 * @return
	 */
	public List<T> list() {
		return getService().list(getResultsPerPage(), getFirstResultIndex(), true);
	}
	
	/**
	 * Returns the Service Bean that should perform queries for this Bean.
	 * @return
	 */
	protected abstract <S extends BaseService<T>> IService<T> getService();

	/**
	 * @return the number of results that should be displayed per page.
	 */
	public int getResultsPerPage() {
		if (resultsPerPage == 0) {
			resultsPerPage = 10;
		}
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	/**
	 * @return the real index (0-based) of the first result that should be displayed.
	 */
	public int getFirstResultIndex() {
		return firstResultIndex;
	}

	public void setFirstResultIndex(int firstResultIndex) {
		this.firstResultIndex = firstResultIndex;
	}

	/**
	 * @return the number of results found.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the in memory list filled with the results (for the last executeQuery() called).
	 */
	public List<T> getList() {
		if (list == null) {
			list = new ArrayList<T>();
		}
		return list;
	}
	
	/**
	 * @return the current page number (1-based).
	 */
	public int getCurrentPage() {
		if (getFirstResultIndex() == 0) {
			return 1;
		}
		int fri = getFirstResultIndex();
		BigDecimal firstResultIndexBD = new BigDecimal(fri);
		BigDecimal resultsPerPageBD = new BigDecimal(getResultsPerPage());
		int rdiv = fri % getResultsPerPage();
		int div = firstResultIndexBD.divide(resultsPerPageBD).intValue();
		return rdiv != 0 ? div : div+1;
	}
	
	/**
	 * @return the number of pages (1-based).
	 */
	public int getPageCount() {
		BigDecimal countBD = new BigDecimal(getCount());
		BigDecimal resultsPerPageBD = new BigDecimal(getResultsPerPage());
		int rdiv = getCount() % getResultsPerPage();
		int div = countBD.divide(resultsPerPageBD).intValue();
		return rdiv > 0 ? div+1 : div;
	}
	
	/**
	 * @return the number of pages (0-based)
	 */
	protected int getRealPageCount() {
		int pageCount = getPageCount();
		return pageCount == 0 ? 0 : pageCount-1;
	}
	
	/**
	 * @return the current page number (0-based)
	 */
	protected int getCurrentPageIndex() {
		int currentPage = getCurrentPage();
		return currentPage == 0 ? 0 : currentPage-1;
	}
	
	/**
	 * @return <code>true</code> if there is a previous page, <code>false</code> otherwise.
	 */
	public boolean hasPreviousPage() {
		return getCurrentPageIndex() > 0;
	}
	
	/**
	 * @return <code>true</code> if there is a next page, <code>false</code> otherwise.
	 */
	public boolean hasNexPage() {
		return getCurrentPageIndex() < getRealPageCount();
	}
	
	/**
	 * Goes to the next page, and reexecutes the query.
	 */
	public void goToNextPage() {
		if (hasNexPage()) {
			setFirstResultIndex(getFirstResultIndex()+getResultsPerPage());
			executeQuery();
		}
	}
	
	/**
	 * Goes to the previous page, and reexecutes the query.
	 */
	public void goToPreviousPage() {
		if (hasPreviousPage()) {
			setFirstResultIndex(getFirstResultIndex()-getResultsPerPage());
			executeQuery();
		}
	}
	
	/**
	 * Goes to the first page, and reexecutes the query.
	 */
	public void goToFirstPage() {
		setFirstResultIndex(0);
		executeQuery();
	}
	
	/**
	 * Goes to the last page, and reexecutes the query.
	 */
	public void goToLastPage() {
		setFirstResultIndex(getRealPageCount()*getResultsPerPage());
		executeQuery();
	}
	
	/**
	 * Goes to the given page, and reexecutes the query.
	 * @param pageNumber desired page number (1-based).
	 */
	public void goToPage(int pageNumber) {
		if (pageNumber <= getPageCount()) {
			pageNumber = pageNumber == 0 ? 0 : pageNumber - 1;
			setFirstResultIndex(pageNumber*getResultsPerPage());
		}
	}

}
