package test;
import other.DiffPackageTarget;
interface TargetInterface {}
abstract class TargetClass implements TargetInterface {String targetField;
public static class TargetStaticNested {private void innerInstanceMethod(TargetClass target) {target.targetField += "_processedByStaticNested";}}
public TargetClass(String field) {this.targetField = field;}}
class SourceClass {private int sourceField = 5;
static class SourceStaticNested {}
TargetClass instanceMethod(DiffPackageTarget diffTarget) {class SourceLocalInner {void localMethod() {System.out.println(SourceClass.this.sourceField);}}
SourceLocalInner localInner = new SourceLocalInner();TargetClass target = new TargetClass("init");TargetClass.TargetStaticNested targetStaticNested = new TargetClass.TargetStaticNested();
superConstructorInvocation: {new SubTargetClass(target.targetField) {};}
for (int i = 0; i < 3; i++) {if (i == 1) {continue;}targetStaticNested.innerInstanceMethod(target);target.targetField += String.valueOf(i);}
if (diffTarget.getTargetField() == null) {throw new IllegalArgumentException("Diff package target field is null: " + this.sourceField);}
variableCall(target);localInner.localMethod();return target;}
private void variableCall(TargetClass target) {target.targetField += "_fromVariableCall";}}
class SubTargetClass extends TargetClass {public SubTargetClass(String field) {super(field);}}
package other;
public class DiffPackageTarget {private String targetField;
public String getTargetField() {return targetField;}
public void setTargetField(String targetField) {this.targetField = targetField;}}