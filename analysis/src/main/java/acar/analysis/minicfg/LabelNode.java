package acar.analysis.minicfg;

import org.objectweb.asm.Label;

import java.util.Objects;

public class LabelNode extends Node {
    public String label;
    public LabelNode(Label l) {
        this.label = l.toString();
    }


    public String toString() {
        return label;
    }
}
