package com.refactoring.movemethod;

import java.io.IOException;

// Diff package class for WhileStatement pos: diff_package_others
package com.other;
public class DiffPackageHelper {
    // For WhileStatement implementation
    public static void executeWhileLogic(com.refactoring.movemethod.SourceClass sourceObj) throws IOException {
        sourceObj.whileLogic();
    }
}

package com.refactoring.movemethod;
import com.other.DiffPackageHelper;

// Source class: public, permits feature (sealed class), same package as target
public sealed class SourceClass permits SubClass1, SubClass2 {
    // Per_condition: source contains target class field
    private TargetClass<String> targetField;
    // Private field for access_outer_private feature
    private int outerPrivateField = 100;

    // Method to refactor: instance, TargetClass Type return, protected, in source class
    protected TargetClass<String> methodToRefactor() throws IOException { // requires_throws
        // Super constructor invocation (SourceClass extends Object implicitly, explicit super())
        super();
        
        // Variable call (target class field)
        if (targetField == null) {
            targetField = new TargetClass<>();
        }
        
        // Access outer private field (access_outer_private feature)
        int value = this.outerPrivateField;
        
        // Labeled statement
        label:
        for (int i = 0; i < 2; i++) { // enhanced for statement prep
            if (i == 1) break label;
            value += i;
        }
        
        // Enhanced for statement
        Integer[] numbers = {1, 2};
        for (int num : numbers) {
            value += num;
        }
        
        // WhileStatement feature: private modifier, this.field, 2 iterations, diff_package_others pos
        private void whileLogic() {
            int count = 2; // target_feature: 2
            while (count > 0) {
                this.outerPrivateField++; // this.field
                count--;
            }
        }
        DiffPackageHelper.executeWhileLogic(this); // pos: diff_package_others

        return targetField; // TargetClass Type return
    }
}

// Permits implementations for source class
final class SubClass1 extends SourceClass {}
final class SubClass2 extends SourceClass {}

// Target class: abstract, type parameter, implements, local inner class
abstract class TargetClass<T> implements Runnable {
    @Override
    public void run() {
        // Local inner class (target_feature)
        class LocalInnerClass {
            T value;
            void setValue(T val) {
                this.value = val;
            }
        }
        LocalInnerClass localObj = new LocalInnerClass();
        localObj.setValue((T) "targetValue");
    }
}