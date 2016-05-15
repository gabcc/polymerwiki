package hu.gab.wiki.shared.dto.useradmin;

import hu.gab.wiki.shared.DTO_Base;

/**
 * @author PG
 * @since 2016-05-15
 */
public class DTO_Role implements DTO_Base {

    private long id;
    private String name;

    public DTO_Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DTO_Role dto_role = (DTO_Role) o;

        if (id != dto_role.id) return false;
        return name != null ? name.equals(dto_role.name) : dto_role.name == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
