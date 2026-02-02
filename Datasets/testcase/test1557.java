package test;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface CallAnnotation {Class<?> value() default TargetClass.class;}
protected class SourceClass implements Runnable {public static class StaticNested {String name;}
public class MemberInner extends ParentInner {@Overridepublic TargetClass process(TargetClass target) {class LocalHandler {TargetClass handle() {return new TargetClass();}}
this(5);StaticNested nested = new StaticNested();nested.name = "static";
for (int i = 0; i < 3; i++) {if (i == 1) continue;target.setValue(nested.name + i);}
return new LocalHandler().handle();}
public MemberInner(int num) {super(num);}}
@Overridepublic void run() {}}
class ParentInner {public ParentInner(int num) {}}
public class TargetClass {private String value;
public TargetClass() {}
public void setValue(String val) {class LocalSetter {void apply() {value = val;}}new LocalSetter().apply();}
public static TargetClass create() {return new TargetClass();}
public TargetClass chain1() {return this;}
public TargetClass chain2() {return this;}
public TargetClass chain3() {return this;}}
class AnnotationUser {@CallAnnotation(TargetClass.create().chain1().chain2().chain3().getClass())public void useAnnotation() {}}