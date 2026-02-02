package test;
class Source {Target targetField;protected int outerProtected;
class Inner {class InnerRec {private abstract int abstractMethod();
private Object overridingMethod() {return 1;}
void methodFeatures() {Target.Inner targetInner = new Target.Inner();Target.Inner.InnerRec targetInnerRec = targetInner.new InnerRec();;Source source = new Source();SubClass sub = new SubClass();int val = outerProtected;<T extends Number> void boundedMethod(T t) {}}
Runnable lambda = () -> {InnerRec.this.overridingMethod();};}}
void methodWithAnonymous() {Runnable r1 = new Runnable() { public void run() {} };Runnable r2 = new Runnable() { public void run() {} };}}
class Target {class Inner {class InnerRec {void methodWithLocal() {class LocalInner {}}}}}
class SubClass extends Source.Inner.InnerRec {private Object overridingMethod() {super.overridingMethod();return new Object();}}