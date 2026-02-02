package test;
import java.io.IOException;import java.util.ArrayList;
protected class SourceClass<T> {// Member inner class (matches source_class.feature)class SourceInner {String innerField;}
// Anonymous inner class (matches source_class.feature)Runnable anonRunnable = new Runnable() {@Overridepublic void run() {System.out.println(SourceClass.this.toString());}};
// Recursive method to refactor (matches method.type: recursion)private TargetClass<T>.TargetInner moveMethod() throws IOException {TargetClass<T> target = new TargetClass<>();TargetClass<T>.TargetInner targetInner = target.new TargetInner();int count = 0;
// Uses outer this (matches method.features)SourceInner sourceInner = SourceClass.this.new SourceInner();
// While statement (matches method.features)while (count < 2) {// IfStatement with super.field and "1" (matches nested feature)if (target.superField == 1) {continue; // Continue statement (matches method.features)}
// Switch statement (matches method.features)switch (count) {case 0:sourceInner.innerField = "case0";break;case 1:sourceInner.innerField = "case1";break;}
count++;}
// InstanceofExpression with numbers:2 (matches feature)boolean isTarget = target instanceof TargetClass;boolean isInner = targetInner instanceof TargetClass.TargetInner;
// Requires try-catch (matches method.features)try {if (isTarget && isInner) {targetInner.writeData(sourceInner.innerField);}} catch (IOException e) {throw e;}
// Instance method in array initialization (matches nested feature)Object[] array = {new TargetClass<>().new TargetInner().synchronizedMethod(1),new TargetClass<>().new TargetInner().synchronizedMethod(2)};
// Recursive call (matches method.type: recursion)if (count > 0) {return moveMethod();}
return targetInner;}}
class TargetClass extends ParentTargetClass {
// Target field (matches per_condition)
U targetField;
// Anonymous inner class (matches target_class.target_feature)Runnable targetAnon = new Runnable() {@Overridepublic void run() {System.out.println("Target anonymous");}};
// Target inner class (matches target class: target_inner)class TargetInner {// Synchronized instance method (matches nested feature)synchronized Object synchronizedMethod(int num) {return num * 2;}
// Method throwing IOException (matches method.features)void writeData(String data) throws IOException {if (data == null) {throw new IOException("Data is null");}targetField = (U) data;}}}
class ParentTargetClass {// super.field for IfStatement (matches nested feature)protected int superField = 1;}