package test;
import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
private class SourceClass {static class StaticNested {}
class MemberInner {/**
Accessor method to retrieve target data*/@MyAnnotationprivate List<String> getTargetData(TargetClass target) {super();List<String> result = new ArrayList<>();Supplier<Object> supplier = () -> TargetClass.overloadedMethod(1);
do {result.add(String.valueOf(target.instanceField));target.action();} while (result.size() < 3);
try {Method method = TargetClass.class.getMethod("overloadedMethod", int.class);} catch (NoSuchMethodException e) {throw new RuntimeException(e);}
return result;}
// Overloading method (violates override if TargetClass has same signature)private List<String> getTargetData(String param) {return new ArrayList<>();}}}
private class TargetClass implements Runnable {int instanceField;
static Object overloadedMethod(int param) {return param;}
void action() {new Runnable() {@Overridepublic void run() {}};}
@Overridepublic void run() {}}
@interface MyAnnotation {}