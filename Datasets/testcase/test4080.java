package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AttrAnnotation {String value() default "";}
private sealed record SourceRecord(int sourceField, TargetRecord targetField) permits SubSourceRecord {
@AttrAnnotation(value = "anno_attr")public void varargsMethod(String... args) {try {int thisFieldVal = this.sourceField;if (thisFieldVal == 1) {System.out.println("Expression statement with this.field=1");}
defaultInstanceMethod(1, new ParentClass(), this, args);
TargetRecord newTarget = new TargetRecord(5, "test");String var = newTarget.targetStrField();System.out.println(var);} catch (Exception e) {e.printStackTrace();}
class LocalInner1 {void innerMethod1() {}}new LocalInner1().innerMethod1();
class LocalInner2 {void innerMethod2() {}}new LocalInner2().innerMethod2();}
default void defaultInstanceMethod(int num, ParentClass parent, SourceRecord instance, String... args) {if (num == 1) {parent.parentMethod();instance.varargsMethod(args);}}}
non-sealed record SubSourceRecord(int sourceField, TargetRecord targetField) extends SourceRecord {public SubSourceRecord(int sourceField, TargetRecord targetField) {super(sourceField, targetField);}}
class ParentClass {public void parentMethod() {}}
record TargetRecord(int targetIntField, String targetStrField) {
public void normalMethod() {class TargetLocalInner {void innerMethod() {SourceRecord.varargsMethod("inner_arg");}}new TargetLocalInner().innerMethod();}
public String callMethod() {Runnable lambda = () -> TargetRecord.normalMethod();lambda.run();return targetStrField();}}