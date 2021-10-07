package acar.analysis;

import javax.annotation.Nonnull;
import java.lang.Override;

/**
 * A subtype relationship between classes.
 * @author jens dietrich
 */

public class SubtypeOf {
    public static enum Kind {EXTENDS,IMPLEMENTS}

    @Nonnull
    private Kind kind = null;

    public SubtypeOf(@Nonnull Kind kind) {
        this.kind = kind;
    }

    @Nonnull
    public Kind getKind() {
        return kind;
    }

}
