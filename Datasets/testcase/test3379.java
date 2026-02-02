package test;
import otherpackage.DiffPackageTarget;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.io.IOException;import java.util.ArrayList;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnnotation {}
abstract class SourceClass {static class StaticNested {}
class MemberInner {// Call_method: inner_class, default modifierList<String> callMethod(TargetClass target) {List<String> result = new ArrayList<>();int count = 0;
// do-while positiondo {// Lambda expression (target_feature)Runnable lambda = () -> target.staticNested.doTask();lambda.run();result.add(String.valueOf(target.field));count++;} while (count < 1);
return result;}}
@MethodAnnotation // has_annotationpublic abstract TargetClass process(TargetClass target) throws IOException;}
class ConcreteSource extends SourceClass {@Overridepublic TargetClass process(TargetClass target) throws IOException {// Try statementtry {// VariableDeclarationStatement (target_feature: this.field=3)DiffPackageTarget.declareVariable(target);
variableCall(target);new StaticNested();new MemberInner().callMethod(target);} catch (Exception e) {throw new IOException("Processing failed", e);}return target;}
private void variableCall(TargetClass target) {target.staticNested.doTask();}}
// Target class with empty modifier (extends ParentTarget)class TargetClass extends ParentTarget {public int field = 3; // this.field=3
public static class StaticNested {public void doTask() {}}
public StaticNested staticNested = new StaticNested();
public TargetClass process() {return this;}}
class ParentTarget {protected int parentField = 3;}
// Different package class for VariableDeclarationStatement positionpackage otherpackage;
import test.TargetClass;
public class DiffPackageTarget {public static void declareVariable(TargetClass target) {// Public VariableDeclarationStatement (target_feature: this.field=3)public int targetField = target.this.field;if (targetField != 3) throw new IllegalArgumentException();}}