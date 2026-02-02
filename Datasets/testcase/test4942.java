package test;

// Source: protected enum class with member inner & anonymous inner class
protected enum SourceEnum {
SOURCE1, SOURCE2;

// Member inner class
public class SourceMemberInner {
// Instance method for method_feature
public TargetEnum processTarget(TargetEnum target, int count) {
target.setCount(count);
return target;
}

// Overloaded method (overload_exist)
public TargetEnum processTarget(TargetEnum target) {
return processTarget(target, 3); // "3" in method_feature
}
}

// Final recursive method to be refactored
public final TargetEnum recursiveMethod(TargetEnum target, int depth) {
// Variable call
if (target == null || depth <= 0) {
return TargetEnum.DEFAULT; // Base case: return TargetClass Type
}

// Labeled statement + break statement
loopLabel:
for (int i = 0; i < 5; i++) {
switch (i) {
case 2:
break loopLabel; // Break statement
case 3:
target = new SourceMemberInner().processTarget(target); // Overload call
break;
default:
target = new SourceMemberInner().processTarget(target, i); // Overload call
}
}

// Ternary operator with instance method (pos: ternary operators)
TargetEnum processed = (depth % 2 == 0)
? new SourceMemberInner().processTarget(target, 3) // "3" + target + instance method
: super.toString().isEmpty() ? target : TargetEnum.TARGET1; // super.methodName(arguments)

// Anonymous inner class
Runnable anonTask = new Runnable() {
@Override
public void run() {
System.out.println("Processed target: " + processed.name());
}
};
anonTask.run();

// Recursive invocation
return recursiveMethod(processed, depth - 1);
}
}

// Target: public enum class with implements & anonymous inner class
public enum TargetEnum implements java.io.Serializable {
TARGET1, TARGET2, DEFAULT;

private int count;

// Anonymous inner class (target_feature)
private final Runnable targetAnon = new Runnable() {
@Override
public void run() {
System.out.println("Target enum anonymous class: " + name());
}
};

public void executeAnon() {
targetAnon.run();
}

public int getCount() {
return count;
}

public void setCount(int count) {
this.count = count;
}
}