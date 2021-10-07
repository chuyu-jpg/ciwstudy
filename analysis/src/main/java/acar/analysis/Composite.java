package acar.analysis;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents an instance of the composite design pattern with the additional constraint that there is a recursive method.
 * @author jens dietrich
 */
public class Composite {
    private Class container = null;
    private Class component = null;
    private Set<Method> recursiveMethods = new HashSet<>();

    public Composite(Class container, Class component) {
        this.container = container;
        this.component = component;
    }

    public void addMethod(Method m) {
        recursiveMethods.add(m);
    }

    public Class getContainer() {
        return container;
    }

    public Class getComponent() {
        return component;
    }

    public Set<Method> getRecursiveMethods() {
        return recursiveMethods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Composite composite = (Composite) o;

        if (container != null ? !container.equals(composite.container) : composite.container != null) return false;
        if (component != null ? !component.equals(composite.component) : composite.component != null) return false;
        return recursiveMethods != null ? recursiveMethods.equals(composite.recursiveMethods) : composite.recursiveMethods == null;
    }

    @Override
    public int hashCode() {
        int result = container != null ? container.hashCode() : 0;
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (recursiveMethods != null ? recursiveMethods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Composite{" +
                "container=" + container +
                ", component=" + component +
                ", recursiveMethods=" + recursiveMethods +
                '}';
    }
}
