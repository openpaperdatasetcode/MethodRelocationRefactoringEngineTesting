package com.sourcepkg;

import com.targetpkg.TargetInterface;
import java.util.ArrayList;
import java.util.List;

interface SourceInterface {
    class SourceInner {
        TargetInterface.TargetInner targetInner = new TargetInterface.TargetInner();
    }

    static class SourceStaticNested {
        void helperMethod1() {}
        void helperMethod2() {}
        void helperMethod3() {}
    }

    @MyAnnotation
    default int sourceMethod(TargetInterface.TargetInner param) {
        List rawList = new ArrayList();
        rawList.add(param);

        int count = 0;
        do {
            synchronized (param) {
                count += param.innerField;
            }
        } while (count < 5);

        SourceStaticNested staticNested = new SourceStaticNested();
        if (count == 1) {
            staticNested.helperMethod1();
        } else if (count == 2) {
            staticNested.helperMethod2();
        } else {
            staticNested.helperMethod3();
        }

        OthersClass.getInst().init().config().execute();
        return count;
    }

    @interface MyAnnotation {}
}

strictfp class OthersClass {
    private static OthersClass inst = new OthersClass();
    public static OthersClass getInst() { return inst; }
    public OthersClass init() { return this; }
    public OthersClass config() { return this; }
    public void execute() {}
}