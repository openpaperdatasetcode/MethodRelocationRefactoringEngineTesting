package test;
import java.util.List;import java.util.ArrayList;
public class TargetClass {int field = 10;
{new Object() {}; // Anonymous inner class}
class TargetInner {class TargetInnerRec {String value;}}}
abstract class SourceClass {class SourceInner {class SourceInnerRec {static void staticMethod(TargetClass.TargetInner.TargetInnerRec param) {// ReturnStatement with target features (diff_package_others)OtherPackageClass.check(() -> {if (param == null) {return;}int val = new SubTarget().superField + 1;});
// Normal method in object initializationList<String> list = new SourceInnerRec() {{addAll(SourceInnerRec.normalMethod(param));}};
// Super keywordsclass Sub extends SourceInner {Sub() {super();}}
// Variable callString var = param.value;TargetClass target = new TargetClass();int targetVar = target.field;}
public static List<String> normalMethod(TargetClass.TargetInner.TargetInnerRec rec) {return new ArrayList<>(List.of(rec.value));}}}
void localInnerMethod() {class LocalInner {} // Local inner class}}
class SubTarget extends TargetClass {int superField = super.field;}
// Class in different packagepackage other;
import test.TargetClass;import java.util.function.Supplier;
public class OtherPackageClass {public static void check(Supplier<Void> supplier) {supplier.get();}}