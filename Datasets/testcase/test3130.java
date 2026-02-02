// Target class packagepackage com.target;
import java.lang.reflect.Field;
// Target class (default modifier + anonymous inner class + inner record)class TargetClass {public static int staticField; // ClassName.fieldclass TargetInner {record TargetInnerRec() {} // target_inner_rec}
public TargetClass() {// Target feature: anonymous inner classRunnable r = new Runnable() {@Overridepublic void run() {}};}
// WhileStatement (private modifier, ClassName.field = 1, pos: same_package_target)private void executeWhile() {while (TargetClass.staticField < 1) {TargetClass.staticField = 1;}}}
// Source class package (different from target)package com.source;
import com.target.TargetClass;import com.target.TargetClass.TargetInner.TargetInnerRec;import java.lang.reflect.Method;
// Parent class for overridingabstract class SourceParent {protected abstract void process(TargetInnerRec rec);}
// Source class (final modifier + different package + static nested + member inner)final class SourceClass extends SourceParent {// Source feature: static nested classstatic class SourceStaticNested {}// Source feature: member inner classclass SourceInner {}
@Overrideprotected void methodToMove(TargetInnerRec rec) {// Variable callTargetClass target = new TargetClass();target.executeWhile();int fieldVal = TargetClass.staticField;
// Raw typeTargetClass rawTarget = target;
// InstanceofExpression (numbers:2, modifier:default)Object obj1 = new TargetClass();Object obj2 = new TargetInnerRec();boolean isTarget1 = obj1 instanceof TargetClass;boolean isTarget2 = obj2 instanceof TargetInnerRec;
// Requires_try_catchtry {if (TargetClass.staticField != 1) throw new IllegalArgumentException("Field mismatch");} catch (IllegalArgumentException e) {// No new exception thrown}
// Used_by_reflectiontry {Method whileMethod = TargetClass.class.getDeclaredMethod("executeWhile");whileMethod.setAccessible(true);whileMethod.invoke(target);
Field staticField = TargetClass.class.getDeclaredField("staticField");staticField.setAccessible(true);fieldVal = (int) staticField.get(null);} catch (Exception e) {// No new exception thrown}}}