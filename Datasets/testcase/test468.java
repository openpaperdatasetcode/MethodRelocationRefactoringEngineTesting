package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Source normal class (public modifier, same package as target, anonymous + member inner class)
public class SourceClass {
    // Member inner class (source_inner for method position)
    public class MemberInnerClass {
        private int continueField = 1; // Field for ContinueStatement (this.field + 1)

        // Target generic method (private access, List<String> return, source_inner)
        // Precondition: method contains target abstract class parameter
        public <T> List<String> targetMethod(AbstractTargetClass targetParam) {
            // Variable call feature
            String varCall = targetParam.memberInner.innerField;
            List<String> resultList = new ArrayList<>();
            resultList.add(varCall);

            // Raw type feature
            ArrayList rawList = new ArrayList();
            rawList.add("raw_type_value");
            resultList.add((String) rawList.get(0));

            // Labeled statement feature
            labelLoop:
            for (int i = 0; i < 5; i++) {
                // ContinueStatement (public modifier, this.field + 1, inner class pos)
                public int continueVar = this.continueField + 1;
                if (i == continueVar) {
                    continue labelLoop; // Labeled continue statement
                }
                resultList.add(String.valueOf(i));
            }

            // Requires try-catch feature (mandatory try-catch for Class.forName)
            try {
                Class.forName("refactoring.test.AbstractTargetClass");
            } catch (ClassNotFoundException e) {
                resultList.add("try_catch_handler");
                e.printStackTrace();
            }

            return resultList;
        }
    }

    // Anonymous inner class feature (source class feature)
    public void sourceAnonymousInner() {
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                MemberInnerClass inner = new MemberInnerClass();
                inner.targetMethod(new ConcreteTargetClass());
            }
        };
        anonymousRunnable.run();
    }
}

// Target abstract normal class (abstract modifier, member inner class target_feature)
abstract class AbstractTargetClass {
    // Member inner class target_feature
    public class TargetMemberInnerClass {
        String innerField = "target_inner_field";
    }

    // Instance of member inner class for access
    public TargetMemberInnerClass memberInner = new TargetMemberInnerClass();

    // Abstract method (required for abstract class)
    public abstract void abstractMethod();
}

// Concrete implementation of abstract target class (for instantiation)
class ConcreteTargetClass extends AbstractTargetClass {
    @Override
    public void abstractMethod() {}
}