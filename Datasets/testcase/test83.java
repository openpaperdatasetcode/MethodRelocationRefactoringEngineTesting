package com.refactoring.movemethod;

import diffpackage.DiffPackageAssertHelper;
import java.lang.reflect.Method;

// Different package class for AssertStatement pos=diff_package_others
package diffpackage;
public class DiffPackageAssertHelper {
    public static int assertValue = 1;
}

package com.refactoring.movemethod;

// Source class: record class, protected modifier, same package, local inner class, static nested class
protected record SourceRecord(String sourceField, TargetRecord targetField) { // per_condition: contains target field
    // Static nested class (source feature)
    public static class StaticNestedSource {
        static final int STATIC_ASSERT_VAL = 1; // target_feature "1" for AssertStatement
    }

    // Member inner class (method_position: source_inner)
    public class InnerSourceClass {
        // Method: varargs, return Object, protected access, source_inner position
        protected Object moveableVarargsMethod(String... args) {
            // Variable call feature
            String localVar = sourceField;
            localVar = targetField().targetData();
            // Access this.field of outer record
            String outerThisField = SourceRecord.this.sourceField;

            // AssertStatement: public modifier, this.field, 1, pos=diff_package_others
            public void assertLogic() {
                // pos=diff_package_others (uses diffpackage class)
                assert diffpackage.DiffPackageAssertHelper.assertValue == 1 : "Assert value not 1"; // target_feature "1"
                assert SourceRecord.this.sourceField != null : "Source field is null"; // target_feature "this.field"
            }
            assertLogic();

            // Type declaration statement feature
            class LocalTypeDeclaration {
                String typeField;
                void processArgs(String... args) {
                    typeField = String.join(",", args);
                }
            }
            LocalTypeDeclaration localType = new LocalTypeDeclaration();
            localType.processArgs(args);

            // Used_by_reflection feature
            try {
                Method method = this.getClass().getDeclaredMethod("moveableVarargsMethod", String[].class);
                method.setAccessible(true);
                method.invoke(this, (Object) args);
            } catch (Exception e) {
                // no_new_exception feature (no custom exceptions instantiated)
                return localVar;
            }

            // Local inner class (source feature)
            class LocalInnerSource {
                void updateVar() {
                    localVar += localType.typeField;
                }
            }
            new LocalInnerSource().updateVar();

            // no_new_exception feature (no custom exceptions thrown)
            return targetField();
        }
    }
}

// Target class: record class, default modifier, same package, empty target_feature
record TargetRecord(String targetData) {}