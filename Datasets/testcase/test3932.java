import java.util.ArrayList;import java.util.List;
sealed class BasePermit permits FinalSourceClass {}
final class FinalSourceClass extends BasePermit {private TargetClass targetField = new TargetClass();
public class FirstInnerClass {@MyCustomAnnotationprivate List<String> overloadedMethod(TargetClass target) throws InvalidTargetException {List<String> result = new ArrayList<>();new SecondInnerClass().validateTarget(target);
TargetClass processed = overloadedMethod(target, "additionalParam");result.add(processed.staticNestedClass.sharedField);variableCall(processed);
return result;}
public TargetClass overloadedMethod(TargetClass target, String extra) {TargetClass newTarget = new TargetClass();newTarget.staticNestedClass.sharedField = extra;return newTarget;}
private void variableCall(TargetClass target) {target.processData();}}
public class SecondInnerClass {private void validateTarget(TargetClass target) throws InvalidTargetException {if (target == null || target.staticNestedClass.sharedField == null) {throw new InvalidTargetException("Target invalid: " + TargetClass.staticNestedClass.sharedField);}}}}
protected class TargetClass implements DataProcessor {public StaticNestedClass staticNestedClass = new StaticNestedClass();
public static class StaticNestedClass {public String sharedField = "default";}
@Overridepublic void processData() {StaticNestedClass.callStaticMethod(this);}
public static class StaticNestedClass {public static void callStaticMethod(TargetClass target) {super.processData();}}}
interface DataProcessor {void processData();}
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)@interface MyCustomAnnotation {}
class InvalidTargetException extends Exception {public InvalidTargetException(String message) {super(message);}}
class ObjectInitializer {private FinalSourceClass.FirstInnerClass inner = new FinalSourceClass().new FirstInnerClass();private TargetClass initTarget = inner.overloadedMethod(new TargetClass(), "initValue");}