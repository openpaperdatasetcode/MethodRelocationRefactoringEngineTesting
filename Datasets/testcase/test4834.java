package test.refactoring;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

interface SuperType {
Object superMethod(TargetClass target);
}

public record SourceClass(String sourceField) implements SuperType {
public static final int STATIC_FIELD = 100;

public class SourceInner {
private TargetClass targetField;

protected Object instanceMethod() {
class LocalInnerClass {
Object processTarget(TargetClass target) {
return target.targetMethod(sourceField + "-" + STATIC_FIELD);
}
}

LocalInnerClass localHandler = new LocalInnerClass();
this.targetField = new TargetClass("target-data-" + STATIC_FIELD);

for (int i = 0; i < 5; i++) {
Object result = localHandler.processTarget(targetField);
variableCall(result);

Object superResult = SuperType.super.superMethod(targetField);
if (i == 2) {
break;
}
}

Collection rawCollection = new ArrayList();
rawCollection.add(targetField);
return rawCollection.iterator().next();
}

private void variableCall(Object obj) {
System.out.println("Variable call result: " + obj);
}

@Override
public Object superMethod(TargetClass target) {
return "SuperType Method: " + target.targetField();
}

strictfp class CallMethodInner implements SuperType {
@Override
public Object superMethod(TargetClass target) {
return target.targetMethod("Overridden");
}

public List<String> processCollection(Collection<TargetClass> targets) {
List<String> results = new ArrayList<>();
targets.forEach(target -> results.add((String) SourceInner.this.superMethod(target)));
targets.forEach(CallMethodInner::superMethod);
return results;
}
}
}

Runnable anonymousInner = new Runnable() {
@Override
public void run() {
SourceInner inner = new SourceInner();
System.out.println("Anonymous Inner: " + inner.instanceMethod());
}
};

@Override
public Object superMethod(TargetClass target) {
return "SourceClass Super: " + target.targetField();
}
}

protected record TargetClass(String targetField) {
public Object targetMethod(String param) {
return param + " | TargetField: " + targetField;
}
}