package acar.analysis.minicfg;

import org.objectweb.asm.Label;

import java.util.Objects;

public class JumpNode extends Node {
    public String location;
    public int opcode;
    public JumpNode(Label l, int opcode) {
        this.location = l.toString();
        this.opcode = opcode;
    }

    public String toString() {
        return "JUMP" + location;
    }
}
