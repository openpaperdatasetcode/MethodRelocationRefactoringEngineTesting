package com.refactoring.movemethod;

import java.util.ArrayList;
import java.util.List;

final class SourceClass {
    // Static nested class (first occurrence)
    public static class StaticNestedOne {
        // Static nested class (second occurrence)
        public static class StaticNestedTwo {
            // Recursive inner class containing the target method
            public static class RecursiveInner {
                /**
                 * Javadoc for the varargs method to be moved
                 * @param targetParam parameter of target class type
                 * @param args variable arguments
                 * @return List of String
                 */
                protected List<String> methodToMove(TargetClass.MemberInner targetParam, String... args) {
                    // Variable call
                    String localVar = "test";
                    List<String> result = new ArrayList<>();
                    for (String arg : args) {
                        result.add(localVar + arg);
                    }

                    // Constructor invocation
                    StaticNestedOne nestedOne = new StaticNestedOne();
                    // Super constructor invocation
                    super();

                    // Static code block with final constructor feature
                    static {
                        FinalConstructorFeature finalConstructor = new FinalConstructorFeature(3);
                        // obj.m1().m2().m3() chain call with inner class reference
                        String chainResult = finalConstructor.getInnerClass().m1().m2().m3();
                    }

                    // No new exception (only return, no exception instantiation)
                    return result;
                }

                // Final constructor feature class with static code block position
                private static class FinalConstructorFeature {
                    private final int num;
                    private InnerFeature innerClass;

                    // Final modifier constructor (simulated via private + final field)
                    private FinalConstructorFeature(final int num) {
                        this.num = num;
                        this.innerClass = new InnerFeature();
                    }

                    public InnerFeature getInnerClass() {
                        return innerClass;
                    }

                    // Inner class for method chain
                    private class InnerFeature {
                        public InnerFeature m1() { return this; }
                        public InnerFeature m2() { return this; }
                        public String m3() { return String.valueOf(num); }
                    }
                }
            }
        }
    }
}

class TargetClass {
    // Member inner class (target_inner_rec)
    public class MemberInner {
        private String value;

        public MemberInner(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}