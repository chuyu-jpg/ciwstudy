package acar.analysis;


import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Set;

public class Field {

    public Field(String name, Class owner, String desc, String sig, Set<String> paramTypes) {
        this.name = name;
        this.owner = owner;
        this.descriptor = desc;
        this.signature = sig;
        this.paramTypes = paramTypes;


    }

    public Class getOwner() {
        return owner;
    }
    public String getName() {
        return name;
    }
    public Set<String> getParamTypes() {
        return paramTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return Objects.equals(name, field.name) &&
                Objects.equals(owner, field.owner);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, owner);
    }

    @Nonnull private String name = null;
    @Nonnull private Class owner = null;
    @Nonnull private String descriptor = null;
    private String signature = null;
    private Set<String> paramTypes;


}
