package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnn {}
record ParentClass(int val) {private void parentMethod() {this.parentMethod();}}
public record SourceClass(String data) {class MemberInner {void innerMethod() {SourceClass.this.overrideMethod();}}
static class StaticNested {}
@MyAnnprivate int overrideMethod() {assert TargetClass.targetField == 1;
int[] arr = { this.accessorMethod(1) };
int num = 1;while (num-- > 0) {((ParentClass) this).parentMethod();}
int var = TargetClass.targetField;variableCall = var;
new Runnable() {};return var;}
public void accessorMethod(int num) {}
int variableCall;}
non-sealed record TargetClass(int id) {static int targetField = 1;
{new Runnable() {};}}