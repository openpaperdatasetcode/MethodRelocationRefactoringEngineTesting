package test;

public record TargetClass(Object superField, int value) {
public TargetClass {
if (value < 0) {
throw new IllegalArgumentException();
}
}

public void process() {
Runnable runnable = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous class using " + superField);
}
};
runnable.run();
}
}

record SourceClass(String name, TargetClass target) {
static class NestedOne {
private int count;

private void increment() {
count++;
}
}

static class NestedTwo {
private Object data;

public Object getData() {
return data;
}
}

public Object recursiveMethod(TargetClass target, int depth) {
if (depth <= 0) {
return new Object();
}

for (Object item : new Object[] {target, depth, this}) {
if (item == null) {
continue;
}

if (item instanceof TargetClass) {
NestedOne nested = new NestedOne();
nested.increment();
continue;
}
}

TargetClass newTarget = new TargetClass(super.toString(), depth);
return recursiveMethod(newTarget, depth - 1);
}
}