package refactoring.test;

import java.util.ArrayList;
import java.util.Collection;

// Source class with final modifier, same package as target class
final class SourceClass {
    // Field of target class to satisfy precondition
    private final TargetClass targetField = new TargetClass();

    // First member inner class
    class InnerClass1 {
        // Second member inner class (nested - source_inner_rec for method position)
        class InnerClass2 {
            // Target static method with protected access, void return type
            protected static void moveTargetMethod() {
                // Volatile variable with BooleanLiteral expression (numbers:1)
                volatile boolean flag = true;
                
                // Variable call feature
                String testVar = "test";
                System.out.println(testVar);
                
                // Super keyword usage (in inner class context)
                super.toString();
                
                // Expression statement with NullPointerException (no_new_exception)
                Object nullObj = null;
                nullObj.toString(); // Triggers NPE without explicit new
            }
        }
    }

    // Final subclass with call_method features
    final class SubClassCaller extends SourceClass {
        // Method with varargs, collection operations, obj.m1().m2().m3() chain
        public Object callMethodWithFeatures(Object... varargs) {
            Collection<String> collection = new ArrayList<>();
            collection.add("element"); // Collection operations position
            
            // obj.m1().m2().m3() feature chain
            return new Helper().m1().m2().m3();
        }

        // Helper class for method chain
        private class Helper {
            Helper m1() { return this; }
            Helper m2() { return this; }
            Object m3() { return new Object(); }
        }
    }
}

// Target class with private modifier, contains anonymous inner class
class TargetClass {
    private TargetClass() {
        // Anonymous inner class target feature
        Runnable anonymousRunnable = new Runnable() {
            @Override
            public void run() {
                // Empty implementation for anonymous inner class requirement
            }
        };
        anonymousRunnable.run();
    }
}