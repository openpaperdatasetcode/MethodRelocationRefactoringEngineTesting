package test;

import otherpackage.OtherClass;
import java.util.function.Consumer;

interface SourceInterface<T> {
    static class StaticNested {}
    class MemberInner {}

    private default int innerMethod(TargetInterface targetParam) {
        // SuperConstructorInvocation: public, diff_package_others, this.field (1)
        otherpackage.OtherClass.ExtendedInner extendedInner = new otherpackage.OtherClass.ExtendedInner(this.toString());

        // Accessor method from inner_class in ternary
        Consumer<TargetInterface.MemberInner> accessor = TargetInterface.MemberInner::setValue;
        TargetInterface.MemberInner targetInner = targetParam.new MemberInner();
        accessor.accept(targetInner);

        // Try statement
        try {
            targetInner.doAction();
        } catch (Exception e) {
            return -1;
        }

        // Variable call + access_outer_private
        targetParam.execute();
        PrivateHelper helper = new PrivateHelper();
        helper.usePrivateField();

        // Call_method: others_class private normal in do-while
        Object result;
        do {
            result = extendedInner.this.method("arg");
        } while (result == null);

        return 0;
    }

    private static class PrivateHelper {
        private String privateField = "privateValue";
        void usePrivateField() { System.out.println(privateField); }
    }
}

abstract interface TargetInterface extends ParentInterface {
    class MemberInner {
        private String value;
        void setValue() { this.value = "set"; } // Accessor feature
        void doAction() {}
    }
    void execute();
}

interface ParentInterface {}

// Different package: otherpackage
package otherpackage;

public class OtherClass {
    public String field = "otherField";

    public class ExtendedInner extends OtherClass {
        public ExtendedInner(String field) {
            super();
            this.field = field;
        }

        private Object method(String arg) {
            return arg + this.field;
        }
    }
}