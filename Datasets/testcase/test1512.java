package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface EnumProcessor {}
protected enum Target {FIRST("first", 1, true),SECOND("second", 2, false),THIRD("third", 3, true);
public final String strField;public final int intField;public final boolean boolField;
Target(String strField, int intField, boolean boolField) {this.strField = strField;this.intField = intField;this.boolField = boolField;
// Anonymous inner class in target enumRunnable initializer = new Runnable() {@Overridepublic void run() {System.out.println("Initialized: " + name());}};initializer.run();}
class Inner {String getCombined() {return strField + "_" + intField;}}}
private enum Source<T> {PROCESSOR("processor");
private final String name;
Source(String name) {this(name, 0); // this(arguments)}
Source(String name, int version) {this.name = name;}
@EnumProcessorpublic int handle(Target target, T... values) {// Type declaration statementTarget.Inner targetInner = target.new Inner();int count = 0;
// BreakStatement with 3 target object fieldsloop:for (T value : values) {count++;if (target.strField.equals(value.toString())) {break loop;}if (target.intField == count) {break loop;}if (target.boolField) {break loop;}}
// Variable callSystem.out.println(targetInner.getCombined());
return count;}}