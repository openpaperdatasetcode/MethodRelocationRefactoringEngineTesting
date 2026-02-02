package com.refactoring.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.ArrayList;

@Retention(RetentionPolicy.RUNTIME)
@interface RefactorAnnotation {}

class OthersClass {
    public static List<String> staticMethod(TargetClass target) {
        List<String> list = new ArrayList<>();
        list.add(target.targetField + "_" + 1);
        return list;
    }
}

class TargetParentClass {}

class TargetClass extends TargetParentClass {
    String targetField;

    public TargetClass() {
        this.targetField = "default";
    }

    public void targetMethod() {
        class TargetLocalInner {
            void updateField(TargetClass target) {
                target.targetField += "_local_inner";
            }
        }
        new TargetLocalInner().updateField(this);
    }
}

private sealed class SourceClass implements Runnable permits SourceSubClass {
    class SourceInnerClass {
        @RefactorAnnotation
        private int moveMethod(TargetClass... targetParams) {
            if (targetParams == null || targetParams.length == 0) {
                return 0;
            }

            List<String>[] arr = new List[] {OthersClass.staticMethod(targetParams[0])};

            int count = 0;
            do {
                targetParams[0].targetField = "do_iter_" + count;
                count++;
            } while (count < 3);

            String varCall = targetParams[0].targetField;
            targetParams[0].targetField = varCall + "_var_modified";

            List rawList = new ArrayList();
            rawList.add(targetParams[0].targetField);

            class LocalInnerOne {}
            class LocalInnerTwo {}

            return rawList.size();
        }
    }

    @Override
    public void run() {}
}

final class SourceSubClass extends SourceClass {}