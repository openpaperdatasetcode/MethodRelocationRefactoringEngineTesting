package test;

import java.util.List;

abstract class SourceClass<T extends Number> {
protected int sourceField;

// Member inner class
protected class MemberInner {
private String name;

MemberInner(String name) {
this.name = name;
}
}

// Constructor with super constructor invocation
protected SourceClass() {
super();
}

// Instance method to be refactored
protected void instanceMethod(TargetClass target) {
// Variable call
target.targetField = 5;

// For statement
for (int i = 0; i < target.targetField; i++) {
// If/else conditions with call chain
if (i % 2 == 0) {
OthersClass.getInstance().m1().m2().m3();
} else {
OthersClass.getInstance().m1("overload").m2().m3();
}
}

// Private WhileStatement with this.field and 1 (diff_package_target simulation)
int count = 0;
while (count < 1) {
this.sourceField = count; // this.field
count++;
}

// Anonymous inner class
Runnable runnable = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous class in SourceClass");
}
};
runnable.run();

// With bounds (using type parameter T with bound Number)
T number = (T) Integer.valueOf(10);
System.out.println(number.intValue());
}
}

class TargetClass {
int targetField;

public TargetClass() {
// Anonymous inner class
Runnable runnable = new Runnable() {
@Override
public void run() {
System.out.println("Anonymous class in TargetClass");
}
};
runnable.run();
}
}

class OthersClass {
private static OthersClass instance = new OthersClass();

public static OthersClass getInstance() {
return instance;
}

// Overloading methods
public OthersClass m1() {
return this;
}

public OthersClass m1(String param) {
return this;
}

public OthersClass m2() {
return this;
}

public void m3() {
// Empty implementation
}
}