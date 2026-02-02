package test;
import com.other.DiffPackageCreator;import java.io.IOException;import java.lang.reflect.Method;import java.util.ArrayList;import java.util.List;
private class SourceClass {protected String outerProtected = "outer_protected";private int outerValue = 100;
public static class StaticNested1 {public static String DATA = "nested1_data";}
public static class StaticNested2 {public static int compute(int x) {return x * 2;}}
@TestAnnotationpublic Object varargsMethod(TargetClass... targets) {List<Object> results = new ArrayList<>();; // Empty statement
// Constructor invocationTargetClass defaultTarget = new TargetClass("default");results.add(defaultTarget.value);
// Variable callfor (TargetClass target : targets) {results.add(target.getValue());results.add(target.inner.calculate(5));}
// Access outer protectedresults.add(outerProtected + "_" + targets[0].value);
// Access instance fieldresults.add(targets[0].count);
// Depends on inner classTargetClass.Inner inner = targets[0].new Inner("depends");results.add(inner.process());
// Uses outer thisRunnable outerRunnable = () -> System.out.println(SourceClass.this.outerValue);outerRunnable.run();
// Lambda: () -> System.out.println(this.value)Runnable targetRunnable = targets[0]::printValue;targetRunnable.run();
// Used by reflectiontry {Method method = TargetClass.Inner.class.getMethod("process");results.add(method.invoke(inner));} catch (Exception e) {// No new exception}
// IOException handlingtry {if (targets.length == 0) {throw new IOException("No targets provided");}} catch (IOException e) {results.add("Handled: " + e.getMessage());}
// Diff package others with private ConstructorInvocation (3 obj.field)results.add(DiffPackageCreator.createInner(targets[0]));
return results;}}
class TargetClass {public String value;public int count;
public TargetClass(String value) {this.value = value;this.count = value.length();}
public String getValue() {return value;}
public void printValue() {System.out.println(this.value);}
public class Inner {private String data;
public Inner(String data) {this.data = data;}
public String process() {return data + "_processed";}
public int calculate(int factor) {return count * factor;}}
public Inner inner = new Inner("default_inner");}
package com.other;
import test.TargetClass;
public class DiffPackageCreator {public static TargetClass.Inner createInner(TargetClass target) {class PrivateCreator {private TargetClass.Inner create() {// 3 obj.field referencesString newData = target.value + "" + target.count + "" + target.inner.data;return target.new Inner(newData);}}return new PrivateCreator().create();}}
@interface TestAnnotation {}