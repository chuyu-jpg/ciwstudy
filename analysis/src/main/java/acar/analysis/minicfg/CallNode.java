package acar.analysis.minicfg;

import acar.analysis.Method;

import java.util.Objects;

public class CallNode extends Node {
    public Method target;
    static int i = 0;
    int id = 0;
    public String toString() {

        if (target.getName().equals("<init>"))
            return  "init"+id;
        else
            return target.getName()+id;
    }
    public CallNode(Method t) {
        this.target = t;
        id = i++;
    }
}
