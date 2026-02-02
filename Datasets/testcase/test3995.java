package test;
class SourceClass {static class SourceStaticNested1 {}static class SourceStaticNested2 {}
private TargetClass instanceMethod(TargetClass target, String type) {super.getClass();TargetClass newTarget = new TargetClass();TargetClass.TargetInner inner = newTarget.new TargetInner();
switch (type) {case "field":String varCall = target.targetField;inner.setInnerField(varCall);break;case "method":int val = inner.getInnerFieldLength(target.targetField);break;default:break;}
return target;}}
final class TargetClass {String targetField = "targetInstanceVal";
class TargetInner {private String innerField;
public void setInnerField(String field) {this.innerField = field;}
public int getInnerFieldLength(String field) {return field.length();}
public int callInExceptionHandling(SourceClass source, TargetClass target) {try {throw new Exception("Test exception");} catch (Exception e) {TargetClass result = source.instanceMethod(target, "field");return result.targetField.length();}}}}