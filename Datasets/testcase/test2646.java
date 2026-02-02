package test.same;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import test.other.OtherClass;

public class SourceClass {
    protected String outerProtected = "protected_field";

    class MemberInner {
        record InnerRec(TargetClass target) {
            @Override
            protected TargetClass overridingMethod() {
                class LocalInner {
                    TargetClass process() {
                        assert target.superField == 1 : "Super field mismatch";
                        return target;
                    }
                }
                LocalInner local = new LocalInner();

                assert target != null : "Target cannot be null";
                Object var = target.instanceField;

                Function<TargetClass, List<String>> func1 = OtherClass::overloadMethod;
                Function<TargetClass, List<String>> func2 = OtherClass::overloadMethod;
                List<String> result = (var != null) ? func1.apply(target) : func2.apply(target);

                var = SourceClass.this.outerProtected;
                target.instanceField = var;

                return local.process();
            }
        }
    }
}

package test.other;

import test.same.TargetClass;
import java.util.List;
import java.util.ArrayList;

public class OtherClass {
    public static List<String> overloadMethod(TargetClass target) {
        return new ArrayList<>(List.of(target.instanceField.toString()));
    }

    public static List<String> overloadMethod(TargetClass target, String suffix) {
        return new ArrayList<>(List.of(target.instanceField + suffix));
    }
}

public class TargetClass extends ParentClass {
    Object instanceField;
    static class StaticNested {}

    protected TargetClass overridingMethod() {
        return this;
    }
}

class ParentClass {
    Object superField = 1;
}