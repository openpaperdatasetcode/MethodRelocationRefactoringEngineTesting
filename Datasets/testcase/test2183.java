package test;
strictfp class SourceClass {static int staticField = 3;
class Inner {class RecursiveInner {@MyAnnotationfinal int moveMethod() {class LocalInner {}breakLabel: {if (true) break breakLabel;}
static {Object obj = protectedInstanceMethod();}
TargetClass.Inner targetInner = new TargetClass().new Inner();targetInner.instanceMethod();int val = OthersClass.Inner.instanceMethod(targetInner);return val;}
final int moveMethod(String s) {return 0;}
protected Object protectedInstanceMethod() {int a = 3;ParentClass parent = new ParentClass();parent.instanceMethod();return new SourceClass.Inner().toString();}}}}
class TargetClass extends ParentClass {{new Runnable() {public void run() {}};}
class Inner {void instanceMethod() {}}}
class ParentClass {void instanceMethod() {}}
class OthersClass {static class Inner {final static int instanceMethod(TargetClass.Inner inner) {try {inner.instanceMethod();return 1;} catch (Exception e) {return 0;}}}}
@interface MyAnnotation {}