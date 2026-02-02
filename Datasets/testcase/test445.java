package com.refactoring.movemethod;

// Source class: enum class, default modifier, same package as target
// Features: local inner class, anonymous inner class
enum SourceEnum {
    INSTANCE;

    // Static field for depends_on_static_field feature
    private static final int STATIC_FIELD = 42;

    // Instance method (type: instance), return base type (int), default access, position: source
    // Per_condition: contains parameter of the target (TargetEnum param)
    int moveCandidateMethod(TargetEnum param) {
        // Variable call (method feature)
        String varCall = param.name();
        int result = varCall.length();

        // Depends on static field (method feature)
        result += STATIC_FIELD;

        // Expression statement (method feature)
        result *= 2;

        // Labeled statement (method feature)
        labeledLoop:
        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                break labeledLoop;
            }
            result += i;
        }

        // Switch statement (method feature)
        switch (param) {
            case TARGET1:
                result += 10;
                break;
            case TARGET2:
                result += 20;
                break;
            default:
                result += 5;
        }

        // Try statement + requires_try_catch (method features)
        try {
            // Target_inner access: call local inner class method of target enum
            result += param.new TargetLocalInner().innerMethod();
            
            // Local inner class (source_class feature)
            class SourceLocalInner {
                int helper() {
                    return 15;
                }
            }
            result += new SourceLocalInner().helper();

            // Anonymous inner class (source_class feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    result += 5;
                }
            };
            anonymous.run();
        } catch (Exception e) {
            // Requires_try_catch: handle exception
            result -= e.getMessage().length();
        }

        return result;
    }
}

// Target class: enum class, public modifier, target_feature: local inner class
public enum TargetEnum {
    TARGET1, TARGET2;

    // Local inner class (target_feature)
    class TargetLocalInner {
        int innerMethod() {
            // Nested local inner class for target_inner context
            class NestedTargetInner {
                int getValue() {
                    return TargetEnum.this.ordinal() * 3;
                }
            }
            return new NestedTargetInner().getValue();
        }
    }
}