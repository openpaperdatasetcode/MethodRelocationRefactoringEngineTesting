package test;
import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;import java.lang.reflect.Constructor;import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
@Retention(RetentionPolicy.RUNTIME)@interface MethodAnn {String value() default "";}
abstract class SourceClass {public static class StaticNested {public static Object getField(TargetClass target) {return target.field;}
public static Object formatField(TargetClass target) {return target.field.toString() + "_formatted";}}
public class MemberInner {@MethodAnn("#{new InnerHelper(target).getList()}")protected void instanceMethod(TargetClass target) {class TypeDecl {boolean isValid(TargetClass t) {return t.field != null;}}
TypeDecl typeDecl = new TypeDecl();final TargetClass.Inner inner = target.new Inner();
staticCodeBlock: {Supplier<Object> supplier1 = StaticNested::getField;Supplier<Object> supplier2 = StaticNested::formatField;System.out.println(supplier1.get() + ", " + supplier2.get());}
if (typeDecl.isValid(target)) {switch (target.field.toString().length()) {case 0:System.out.println("Empty field");break;default:System.out.println("Valid field: " + target.field);}}
try {Constructor<TargetClass.Inner> constructor = TargetClass.Inner.class.getConstructor(TargetClass.class);TargetClass.Inner reflectedInner = constructor.newInstance(target);reflectedInner.printParentField();} catch (Exception e) {e.printStackTrace();}}}
protected class InnerHelper {private final TargetClass target;
public InnerHelper(TargetClass target) {this.target = target;}
public List<String> getList() {List<String> list = new ArrayList<>();list.add(target.field.toString());return list;}}}
public abstract class TargetClass implements Processable {protected Object field;
public TargetClass(Object field) {this.field = field;}
public class Inner {public Inner() {}
public final List<String> getList() {return superList();}
private List<String> superList() {List<String> list = new ArrayList<>();list.add(field.toString() + "_inner");return list;}
public void printParentField() {new Runnable() {@Overridepublic void run() {System.out.println("Parent field: " + field);}}.run();}}
@Overridepublic abstract void process();}
interface Processable {void process();}