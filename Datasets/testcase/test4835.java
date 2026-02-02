package test.refactoring;
import java.util.function.Predicate;

public record SourceClass(String sourceField) {
static class SourceStaticNested<T extends Number> {
T nestedField;

public SourceStaticNested(T nestedField) {
this.nestedField = nestedField;
}

public T getNestedField() {
return nestedField;
}
}

public class SourceInner {
private int innerCount = 0;

@FunctionalInterface
private interface RecursionHelper {
Object process(TargetClass.TargetInner target);
}

@SuppressWarnings("unchecked")
private Object recursiveMethod(int depth, TargetClass.TargetInner targetParam) {
class LocalInnerClass {
Object handleTarget(TargetClass.TargetInner target) {
return target.targetMethod(innerCount++);
}
}

if (depth < 0) {
throw new IllegalArgumentException("Depth cannot be negative");
}
if (targetParam == null) {
throw new NullPointerException("Target parameter cannot be null");
}

LocalInnerClass localHandler = new LocalInnerClass();
Object currentResult = localHandler.handleTarget(targetParam);

try {
Object varargsResult = varargsMethod(
depth == 0 ? "Base case" : "Recursive step",
targetParam,
new SourceStaticNested<>(10).getNestedField()
);

RecursionHelper helper = depth > 0
? this::recursiveMethod
: (t) -> currentResult;

return depth == 0
? varargsResult
: helper.process(targetParam);
} catch (ClassCastException e) {
e.printStackTrace();
return currentResult;
}
}

default Object varargsMethod(Object... args) {
if (args.length == 0) {
return super.toString();
}
return args[0] + "-" + args[1].toString();
}

public Object startRecursion(TargetClass.TargetInner target) {
return recursiveMethod(3, target);
}
}
}

public record TargetClass(String targetField) {
public TargetClass {
Runnable anonymousInner = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous inner class in TargetClass: " + targetField);
}
};
anonymousInner.run();
}

public class TargetInner {
private String innerData;

public TargetInner(String innerData) {
this.innerData = innerData;
}

public Object targetMethod(int count) {
return "Target processed: " + innerData + " (count: " + count + ")";
}

@Override
public String toString() {
return innerData;
}
}
}