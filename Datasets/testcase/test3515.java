package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface AbstractMethodAnnotation {String desc() default "Abstract method for Move Method refactoring test";}
public abstract class SourceClass {public class MemberInner {public void helperMethod(TargetClass target) {synchronized (target) {System.out.println("Processing target: " + target.field);}}}
@AbstractMethodAnnotation(desc = "Returns processed TargetClass instance")public abstract TargetClass abstractMethod(TargetClass target);
{new Runnable() {@Overridepublic void run() {TargetClass target = new TargetClass() {@Overridepublic String getField() {super.toString();return field + "_anonymous";}};System.out.println(target.getField());}}.run();}}
abstract class ParentTarget {protected String parentField = "parent_data";}
private abstract class TargetClass extends ParentTarget {protected String field;
public TargetClass() {this.field = "default";}
public abstract String getField();
{new Runnable() {@Overridepublic void run() {super.parentField = parentField + "_updated";}}.run();}}
// Concrete implementation for testabilityclass ConcreteSource extends SourceClass {@Overridepublic TargetClass abstractMethod(TargetClass target) {new MemberInner().helperMethod(target);target.field = target.getField() + "_processed";super.toString();return target;}}
class ConcreteTarget extends TargetClass {@Overridepublic String getField() {return field;}}
