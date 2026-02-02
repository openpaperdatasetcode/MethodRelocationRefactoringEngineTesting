package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnnotation {}
private class SourceClass<T> {{new Runnable() {};new Thread() {};}
@MyAnnotationprivate TargetClass<String> instanceMethod(TargetClass<Integer> param) {// Access target fieldInteger fieldVal = param.targetField;
variableCall();
// Depends on inner classTargetClass<String>.StaticNested nested = new TargetClass<>().new StaticNested();
// Requires try-catchtry {param.instanceMethod();} catch (Exception e) {// Handle exception}
@MyAnnotationTargetClass<String> result = new TargetClass<>();return result;}
private void variableCall() {}}
class TargetClass extends ParentClass implements Runnable {
U targetField;
static class StaticNested {}
void instanceMethod() throws Exception {}
@Overridepublic void run() {}}
class ParentClass {}