// Wrapper class to enable private modifier for generic source class (top-level can't be private)
class PackageWrapper {
    // Source class: generic, private modifier, same package, two member inner classes
    private class SourceClass<T extends CharSequence> {
        // First member inner class (source_class feature)
        public class FirstMemberInnerClass<U extends Number> {
            private U innerValue = (U) Integer.valueOf(2); // Matches "2" in accessor method_feature

            // Accessor method chain (obj.m1().m2().m3())
            public SecondMemberInnerClass<T> m1() {
                return new SecondMemberInnerClass<>();
            }

            // Accessor method (base type return)
            public U getInnerValue() {
                return innerValue;
            }
        }

        // Second member inner class (source_class feature)
        public class SecondMemberInnerClass<V extends CharSequence> {
            // Accessor method chain link
            public AccessorHelper m2() {
                return new AccessorHelper();
            }

            // Instance method to refactor: void return, public access, source_inner position
            public void instanceMethod(TargetClass<T> targetParam) { // per_condition: contains target parameter
                // Variable call feature
                T localVar = (T) "sourceVar";
                int accessorNum = 2; // Matches "2" in accessor method_feature

                // Constructor invocation feature
                FirstMemberInnerClass<Integer> firstInner = new FirstMemberInnerClass<>();
                SecondMemberInnerClass<T> secondInner = new SecondMemberInnerClass<>();

                // Accessor feature: public modifier, 2, inner_class, accessor, obj.m1().m2().m3(), ternary pos, base type return
                int accessorResult = (localVar.length() > 0) ? 
                    firstInner.m1().m2().m3(accessorNum) : // obj.m1().m2().m3() (ternary operators pos)
                    firstInner.getInnerValue(); // base type return (int)

                // Variable call + accessor result
                localVar = (T) (localVar + "_accessor_" + accessorResult);

                // Constructor invocation + target parameter usage
                targetParam.getInnerClass().processValue(localVar);

                // No new exception feature (no 'new Exception()' statements)
                System.out.println("Result: " + localVar + ", Target: " + targetParam.getValue());
            }
        }

        // Accessor helper class for method chain (obj.m1().m2().m3())
        public class AccessorHelper {
            // Final link in accessor method chain
            public int m3(int num) {
                return num * 2; // Matches "2" in accessor method_feature
            }
        }
    }

    // Target class: generic, strictfp modifier, member inner class target_feature
    strictfp class TargetClass<T extends CharSequence> {
        private T value;

        public TargetClass(T value) {
            this.value = value;
        }

        // Member inner class (target_feature)
        public class TargetInnerClass<U extends CharSequence> {
            public void processValue(U input) {
                System.out.println("Target inner processing: " + input);
            }
        }

        // Target inner recursive structure for method relocation
        public static class target_inner_rec {
            // Placeholder for moved instance method
            public <T extends CharSequence> void instanceMethod(TargetClass<T> targetParam) {
                PackageWrapper wrapper = new PackageWrapper();
                PackageWrapper.SourceClass<T> source = wrapper.new SourceClass<>();
                PackageWrapper.SourceClass<T>.SecondMemberInnerClass<T> inner = source.new SecondMemberInnerClass<>();
                inner.instanceMethod(targetParam);
            }
        }

        // Accessor method for target parameter
        public TargetInnerClass<T> getInnerClass() {
            return new TargetInnerClass<>();
        }

        public T getValue() {
            return value;
        }
    }
}