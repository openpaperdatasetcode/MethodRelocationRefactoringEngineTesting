package test;

public class SourceClass<T> {
private TargetClass target;
private int outerField;

// Member inner class
public class SourceInner {
// Method to be refactored
public final int instanceMethod() {
int result = 0;

// Uses outer this
SourceClass.this.outerField = 5;

// For statement
for (int i = 0; i < 3; i++) {
// Continue statement
if (i == 1) continue;

// Try statement
try {
// Variable call
result += target.targetField;

// Raw type
java.util.List rawList = new java.util.ArrayList();
rawList.add(target);

// Assignment with final modifier and number 3
final int num = 3;
result *= num;
} catch (NullPointerException e) {
// No new exception
}
}

// Protected TryStatement with ClassName.field and 1
try {
TargetClass.memberInner.staticField = 1;
} finally {
// Empty finally block
}

// Anonymous inner class
Runnable runnable = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous class in source inner");
}
};
runnable.run();

return result;
}
}
}

class TargetClass {
int targetField;

// Member inner class
class MemberInner {
static int staticField;
}
}