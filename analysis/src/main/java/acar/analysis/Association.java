package acar.analysis;

import javax.annotation.Nonnull;

public class Association {
    public static enum Kind {ONE,MANY}
    @Nonnull  private Kind kind = Kind.ONE;

    public Association(Kind kind) {
        this.kind = kind;
    }

    public Kind getKind() {
        return kind;
    }

}
