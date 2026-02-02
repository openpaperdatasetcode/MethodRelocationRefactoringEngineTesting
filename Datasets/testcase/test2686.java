package test;
import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
interface TargetInterface {}
abstract class SourceClass {protected String outerProtectedField = "sourceProtectedVal";
class SourceInner {default <T> void callMethod(TargetClass target) {// Generic method reference in property assignmentRunnable ref = target::genericMethod;target.property = ref;}}
public void createLocalInner() {class LocalInnerSource {}}
strictfp public List<String> methodToMove(TargetClass target) {super();
// Static SynchronizedStatement with super.field (count 1)synchronized (TargetClass.class) {String superFieldVal = target.superField;}
// Constructor invocationTargetClass newTarget = new TargetClass();
// Switch caseswitch (target.superField.length()) {case 5 -> System.out.println("Length 5");default -> System.out.println("Default");}
// Variable call + contains target parameter (per_condition)target.toString();target.genericMethod();
// Access outer protected fieldString protectedVal = this.outerProtectedField;
// Used by reflectiontry {Method method = getClass().getMethod("methodToMove", TargetClass.class);method.invoke(this, target);} catch (Exception e) {}
List<String> result = new ArrayList<>();result.add(superFieldVal);result.add(protectedVal);return result;}}
private class TargetClass implements TargetInterface {public String superField = "targetSuper"; // super.field referencepublic Runnable property;
public <T> void genericMethod() {}}