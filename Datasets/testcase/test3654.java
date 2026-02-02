package test;
interface BaseInterface {void process(TargetClass target);}
sealed class SourceClass permits SubSourceClass {protected String sourceField = "base_source_field";
public static class StaticNestedFirst {public static TargetClass.InnerTarget createInnerTarget(TargetClass target) {return target.new InnerTarget();}}
public static class StaticNestedSecond {public static void invokeTargetMethod(TargetClass.InnerTarget inner) {inner.process();}}
static {TargetClass target = new TargetClass();Object result = target.new InnerTarget().setValue("static_init").getValue().toString();System.out.println(result);}
@Override@Deprecatedpublic void process(TargetClass target) {TargetClass.InnerTarget inner = StaticNestedFirst.createInnerTarget(target);inner.setValue(SourceClass.this.sourceField);
String switchResult = switch (inner.getValue().length()) {case 1 -> "short";default -> "long";};System.out.println(switchResult);
StaticNestedSecond.invokeTargetMethod(inner);overloadMethod(target);overloadMethod(inner);
TargetClass result = (target.getField().isEmpty()) ? callParentMethod(target) : target;System.out.println(result.getField());}
public void overloadMethod(TargetClass target) {target.setField(target.new InnerTarget().getValue());}
public void overloadMethod(TargetClass.InnerTarget inner) {inner.setValue(inner.getValue() + "_overload");}
protected TargetClass callParentMethod(TargetClass target) {return new ParentClass().superTypeMethod(target);}}
final class SubSourceClass extends SourceClass implements BaseInterface {@Overridepublic void process(TargetClass target) {super.process(target);TargetClass.InnerTarget inner = target.new InnerTarget();inner.setValue(SubSourceClass.this.sourceField + "_sub");}}
sealed class TargetClass permits SubTargetClass {protected String targetField = "base_target_field";
public class InnerTarget {private String value;
public TargetClass.InnerTarget setValue(String val) {this.value = val;return this;}
public String getValue() {return value;}
public void process() {this.value += "_processed";}}
public String getField() {return targetField;}
public void setField(String field) {this.targetField = field;}}
final class SubTargetClass extends TargetClass {@Overridepublic String getField() {return super.getField() + "_sub_target";}}
class ParentClass {protected TargetClass superTypeMethod(TargetClass target) {target.setField(super.toString() + "_parent");return target;}}