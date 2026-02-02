package refactoring.test;

import java.lang.reflect.Method;

// Source enum class (public modifier, same package as target, local + member inner class)
public enum SourceEnum {
    INSTANCE;

    // Member inner class (for source_inner_rec method position)
    public class MemberInnerClass {
        // Nested member inner class (source_inner_rec)
        public class NestedMemberInnerClass {
            // Target normal method (default access, base type return, source_inner_rec)
            // Precondition: method contains target enum parameter
            int targetMethod(TargetEnum targetParam) {
                // Variable call feature
                String varCall = targetParam.name();
                int varLength = varCall.length();

                // If statement feature
                if (varLength > 0) {
                    varLength *= 2;
                }

                // Super constructor invocation (in anonymous subclass context)
                Runnable anonymousSub = new Runnable() {
                    {
                        super(); // Super constructor invocation
                    }
                    @Override
                    public void run() {}
                };

                // Used by reflection feature
                try {
                    Method method = NestedMemberInnerClass.class.getDeclaredMethod("targetMethod", TargetEnum.class);
                    method.invoke(new NestedMemberInnerClass(), TargetEnum.VALUE);
                } catch (Exception e) {
                    // No new exception (reuse reflection exception, no explicit new)
                    throw new RuntimeException(e);
                }

                // Override_violation feature (attempt to override final method)
                class LocalInnerForOverride extends TargetEnum {
                    @Override
                    public final String name() { // Override violation (final method)
                        return super.name();
                    }
                }

                // Local inner class feature (within target method)
                class LocalInnerInMethod {
                    int processParam(TargetEnum param) {
                        return param.ordinal();
                    }
                }

                // No_new_exception (implicit NPE if targetParam is null, no new exception)
                return targetParam.ordinal() + varLength; // Base type return (int)
            }
        }
    }

    // Local inner class feature (within enum method)
    public void sourceLocalInner() {
        class LocalInnerInSource {
            void innerMethod() {
                MemberInnerClass inner = new MemberInnerClass();
                NestedMemberInnerClass nested = inner.new NestedMemberInnerClass();
                nested.targetMethod(TargetEnum.VALUE);
            }
        }
        new LocalInnerInSource().innerMethod();
    }

    // Call method (source type, default modifier, exception throwing pos, int return)
    int callMethod() {
        try {
            // Constructor target_feature
            MemberInnerClass innerObj = new MemberInnerClass();
            
            // outerInstance.new InnerClass().methodName() feature
            int result = innerObj.new NestedMemberInnerClass().targetMethod(TargetEnum.VALUE);
            
            // Exception throwing statements (pos requirement)
            if (result < 0) {
                throw new IllegalArgumentException("Invalid result");
            }
            return result;
        } catch (IllegalArgumentException e) {
            // Exception handling for throwing statement
            return -1;
        }
    }
}

// Target enum class (default modifier, extends + local inner class target_feature)
enum TargetEnum {
    VALUE;

    // Enum implicitly extends Enum, explicit super constructor invocation
    TargetEnum() {
        super(); // Super constructor invocation (Enum constructor)
        
        // Local inner class target_feature
        class LocalInnerInTarget {
            int getOrdinal() {
                return TargetEnum.this.ordinal();
            }
        }
        new LocalInnerInTarget().getOrdinal();
    }
}