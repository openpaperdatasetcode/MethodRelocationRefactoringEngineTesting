package test;
import java.util.Objects;
class SourceClass {public static class StaticNested {int nestedField;StaticNested(int value) {this.nestedField = value;}}
@Deprecatedpublic SourceClass(TargetClass target) {super();Objects.requireNonNull(target, "Target cannot be null");if (target == null) throw new NullPointerException();
TargetClass.InnerRecursive innerRec = target.new InnerRecursive();StaticNested staticNested = new StaticNested(innerRec.recField);
int count = 0;do {innerRec.recField++;count++;} while (count < 3);
class LocalInner {void printData() {System.out.println("Target field: " + target.targetField);System.out.println("Recursive field: " + innerRec.recField);}}new LocalInner().printData();}}
public class TargetClass {int targetField;
public TargetClass() {this.targetField = 10;}
public class InnerRecursive {int recField = 0;
public class DeepInner {void useFields() {System.out.println("Outer: " + targetField + ", Recursive: " + recField);}}}}