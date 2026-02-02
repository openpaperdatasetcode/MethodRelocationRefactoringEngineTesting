// Target class packagepackage com.target;
import java.util.List;
// Target class (public modifier + member inner class)public class TargetClass {public static int staticField; // ClassName.field for TryStatementpublic class TargetInner {} // target_feature: member inner class}
// Source class package (different from target)package com.source;
import com.target.TargetClass;import java.util.List;import java.util.ArrayList;
// Parent class for source extends featureclass SourceParent<T> {public abstract List<String> process(TargetClass.TargetInner inner);}
// Source class (private modifier + different package + type parameter + extends + two local inner classes)private class SourceClass<T> extends SourceParent<T> {@Overridepublic final List<String> methodToMove(TargetClass.TargetInner inner) {List<String> result = new ArrayList<>();
// Variable callTargetClass target = new TargetClass();TargetClass.staticField = 1;
// Constructor invocationTargetClass.TargetInner newInner = target.new TargetInner();
// Type declaration statementclass LocalType {}LocalType local = new LocalType();
// TryStatement (private modifier, ClassName.field = 1, pos: source)try {if (TargetClass.staticField != 1) {throw new IllegalArgumentException("Field value mismatch");}result.add("field: " + TargetClass.staticField);} catch (IllegalArgumentException e) {// No new exception thrownresult.add("error: " + e.getMessage());}
// Continue statementfor (int i = 0; i < 3; i++) {if (i == 1) continue;result.add("loop: " + i);}
// Source feature: first local inner classclass LocalInner1 {void addToResult() {result.add("local1: " + target.staticField);}}new LocalInner1().addToResult();
// Source feature: second local inner class (depends_on_inner_class)class LocalInner2 {void processInner(TargetClass.TargetInner in) {result.add("local2: " + in.toString());}}new LocalInner2().processInner(inner);
return result;}}
