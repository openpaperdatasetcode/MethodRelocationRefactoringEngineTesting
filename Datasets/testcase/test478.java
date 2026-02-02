package com.refactoring.movemethod;

// Different package helper class for BreakStatement pos (diff_package_others)
package com.other;
public class BreakHelper {
    public int field = 2; // target_feature: 2, obj.field
    private void breakLogic() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                break; // BreakStatement (private modifier)
            }
        }
    }
}

// Back to main package
package com.refactoring.movemethod;
import com.other.BreakHelper;
import java.util.function.Consumer;

// Source record class (non-sealed, same package as target, local/anonymous inner class)
non-sealed public record SourceClass(String privateField, TargetClass targetField) {
    // Overriding method (final, void return, source position)
    @Override
    public final void toString() { // Overriding type (Object's toString)
        // Uses outer this feature
        SourceClass outerThis = SourceClass.this;
        
        // Access outer private feature (privateField is record's private component)
        String outerPrivate = outerThis.privateField;
        
        // Variable call feature
        BreakHelper breakObj = new BreakHelper();
        int varCall = breakObj.field; // target_feature: obj.field
        
        // Expression statement feature
        varCall += outerPrivate.length();
        
        // Super constructor invocation (via anonymous inner class)
        Consumer<String> anonInner = new Consumer<String>() {
            @Override
            public void accept(String s) {
                super.getClass(); // Super constructor invocation equivalent
                System.out.println(s);
            }
        };
        
        // Local inner class (source class feature)
        class LocalInner {
            void processTarget() {
                // No new exception feature (no throw new Exception)
                if (outerThis.targetField == null) {
                    return;
                }
                // BreakStatement usage (diff_package_others pos)
                for (int i = 0; i < varCall; i++) {
                    if (i == breakObj.field) {
                        break; // private modifier BreakStatement
                    }
                }
            }
        }
        
        // Call method (others_class, strictfp, new ClassName().method() in constructor param)
        OtherClass other = new OtherClass(new CallHelper().getText(outerPrivate));
        anonInner.accept(other.strictfpMethod());
        
        // Local inner class usage
        new LocalInner().processTarget();
    }

    // Anonymous inner class (source class feature)
    private Consumer<TargetClass> anonConsumer = new Consumer<TargetClass>() {
        @Override
        public void accept(TargetClass t) {
            System.out.println(t.value());
        }
    };
}

// Target record class (public, local inner class feature)
public record TargetClass(String id, int value) {
    // Local inner class (target_feature)
    public String process() {
        class LocalInnerTarget {
            String getLocal() {
                return TargetClass.this.id();
            }
        }
        return new LocalInnerTarget().getLocal();
    }
}

// Others_class for call_method
class OtherClass {
    private final String param;

    // Call method pos: constructor parameter list
    public OtherClass(String param) {
        this.param = param;
    }

    // strictfp modifier, return String, normal target_feature
    strictfp String strictfpMethod() {
        return this.param.toUpperCase();
    }
}

// CallHelper for new ClassName().method() feature
class CallHelper {
    String getText(String input) {
        return input + "_processed";
    }
}