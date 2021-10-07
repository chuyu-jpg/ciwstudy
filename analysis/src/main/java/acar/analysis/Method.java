package acar.analysis;

import javassist.bytecode.Descriptor;

import javax.annotation.Nonnull;
import java.lang.Override;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A java method.
 * @author jens dietrich
 */
public class Method {

    private static AtomicInteger seq = new AtomicInteger(1);

    @Nonnull private String name = null;
    @Nonnull private Class owner = null;
    @Nonnull private String descriptor = null;
    private String signature = null;
    private Integer methodId;

    public Method(@Nonnull String name, @Nonnull Class owner, @Nonnull String descriptor, String signature) {
        this.name = name;
        this.owner = owner;
        this.descriptor = descriptor;
        this.signature = signature;
        methodId = seq.getAndIncrement() ;
    }

    public Integer getMethodId() {
        return methodId;
    }
    public static String getId(String owner,String name,String desc) {
        return owner + ":" + name + desc;
    }

    public String getId() {
        return getId(this.owner.getName(),this.name,this.descriptor);
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public Class getOwner() {
        return owner;
    }

    @Nonnull
    public String getDescriptor() {
        return descriptor;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (!name.equals(method.name)) return false;
        if (!owner.equals(method.owner)) return false;
        if (!descriptor.equals(method.descriptor)) return false;
        return signature != null ? signature.equals(method.signature) : method.signature == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + descriptor.hashCode();
        result = 31 * result + (signature != null ? signature.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "<"+this.owner.getName().replace('/','.')+": " + this.getName() + this.getDescriptor()+">";
    }

    public String doopMethodName() {
        String[] parts = descriptor.split("\\)");
        return "<"+owner.getSootClassName() +
                ": "+Descriptor.toString(parts[1]) + " " +
                 name +
                Descriptor.toString(parts[0]+")")+">" ;

    }
}
