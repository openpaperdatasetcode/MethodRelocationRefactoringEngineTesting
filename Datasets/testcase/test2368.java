package test;
import java.util.ArrayList;import java.util.List;import java.util.Arrays;
/**
Javadoc for TargetClass
Contains member inner class*/class TargetClass {int field = 5;
class TargetInner {String name;
TargetInner(String name) {this.name = name;}}}
protected class SourceClass<T> {class SourceInner1 {}
class SourceInner2 {class SourceInnerRec {strictfp List<String> methodToMove(TargetClass targetParam) {// DoStatement with target features (diff_package_others)DiffPackageClass.process(() -> {int i = 0;do {staticMethod(targetParam);i++;} while (i < targetParam.field + 1);});
// Constructor invocationTargetClass.TargetInner inner = targetParam.new TargetInner("test");
// If statementif (inner.name != null) {inner.name = inner.name.toUpperCase();}
// Variable callint var = targetParam.field;String innerVar = inner.name;
// With boundsSourceClass<? extends CharSequence> bounded = new SourceClass<>();
// Requires try-catchList<String> result = new ArrayList<>();try {result.add(innerVar);result.add(String.valueOf(var));} catch (NullPointerException e) {result.add("error");}
return result;}
private static void staticMethod(TargetClass target) {System.out.println(target.field);}}}}
// Assume this class is in a different packagepackage other;
import test.TargetClass;import java.util.function.Runnable;
public class DiffPackageClass {public static void process(Runnable runnable) {runnable.run();}}