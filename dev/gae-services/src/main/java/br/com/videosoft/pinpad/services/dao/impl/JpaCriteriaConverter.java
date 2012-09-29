package br.com.videosoft.pinpad.services.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class responsible for converting an {@link EntityCriteria} to the actual JPA query.
 *
 */
public class JpaCriteriaConverter {

    private static final Logger LOG = LoggerFactory.getLogger(JpaCriteriaConverter.class);

    private StringBuilder jpaQl;
    private List<Object> bindList;
    private int n;
    private Query query;
    private EntityCriteria criteria;

    // CONSTRUCTOR ---------------------------------------------------------------------------------

    /**
     * Creates a new instance and converts the {@link EntityCriteria} to a suitable JPA query.
     */
    public JpaCriteriaConverter(EntityCriteria criteria,
                                Class<?> entityClass,
                                EntityManager entityManager) {
        this.criteria = criteria;
        initQueryString(entityClass);
        initQuery(entityManager);
        bindList = null;
        jpaQl = null;
    }

    // PUBLIC METHODS ------------------------------------------------------------------------------

    /**
     * Gets the generated JPA query pre-configured with all bindings, order by's, etc.
     *
     * @return query
     */
    public Query getQuery() {
        return query;
    }

    // HELPER METHODS ------------------------------------------------------------------------------

    private void initQueryString(Class<?> type) {
        jpaQl = new StringBuilder();
        n = 1;
        bindList = new ArrayList<Object>();
        jpaQl.append("select object(o) from " + type.getName() + " o ");
        for (String property : criteria.getJoinFetch()) {
            jpaQl.append(" inner join fetch o.").append(property);
        }
        for (String property : criteria.getAliasJoinFetch()) {
            jpaQl.append(" inner join fetch ").append(property);
        }
        for (String property : criteria.getOuterJoinFetch()) {
            jpaQl.append(" left outer join fetch o.").append(property);
        }
        
        for (String property : criteria.getAliasOuterJoinFetch()) {
            jpaQl.append(" left outer join fetch ").append(property);
        }

        if (!criteria.getRestrictions().isEmpty()) {
            jpaQl.append(" where ");
        }
        boolean andOperator = false;
        for (EntityCriteria.Restriction r : criteria.getRestrictions()) {
            if (andOperator) {
                jpaQl.append(" and ");
            }
            andOperator = true;
            switch (r.getType()) {
                case EQ :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " = ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case NE :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " <> ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case LT :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " < ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case LE :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " <= ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case GT :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " > ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case GE :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " >= ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case NULL :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " is null");
                    break;
                case NOT_NULL :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " is not null");
                    break;
                case LIKE :
                    jpaQl.append("o.");
                    jpaQl.append(r.getProperty() + " like ?" + n++);
                    bindList.add(r.getValue());
                    break;
                case ILIKE :
                    jpaQl.append("upper(o." + r.getProperty() + ") like upper(?" + (n++) + ")");
                    bindList.add(r.getValue());
                    break;
                case IN :
                    genInList(r, false);
                    break;
                case N_IN :
                    genInList(r, true);
                    break;
            }
        }
        andOperator = false;
        boolean comma = false;
        for (EntityCriteria.OrderBy o : criteria.getOrderBy()) {
            if (comma) {
                jpaQl.append(", ");
            } else {
                jpaQl.append(" order by ");
            }
            if (o.isAsc()) {
                jpaQl.append("o." + o.getProperty() + " asc");
            } else {
                jpaQl.append("o." + o.getProperty() + " desc");
            }
            comma = true;
        }
        LOG.debug("Resulting JPA-QL: " + jpaQl.toString());
    }

    private void initQuery(EntityManager em) {
        query = em.createQuery(jpaQl.toString());
        int i = 1;
        for (Object v : bindList) {
            query.setParameter(i++, v);
        }
        if (criteria.getFirstResult() != null) {
            query.setFirstResult(criteria.getFirstResult());
        }
        if (criteria.getMaxResults() != null) {
            query.setMaxResults(criteria.getMaxResults());
        }
    }

    private void genInList(EntityCriteria.Restriction r, boolean not) {
        boolean comma = false;
        
        String notStr = not ? " not " : "";

        jpaQl.append("o." + r.getProperty() + notStr +" in (");

        if (r.getValue() instanceof Collection) {

            for (Object item : ((Collection<?>) r.getValue())) {
                if (comma) {
                    jpaQl.append(",");
                }
                jpaQl.append("?" + (n++));
                bindList.add(item);
                comma = true;
            }

            jpaQl.append(" )");
        } else if (r.getValue() instanceof Object[]) {
            comma = false;
            for (Object item : ((Collection<?>) r.getValue())) {
                if (comma) {
                    jpaQl.append(",");
                }
                jpaQl.append("?" + (n++));
                bindList.add(item);
                comma = true;
            }

            jpaQl.append(r.getProperty() + " )");

        } else {
            throw new IllegalArgumentException("Unsupported collection/array type");
        }
    }

}