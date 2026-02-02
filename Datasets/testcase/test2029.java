package test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class SourceClass<T> {
    class MemberInner {}

    {
        new Runnable() {};
    }

    Object instanceMethod(TargetClass<String>.TargetInner inner) {
        TargetClass<Integer> target = new TargetClass<>();
        variableCall = inner.field;
        TargetClass rawTarget = new TargetClass();
        abstractMethod1();
        abstractMethod2();

        List<TargetClass<String>.TargetInner> list = Arrays.asList(inner);
        list.forEach(item -> this.protectedMethod(1));

        int i = 0;
        switch (i) {
            case 1:
                break;
            default:
                break;
        }

        do {
            int result = new OtherClass().overloadMethod(inner);
        } while (i < 1);

        return variableCall;
    }

    protected List<String> protectedMethod(int num) {
        return new ArrayList<>();
    }

    abstract void abstractMethod1();
    abstract void abstractMethod2();

    TargetClass<?>.TargetInner variableCall;
}

public class TargetClass<S> {
    static class TargetStaticNested {}

    class TargetInner {
        S field;
    }
}

class OtherClass {
    int overloadMethod(TargetClass<?>.TargetInner inner) {
        return 0;
    }

    int overloadMethod(String str) {
        return 1;
    }
}
package test;

record SourceRecord(int id) {
    static class StaticNested {}

    {
        new Runnable() {};
    }

    @Override
    public void equals(Object obj) {
        return false;
    }

    public void instanceMethod(TargetRecord target) {
        variableCall = target.field;

        private void privateContinue() {
            for (int i = 0; i < 5; i++) {
                if (target.superField == 3) {
                    continue;
                }
            }
        }
        privateContinue();
    }

    TargetRecord variableCall;
}

public record TargetRecord(String data) extends ParentRecord {
    int field;

    static class TargetStaticNested {}
}

record ParentRecord() {
    int superField;
}