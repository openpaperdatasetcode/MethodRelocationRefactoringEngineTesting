package test;

class TargetClass {
private int targetField;

class TargetInnerRec {
private String innerData;

TargetInnerRec() {
Runnable r = new Runnable() {
@Override
public void run() {
System.out.println("Target inner anonymous class");
}
};
r.run();
}
}
}

class SourceClass {
private TargetClass target;
private int outerPrivate;

SourceClass(TargetClass target) {
this.target = target;

// Anonymous inner class
Runnable anon = new Runnable() {
@Override
public void run() {
System.out.println("Source anonymous class");
}
};
anon.run();

// Local inner class
class LocalInner {
// Constructor to be moved
LocalInner() {
outerPrivate = 5; // access_outer_private

if (target != null) {
Object obj = TargetClass.TargetInnerRec.class.newInstance(); // OuterClass.InnerClass.methodName()
} else {
TargetClass parent = new TargetClass(); // parent_class
TargetClass.TargetInnerRec inner = parent.new TargetInnerRec();
}

// variable call
int localVar = 3; // 3
System.out.println(localVar);

// Call chain: obj.m1().m2().m3()
for (int i = 0; i < 5; i++) {
OthersClass.getObject().m1().m2().m3();
}
}

// Override violation (method with same signature as Object but wrong return type)
public String toString() {
return "LocalInner";
}
}

new LocalInner();
}
}

class OthersClass {
private static Object instance;

static Object getObject() {
return instance;
}

Object m1() {
return this;
}

Object m2() {
return this;
}

Object m3() {
return this;
}
}