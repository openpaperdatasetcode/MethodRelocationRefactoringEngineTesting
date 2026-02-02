package test;
private class SourceClass {// 1st member inner class (source_class feature)class FirstSourceInner {// Overloading methods to be refactoredprotected TargetClass processTarget(TargetClass target) {try {// Type declaration statementTargetClass.TargetInner targetInner = target.new TargetInner();
// 3 AssertStatements with "obj.field" (matches target_feature)assert target.field1 != null : "Field1 is null";assert target.field2 > 0 : "Field2 <= 0";assert targetInner.innerField.equals("init") : "InnerField not initialized";
// For statement + Continue statementfor (int i = 0; i < 5; i++) {if (i % 2 == 0) continue;// Variable call: update target fieldstarget.field2 += i;}
return target;} catch (Exception e) {e.printStackTrace();return null;}}
// Overloaded method (matches "method.type: overloading")protected TargetClass processTarget(TargetClass target, String suffix) {try {// Constructor invocation: create target inner class instanceTargetClass.TargetInner targetInner = new TargetClass.TargetInner(target, suffix);
// 3 AssertStatements with "obj.field" (matches target_feature)assert target.field1 != null : "Field1 is null";assert target.field2 > 0 : "Field2 <= 0";assert targetInner.innerField.endsWith(suffix) : "InnerField missing suffix";
return target;} catch (Exception e) {e.printStackTrace();return null;}}}
// 2nd member inner class (source_class feature)class SecondSourceInner {void callOverloadedMethods(TargetClass target) {FirstSourceInner inner = new FirstSourceInner();inner.processTarget(target);inner.processTarget(target, "_test");}}}
protected class TargetClass {String field1 = "default";int field2 = 10;
// Member inner class (target_class feature)class TargetInner {String innerField;
// Default constructorTargetInner() {this.innerField = "init";}
// Parameterized constructor (for constructor invocation)TargetInner(TargetClass outer, String suffix) {this.innerField = outer.field1 + suffix;}}}
// Subclass for call_method (sub_class type)private class SourceSubClass extends SourceClass {@Overridepublic SecondSourceInner new SecondSourceInner() {return new SecondSourceInner() {@Overridevoid callOverloadedMethods(TargetClass target) {FirstSourceInner inner = new FirstSourceInner();// if/else conditions (matches call_method "pos")if (target.field2 > 15) {// Lambda expression (matches target_feature "(parameters) -> methodBody")Runnable run = () -> inner.processTarget(target);run.run();} else {Runnable run = () -> inner.processTarget(target, "_sub");run.run();}}};}}