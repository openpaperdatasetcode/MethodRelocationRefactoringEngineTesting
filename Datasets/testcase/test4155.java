package test;
abstract class SourceClass {private String sourceField = "source_data";
public class MemberInner {public TargetClass getTargetInstance() {return new TargetClass() {};}}
public static class StaticNested {public void useTarget(TargetClass target) {System.out.println(target.targetField);}}
private void process(TargetClass target) {new MemberInner();;
TargetClass.StaticNested targetStatic = new TargetClass.StaticNested();TargetClass result = targetStatic.createTarget(sourceField);StaticNested.useTarget(target);
String combined = target.targetField + sourceField;System.out.println(combined);}}
public abstract class TargetClass {String targetField;
private TargetClass(String field) {super();this.targetField = field;}
public TargetClass() {this("default_target_field");}
public static class StaticNested {public TargetClass createTarget(String field) {return new TargetClass(field) {};}}
public abstract void abstractMethod();}
