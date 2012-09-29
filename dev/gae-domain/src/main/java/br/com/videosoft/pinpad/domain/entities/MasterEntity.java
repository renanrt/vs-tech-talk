package br.com.videosoft.pinpad.domain.entities;

import java.io.Serializable;


public abstract class MasterEntity {

    public abstract Serializable getId();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MasterEntity)) {
            return false;
        }
        MasterEntity other = (MasterEntity) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }
}
