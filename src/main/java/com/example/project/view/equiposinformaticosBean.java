package com.example.project.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.project.model.equiposinformaticos;

/**
 * Backing bean for equiposinformaticos entities.
 * <p/>
 * This class provides CRUD functionality for all equiposinformaticos entities.
 * It focuses purely on Java EE 6 standards (e.g.
 * <tt>&#64;ConversationScoped</tt> for state management,
 * <tt>PersistenceContext</tt> for persistence, <tt>CriteriaBuilder</tt> for
 * searches) rather than introducing a CRUD framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class equiposinformaticosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving equiposinformaticos entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private equiposinformaticos equiposinformaticos;

	public equiposinformaticos getequiposinformaticos() {
		return this.equiposinformaticos;
	}

	public void setequiposinformaticos(equiposinformaticos equiposinformaticos) {
		this.equiposinformaticos = equiposinformaticos;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(unitName = "inventarioaspa-persistence-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		this.conversation.setTimeout(1800000L);
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversation.setTimeout(1800000L);
		}

		if (this.id == null) {
			this.equiposinformaticos = this.example;
		} else {
			this.equiposinformaticos = findById(getId());
		}
	}

	public equiposinformaticos findById(Long id) {

		return this.entityManager.find(equiposinformaticos.class, id);
	}

	/*
	 * Support updating and deleting equiposinformaticos entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.equiposinformaticos);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.equiposinformaticos);
				return "view?faces-redirect=true&id="
						+ this.equiposinformaticos.getId();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			equiposinformaticos deletableEntity = findById(getId());

			this.entityManager.remove(deletableEntity);
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching equiposinformaticos entities with pagination
	 */

	private int page;
	private long count;
	private List<equiposinformaticos> pageItems;

	private equiposinformaticos example = new equiposinformaticos();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public equiposinformaticos getExample() {
		return this.example;
	}

	public void setExample(equiposinformaticos example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<equiposinformaticos> root = countCriteria
				.from(equiposinformaticos.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<equiposinformaticos> criteria = builder
				.createQuery(equiposinformaticos.class);
		root = criteria.from(equiposinformaticos.class);
		TypedQuery<equiposinformaticos> query = this.entityManager
				.createQuery(criteria.select(root).where(
						getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<equiposinformaticos> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String serie = this.example.getSerie();
		if (serie != null && !"".equals(serie)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("serie")),
					'%' + serie.toLowerCase() + '%'));
		}
		String marca = this.example.getMarca();
		if (marca != null && !"".equals(marca)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("marca")),
					'%' + marca.toLowerCase() + '%'));
		}
		String color = this.example.getColor();
		if (color != null && !"".equals(color)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("color")),
					'%' + color.toLowerCase() + '%'));
		}
		String estatus = this.example.getEstatus();
		if (estatus != null && !"".equals(estatus)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("estatus")),
					'%' + estatus.toLowerCase() + '%'));
		}
		String modelo = this.example.getModelo();
		if (modelo != null && !"".equals(modelo)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("modelo")),
					'%' + modelo.toLowerCase() + '%'));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<equiposinformaticos> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back equiposinformaticos entities (e.g. from
	 * inside an HtmlSelectOneMenu)
	 */

	public List<equiposinformaticos> getAll() {

		CriteriaQuery<equiposinformaticos> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(equiposinformaticos.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(equiposinformaticos.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final equiposinformaticosBean ejbProxy = this.sessionContext
				.getBusinessObject(equiposinformaticosBean.class);

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ejbProxy.findById(Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((equiposinformaticos) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private equiposinformaticos add = new equiposinformaticos();

	public equiposinformaticos getAdd() {
		return this.add;
	}

	public equiposinformaticos getAdded() {
		equiposinformaticos added = this.add;
		this.add = new equiposinformaticos();
		return added;
	}
}
