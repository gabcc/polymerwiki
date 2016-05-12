package hu.gab.wiki.server.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author PG
 * @since 2016-05-12
 */

@Entity
abstract public class AbstractEntity implements Serializable {

    @Id
    private long id;

    public AbstractEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
