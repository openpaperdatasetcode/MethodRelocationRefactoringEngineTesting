package com.refactoring.movemethod;

// Abstract base class for super constructor invocation (record can't extend directly, use wrapper)
abstract class RecordSuperClass<T> {
    protected T superValue;

    public RecordSuperClass(T value) {
        this.superValue = value; // super constructor invocation base
    }
}

// Source record class: private modifier, same package as target, type parameter + anonymous inner + local inner class
private record SourceRecord<T extends CharSequence>(T value) {
    // Per_condition: source contains target class field
    private final TargetRecord<String> targetField = new TargetRecordImpl<>("initial_target_1");

    // Wrapper for super constructor invocation (record can't extend, use inner class)
    private class RecordWrapper extends RecordSuperClass<T> {
        public RecordWrapper(T value) {
            super(value); // super constructor invocation
        }
    }

    /**
     * Method to refactor: normal, base type (int) return, protected access, in source
     * <p>Features: continue statement, super constructor invocation, switch case, variable call, no_new_exception</p>
     * @param innerParam Target inner recursive class instance
     * @param <T> Generic type parameter (CharSequence subtype)
     * @return Processed integer result (base type)
     */
    protected <T extends CharSequence> int methodToRefactor(TargetRecord<T>.TargetInnerRec innerParam) {
        // Per_condition: method contains target parameter
        if (innerParam == null) {
            return 0;
        }

        // Super constructor invocation (via wrapper)
        RecordWrapper wrapper = new RecordWrapper((T) "super_1");

        int result = 0;
        int count = 0;

        // Local inner class (source feature)
        class LocalInnerProcessor {
            int process(T input) {
                return input.length() + 1;
            }
        }

        while (count < 5) {
            // Continue statement
            if (count % 2 == 0) {
                count++;
                continue; // continue statement
            }

            // Variable call (target inner recursive class)
            T innerValue = innerParam.getInnerValue();
            
            // Switch case
            switch (innerValue.length()) {
                case 1:
                    result += 1;
                    break;
                case 2:
                    result += 2;
                    break;
                default:
                    result += 3;
                    break;
            }

            // Anonymous inner class (source feature)
            Runnable anonymous = new Runnable() {
                @Override
                public void run() {
                    innerParam.setInnerValue((T) (innerValue + "_anonymous_1"));
                }
            };
            anonymous.run();

            // Local inner class usage
            LocalInnerProcessor processor = new LocalInnerProcessor();
            result += processor.process(innerValue);

            // No_new_exception feature
            try {
                Integer.parseInt(innerValue.toString());
            } catch (NumberFormatException e) {
                // No throw new exception, handle only
                innerParam.setInnerValue((T) "safe_value_1");
                result += 1;
            }

            count++;
        }

        // Variable call for targetField (per_condition)
        result += targetField.getInnerRec().getInnerValue().length();

        return result; // Base type (int) return
    }
}

// Target abstract record class: abstract modifier, static nested class feature
abstract sealed class TargetRecord<T extends CharSequence> permits TargetRecordImpl<T> {
    // Static nested class (target_feature)
    public static class TargetStaticNested<U> {
        private U nestedValue;

        public TargetStaticNested(U value) {
            this.nestedValue = value;
        }

        public U getNestedValue() {
            return nestedValue;
        }
    }

    // Target_inner_rec (target inner recursive class)
    public abstract class TargetInnerRec {
        private T innerValue;

        public TargetInnerRec(T value) {
            this.innerValue = value;
        }

        // Variable call getters/setters
        public abstract T getInnerValue();
        public abstract void setInnerValue(T value);
    }

    // Abstract method for instantiation
    public abstract TargetInnerRec getInnerRec();
}

// Concrete implementation of abstract target record
final class TargetRecordImpl<T extends CharSequence> extends TargetRecord<T> {
    private final T value;
    private final TargetInnerRec innerRec;

    public TargetRecordImpl(T value) {
        this.value = value;
        this.innerRec = new TargetInnerRecImpl(value);
    }

    @Override
    public TargetInnerRec getInnerRec() {
        return innerRec;
    }

    // Concrete inner recursive class
    public class TargetInnerRecImpl extends TargetInnerRec {
        public TargetInnerRecImpl(T value) {
            super(value);
        }

        @Override
        public T getInnerValue() {
            return super.innerValue;
        }

        @Override
        public void setInnerValue(T value) {
            super.innerValue = value;
        }
    }
}