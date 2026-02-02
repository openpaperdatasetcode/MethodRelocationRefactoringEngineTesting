package test;
import other.OtherClass;import java.util.ArrayList;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {String value() default OtherClass.FINAL_INSTANCE.getAnnotationValue("default");}
public record TargetClass(String data) {public static class TargetStaticNested {private int nestedField;
public int getNestedField() {return nestedField;}
public void setNestedField(int nestedField) {this.nestedField = nestedField;}}
class TargetInner {abstract void abstractMethod(TargetClass tc, int a, int b);
class TargetRecursiveInner {void process(TargetClass target) {variableCall(target);}}}}
record SourceClass(int id) {class SourceInner {class SourceRecursiveInner {@MyAnnotationprotected SourceRecursiveInner(TargetClass target) {TargetClass.TargetInner targetInner = target.new TargetInner();TargetClass.TargetInner.TargetRecursiveInner targetRecInner = targetInner.new TargetRecursiveInner();TargetClass.TargetStaticNested staticNested = new TargetClass.TargetStaticNested();ArrayList rawList = new ArrayList();rawList.add(target.data());
try {for (int i = 0; i < 5; i++) {if (i == 2) {continue;}staticNested.setNestedField(staticNested.getNestedField() + i);rawList.add(staticNested.getNestedField());}
targetInner.abstractMethod(target, 1, 2) {@Overridevoid abstractMethod(TargetClass tc, int a, int b) {tc.data();this.methodFromOther(new OtherClass(), a, b);}};} catch (Exception e) {rawList.clear();}
targetRecInner.process(target);}
private void variableCall(TargetClass target) {System.out.println(target.data());}
private void methodFromOther(OtherClass oc, int a, int b) {oc.getAnnotationValue(a + "_" + b);}}}
void methodWithLocalInner() {class LocalInner {void useTarget(TargetClass target) {System.out.println(target.data());}}}}
package other;
public final class OtherClass {public static final OtherClass FINAL_INSTANCE = new OtherClass();
public String getAnnotationValue(String param) {return "annot_val_" + param;}}
