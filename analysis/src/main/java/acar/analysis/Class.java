package acar.analysis;

import com.google.common.collect.Sets;

import javax.annotation.Nonnull;
import java.lang.Override;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A Java class.
 * @author jens dietrich
 */
public class Class {

    public static enum Kind {INTERFACE, CLASS, ENUM}

    private static AtomicInteger seq = new AtomicInteger(1);

    @Nonnull private String name = null;
    @Nonnull private Set<Method> methods = new HashSet<>();
    @Nonnull private Set<Method> inheritedMethods = new HashSet<>();
    @Nonnull private Kind kind = Kind.CLASS;
    @Nonnull private Integer classId;

    public Class(@Nonnull String name, @Nonnull Kind kind) {
        this.name = name;
        this.kind = kind;
        classId = seq.getAndIncrement();
    }

    public int getClassId() {
        return classId;
    }

    @Nonnull
    public Set<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        this.methods.add(method);
    }

    @Nonnull
    public Set<Method> getInheritedMethods() {
        return inheritedMethods;
    }

    public void addInheritedMethod(Method method) {
        this.inheritedMethods.add(method);
    }

    @Nonnull
    public Set<Method> getAllMethods() {
        return Sets.union(methods,inheritedMethods);
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getSootClassName() {
        return name.replaceAll("/", ".");
    }

    @Nonnull
    public Kind getKind() {
        return kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Class aClass = (Class) o;

        return name != null ? name.equals(aClass.name) : aClass.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Class{" + name + '}';
    }
}
