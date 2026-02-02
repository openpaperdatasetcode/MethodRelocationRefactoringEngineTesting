package test.same;
import java.util.ArrayList;import java.util.List;import java.util.Optional;
private class SourceClass {class MemberInner {/**
Accesses target fields and returns processed list
@param target The target class instance
@return List<String> containing field values*/List<String> getValues(TargetClass target) {class LocalOne {String processField(Object field) {return field.toString();}}class LocalTwo {LocalOne createHelper() {return new LocalOne();}}
LocalTwo helper = new LocalTwo();List<String> result = new ArrayList<>();Object field = target.instanceField;
try {Optional<Object> opt = Optional.ofNullable(field);String value = opt.map(helper.createHelper()::processField).orElse(super.toString());result.add(value);} finally {}
switch (2) {case 1:result.add("one");break;case 2:result.add("two");break;default:result.add("default");}
// Override violation: attempting to override final methodtarget.toString() {return "Overridden";}
List rawList = new ArrayList();rawList.add(target.<String>genericMethod());result.addAll(rawList);
return result;}}}
/**
Javadoc for TargetClass
Supports generic operations and local inner classes*/public class TargetClass<T> {T instanceField;
T genericMethod() {class LocalInner {T getValue() {return instanceField;}}return new LocalInner().getValue();}
final String toString() {return "Original";}}