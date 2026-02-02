package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AnnoWithMethodRef {Class<?> value();}
protected class SourceClass extends ParentClass {private String instanceField = "sourceField";static class StaticNested {}
class SourceInner {private List<String> methodToMove(TargetClass.TargetInner target) {class LocalInner {}LocalInner local = new LocalInner();
// ConstructorInvocation (transient) with target_featureTransientClass transientObj = new TransientClass();transientObj.superField = 1;
// Access instance fieldString fieldVal = instanceField;// Variable call (source contains target's field)int targetField = target.value;
List<String> result = new ArrayList<>();// Enhanced for statementfor (String s : List.of("a", "b")) {result.add(s + targetField);}
// Lambda: () -> System.out.println(this.value)Runnable lambda = () -> System.out.println(target.value);lambda.run();
// Method feature in annotation attribute@AnnoWithMethodRef(ParentClass.class)Object annoObj = ParentClass::parentMethod;
return result;}}}
class ParentClass {public int parentMethod(int num, String arg) {return num * 2; // method_feature with number 2}}
class TransientClass extends SuperClass {transient TransientClass() {} // transient constructor}
class SuperClass {int superField;}
class TargetClass {class TargetInner {int value = 1; // Target field used in source}
// Target feature: anonymous inner classRunnable anonRunnable = new Runnable() {@Overridepublic void run() {}};}