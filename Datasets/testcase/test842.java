package refactoring.test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;

// Different package for diff_package_others pos
package refactoring.others;

import java.util.List;

public class OtherPackageClass {
    public static int staticField = 2; // ClassName.field + 2 for VariableDeclarationStatement
}

// Back to same package
package refactoring.test;

import refactoring.others.OtherPackageClass;
import java.util.List;
import java.util.ArrayList;

// Sealed interface with permits for source_class feature
sealed interface GenericSealedInterface<T> permits SourceClass<T> {}

// Target generic class: protected modifier, anonymous inner class (target_feature)
protected class TargetClass<T> {
    public T data;
    
    // Target inner class (target_inner - target class of method)
    public class TargetInner {
        private String innerField = "innerData";
        
        public String getInnerField() { return innerField; }
        public TargetInner chainM1() { return this; }
        public TargetInner chainM2() { return this; }
        public TargetInner chainM3() { return this; }
    }

    // Anonymous inner class (target_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Target anonymous inner class");
        }
    };

    public TargetClass(T data) {
        this.data = data;
    }
}

// Subclass of TargetClass for method_feature: sub_class
class TargetSubClass<T> extends TargetClass<T> {
    public TargetSubClass(T data) {
        super(data);
    }

    public List<String> processChain(TargetInner inner) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) { // method_feature: 3, pos=for
            list.add(inner.chainM1().chainM2().chainM3().getInnerField()); // obj.m1().m2().m3()
        }
        return list;
    }
}

// Source generic class: public modifier, permits, static nested + anonymous inner class (source_feature)
public final class SourceClass<T> implements GenericSealedInterface<T> {
    // Static nested class (source_feature)
    public static class SourceStaticNested<T> {
        public static <T> TargetClass<T> createTarget(T data) {
            return new TargetClass<>(data);
        }
    }

    // Anonymous inner class (source_feature)
    private Runnable anonymousInner = new Runnable() {
        @Override
        public void run() {
            System.out.println("Source anonymous inner class");
        }
    };

    // Inner recursive class (source_inner_rec - method_position)
    public class SourceInnerRecursive {
        // VariableDeclarationStatement feature: private modifier, ClassName.field, 2, pos=diff_package_others
        private void variableDeclarationFeature() {
            int localVar = OtherPackageClass.staticField; // diff_package_others pos, ClassName.field + 2
        }

        // Normal method feature: public modifier, 3, sub_class, normal, obj.m1().m2().m3(), pos=for, return_type=List<String>
        public List<String> normalHelperMethod(TargetSubClass<T> subClass) {
            TargetClass<TargetInner> target = new TargetClass<>(subClass.new TargetInner());
            return subClass.processChain(target.data);
        }

        // Overriding method: final access, TargetInner return type, target parameter (per_condition)
        @Override
        public final TargetClass<T>.TargetInner refactorMethod(TargetClass<T> targetParam) throws IOException { // requires_throws
            // VariableDeclarationStatement execution
            variableDeclarationFeature();

            // Super keywords feature
            super.toString();

            // Variable call feature
            T varCall = targetParam.data;

            // Switch statement feature
            int switchVal = varCall != null ? 2 : 3;
            switch (switchVal) {
                case 2:
                    targetParam.anonymousInner.run();
                    break;
                case 3:
                    this.anonymousInner.run();
                    break;
                default:
                    break;
            }

            // Call normal helper method (sub_class)
            TargetSubClass<T> subClass = new TargetSubClass<>(varCall);
            List<String> helperResult = normalHelperMethod(subClass);

            // Requires throws: call method that throws IOException
            if (helperResult.size() != 3) {
                throw new IOException("Invalid list size");
            }

            // Return TargetInner (TargetClas Type)
            return targetParam.new TargetInner();
        }
    }
}