package refactoring.test;

import java.util.ArrayList;
import java.util.List;

// Base enum for target class extends feature
enum BaseTargetEnum {}

/**
 * Javadoc for TargetEnum (target_feature: javadoc)
 * Sealed enum target for move method refactoring
 */
// Target class: enum, private modifier, same package, target_feature: javadoc, extends, anonymous inner class
private enum TargetEnum extends BaseTargetEnum {
    VALUE_1, VALUE_2;

    // Anonymous inner class (target_feature)
    Runnable anonymousRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target enum anonymous inner class");
        }
    };

    public String getTargetData() {
        return this.name() + "-data";
    }
}

// Source class: enum, sealed modifier, same package as target, features: two member inner classes
sealed enum SourceEnum permits SourceEnum.FirstMemberInner, SourceEnum.SecondMemberInner {
    INSTANCE;

    // First member inner class (source_class feature)
    final class FirstMemberInner {
        List<String> process(TargetEnum targetParam) {
            List<String> list = new ArrayList<>();
            list.add(targetParam.getTargetData());
            return list;
        }
    }

    // Second member inner class (source_class feature, source_inner_rec - method position host)
    class SecondMemberInner {
        // Base method for overriding
        List<String> moveMethod(TargetEnum targetParam) {
            return new ArrayList<>();
        }

        // Overload method (overload_exist feature)
        List<String> moveMethod(TargetEnum targetParam, String extra) {
            List<String> list = new ArrayList<>();
            list.add(targetParam.getTargetData() + "-" + extra);
            return list;
        }
    }

    // Subclass for overriding feature (method type: overriding)
    class SourceInnerRecClass extends SecondMemberInner {
        // Method to be refactored: overriding, return List<String>, private access, position source_inner_rec
        // Per condition: method contains target class parameter
        @Override
        private List<String> moveMethod(TargetEnum targetParam) {
            // Variable call feature
            int counter = 0;
            // Constructor invocation feature
            FirstMemberInner firstInner = new FirstMemberInner();
            
            // Expression statement feature
            String exprStmt = "Processing target: " + targetParam.name();
            System.out.println(exprStmt);

            // Synchronized statement feature
            synchronized (this) {
                counter++;
                List<String> result = firstInner.process(targetParam);
                result.add(exprStmt + "-" + counter);
                // No new exception thrown (no_new_exception feature)
                return result;
            }
        }
    }
}