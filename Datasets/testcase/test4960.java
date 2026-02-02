package test;

class SourceClass {
private int outerPrivateField;
private MemberInner memberInner;

// Member inner class
class MemberInner {
void innerMethod() {
System.out.println("Member inner class method");
}
}

// Anonymous inner class
public SourceClass() {
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous inner class in SourceClass");
}
};
anon.run();
this.memberInner = new MemberInner();
}

// Overloading methods to be refactored
private void overloadedMethod(TargetClass.TargetInnerRec param) {
// Type declaration statement
TargetClass.TargetInnerRec localTarget;

// Enhanced for statement
for (String s : new String[]{"a", "b"}) {
// If statement
if (s.isEmpty()) {
// Return statement
return;
}
}

// Access outer private
outerPrivateField = 5;

// Variable call
param.process();

// Depends on inner class
memberInner.innerMethod();

// Protected WhileStatement with this.field and 2
int count = 0;
while (count < 2) {
param.value = count; // this.field reference
count++;
}
}

private void overloadedMethod(TargetClass.TargetInnerRec param, String extra) {
overloadedMethod(param);
System.out.println(extra);
}
}

class TargetClass {
// Static nested class (target_inner_rec)
static class TargetInnerRec {
int value;

void process() {
System.out.println("Processing TargetInnerRec: " + value);
}
}
}