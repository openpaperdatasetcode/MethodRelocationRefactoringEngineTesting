package test.refactoring.movemethod;
import java.util.function.Supplier;
public class SourceClass {private String value = "source_value";protected TargetClass targetField;
static class StaticNestedSource {public static void init() {}}
class MemberInnerSource {Object getTargetData() {return targetField.new MemberInnerTarget().getData();}}
static {ParentClass parent = new ParentClass();Supplier<Void> accessor1 = parent::setName;Supplier<Void> accessor2 = parent::setAge;accessor1.get();accessor2.get();}
Object process(TargetClass targetParam) {this.targetField = targetParam;
ParentClass parentInstance = new ParentClass("parent_name", 30);TargetClass.MemberInnerTarget innerTarget = new TargetClass.MemberInnerTarget(parentInstance.superField);
Runnable runnable = () -> System.out.println(this.value);runnable.run();
int count = 0;while (count < 2) {callOverloaded(targetParam, "arg1");callOverloaded(targetParam, "arg1", count);count++;}
return innerTarget.getData();}
public void callOverloaded(TargetClass target, String arg) {target.process(arg);}
public void callOverloaded(TargetClass target, String arg, int num) {target.process(arg, num);}}
class ParentClass {protected String superField;
public ParentClass() {this.superField = "default_super";}
public ParentClass(String name, int age) {this.superField = name + "_" + age;}
public void setName() {this.superField = "updated_name";}
public void setAge() {this.superField = "updated_age";}}
public class TargetClass {class MemberInnerTarget {private String data;
public MemberInnerTarget(String data) {this.data = data;}
public Object getData() {return data;}}
public <T extends CharSequence> void process(T arg) {System.out.println("Process: " + arg);}
public <T extends CharSequence> void process(T arg, int num) {System.out.println("Process: " + arg + ", num: " + num);}}
public class MoveMethodTest5182 {public static void main(String[] args) {SourceClass source = new SourceClass();TargetClass target = new TargetClass();
Object result = source.process(target);System.out.println(result);
SourceClass.MemberInnerSource innerSource = source.new MemberInnerSource();System.out.println(innerSource.getTargetData());}}