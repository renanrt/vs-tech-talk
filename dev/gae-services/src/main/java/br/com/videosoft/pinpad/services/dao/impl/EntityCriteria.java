package br.com.videosoft.pinpad.services.dao.impl;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO that represents a simple and portable criteria for retrieving entities of a certain type
 * based on restrictions on the values of their properties.
 * <p>
 * The underlying details of how the query is actually performed depend entirely on the persistence
 * layer.
 *
 */
public class EntityCriteria implements Serializable {

    private static final long serialVersionUID = 3365832644914360605L;

    private final List<Restriction> restrictions = new ArrayList<Restriction>();
    private final List<OrderBy> orderBy = new ArrayList<OrderBy>();
    private Boolean cacheable;
    private Integer maxResults;
    private Integer firstResult;
    private List<String> joinFetch = new ArrayList<String>();
    private List<String> aliasJoinFetch = new ArrayList<String>();
    private List<String> outerJoinFetch = new ArrayList<String>();
    private List<String> aliasOuterJoinFetch = new ArrayList<String>();


    // RESTRICTION METHODS -------------------------------------------------------------------------

    public EntityCriteria cacheable() {
        cacheable = true;
        return this;
    }

    
    public EntityCriteria addAliasJoinFetch(String property) {
    	aliasJoinFetch.add(property);
    	return this;
    }
    
    public List<String> getAliasJoinFetch() {
		return aliasJoinFetch;
	}
    
    public List<String> getAliasOuterJoinFetch() {
		return aliasOuterJoinFetch;
	}
    
    public EntityCriteria addJoinFetch(String property) {
        joinFetch.add(property);
        return this;
    }

    public EntityCriteria addOuterJoinFetch(String property) {
        outerJoinFetch.add(property);
        return this;
    }
    
    public EntityCriteria addAliasOuterJoinFetch(String property) {
    	aliasOuterJoinFetch.add(property);
        return this;
    }

    public EntityCriteria addEq(String property, Object value) {
        if (value == null) {
            throw new IllegalArgumentException("The value cannot be NULL");
        }
        restrictions.add(new Restriction(RestrictionType.EQ, property, value));
        return this;
    }

    public EntityCriteria addNe(String property, Object value) {
        restrictions.add(new Restriction(RestrictionType.NE, property, value));
        return this;
    }

    public EntityCriteria addGt(String property, Object value) {
        restrictions.add(new Restriction(RestrictionType.GT, property, value));
        return this;
    }

    public EntityCriteria addGe(String property, Object value) {
        restrictions.add(new Restriction(RestrictionType.GE, property, value));
        return this;
    }

    public EntityCriteria addLt(String property, Object value) {
        restrictions.add(new Restriction(RestrictionType.LT, property, value));
        return this;
    }

    public EntityCriteria addLe(String property, Object value) {
        restrictions.add(new Restriction(RestrictionType.LE, property, value));
        return this;
    }

    public EntityCriteria addBetween(String property, Object minValue, Object maxValue) {
        addGe(property, minValue);
        addLe(property, maxValue);
        return this;
    }

    public EntityCriteria addIsNull(String property) {
        restrictions.add(new Restriction(RestrictionType.NULL, property));
        return this;
    }

    public EntityCriteria addNotNull(String property) {
        restrictions.add(new Restriction(RestrictionType.NOT_NULL, property));
        return this;
    }

    public EntityCriteria addLike(String property, String pattern) {
        restrictions.add(new Restriction(RestrictionType.LIKE, property, pattern));
        return this;
    }

    public EntityCriteria addILike(String property, String pattern) {
        restrictions.add(new Restriction(RestrictionType.ILIKE, property, pattern));
        return this;
    }

    public EntityCriteria addIn(String property, List<?> elements) {
        if (elements != null && !elements.isEmpty()) {
            restrictions.add(new Restriction(RestrictionType.IN, property, elements));
        }
        return this;
    }
    
    public EntityCriteria addNotIn(String property, List<?> elements) {
        if (elements != null && !elements.isEmpty()) {
            restrictions.add(new Restriction(RestrictionType.N_IN, property, elements));
        }
        return this;
    }

    public EntityCriteria addOrderBy(String property, boolean asc) {
        orderBy.add(new OrderBy(property, asc));
        return this;
    }

    public EntityCriteria maxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    public EntityCriteria firstResult(int firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    // OTHER METHODS -------------------------------------------------------------------------------

    public Integer getMaxResults() {
        return maxResults;
    }

    public Integer getFirstResult() {
        return firstResult;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public List<OrderBy> getOrderBy() {
        return orderBy;
    }

    public List<String> getJoinFetch() {
        return joinFetch;
    }

    public List<String> getOuterJoinFetch() {
        return outerJoinFetch;
    }

    /**
     * Returns this criteria to the initial state, so that it can be re-used.
     *
     * @return this criteria
     */
    public EntityCriteria clear() {
        restrictions.clear();
        orderBy.clear();
        cacheable = null;
        maxResults = null;
        firstResult = null;
        joinFetch.clear();
        outerJoinFetch.clear();
        return this;
    }

    public Boolean getCacheable() {
        return cacheable;
    }

    // INNER CLASSES -------------------------------------------------------------------------------

    /**
     * Represents an order by column along with the direction.
     */
    public static class OrderBy implements Serializable {

        private static final long serialVersionUID = -8547096550591193511L;

        private final String property;
        private final boolean asc;

        public OrderBy(String property, boolean asc) {
            this.property = property;
            this.asc = asc;
        }

        public String getProperty() {
            return property;
        }

        public boolean isAsc() {
            return asc;
        }
    }

    public static class Restriction implements Serializable {

        private static final long serialVersionUID = -1685266983477061681L;

        private final RestrictionType type;
        private final String property;
        private final Object value;

        public Restriction(RestrictionType type, String property) {
            this(type, property, null);
        }

        public Restriction(RestrictionType type, String property, Object value) {
            this.type = type;
            this.property = property;
            this.value = value;
        }

        public RestrictionType getType() {
            return type;
        }

        public String getProperty() {
            return property;
        }

        public Object getValue() {
            return value;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Restriction that = (Restriction) o;
            if (!property.equals(that.property)) {
                return false;
            }
            if (type != that.type) {
                return false;
            }
            if (value != null ? !value.equals(that.value) : that.value != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int result;
            result = type.hashCode();
            result = 31 * result + property.hashCode();
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }
    }

    /**
     * Enumeration that contains all possible types of restrictions supported by this criteria.
     */
    public enum RestrictionType {
        EQ, NE, GT, GE, LT, LE, BETWEEN, NULL, NOT_NULL, LIKE, ILIKE, IN, N_IN
    }

}