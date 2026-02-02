package test;

class SourceClass {
// Member inner class
class MemberInner {
private String data;

MemberInner(String data) {
this.data = data;
}
}

// Static nested class
static class StaticNested {
private int value;

StaticNested(int value) {
this.value = value;
}
}

// Constructor to be refactored
public SourceClass(TargetClass.TargetInnerRec innerRec) {
// Variable call
innerRec.process();

// Varargs feature in while loop
int count = 0;
while (count < 1) {
Object result = SourceClass.handleVarargs(innerRec, "arg1", "arg2");
count++;
}
}

// Public varargs method
public static Object handleVarargs(TargetClass.TargetInnerRec inner, String... args) {
return new Object();
}
}

class TargetClass {
// Target inner class (target_inner_rec)
class TargetInnerRec {
void process() {
// Anonymous inner class
Runnable runnable = new Runnable() {
@Override
public void run() {
System.out.println("Processing in TargetInnerRec");
}
};
runnable.run();
}
}
}