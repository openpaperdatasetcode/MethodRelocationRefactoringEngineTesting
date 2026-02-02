package test;
import java.util.List;import java.util.ArrayList;import other.ExternalClass;
class TargetClass<T> {private T targetInstanceField;
class TargetInner {T innerField;
TargetInner(T val) {this.innerField = val;}}
public T getTargetInstanceField() {return targetInstanceField;}
public void setTargetInstanceField(T val) {this.targetInstanceField = val;}}
abstract class SourceClass permits SourceClass.ConcreteSource {private TargetClass<String> targetField = new TargetClass<>();
static class SourceStaticNested {}
class SourceMemberInner {}
/**
Overridden method to process TargetClass and return string list
@param targetParam TargetClass instance to process
@return List of processed strings*/@Overridepublic List<String> overridingMethod(TargetClass<String> targetParam) {List<String> result = new ArrayList<>();TargetClass<String>.TargetInner targetInner = new TargetClass<String>().new TargetInner("init-1");
this.targetField.setTargetInstanceField("source-field");ExternalClass.process(new SourceMemberInner());
try {targetParam.setTargetInstanceField(super.toString());result.add(targetParam.getTargetInstanceField());result.add(targetInner.innerField);} catch (Exception e) {}
return result;}
static class ConcreteSource extends SourceClass {}}
class ParentClass {public List<String> overridingMethod(TargetClass<String> targetParam) {return new ArrayList<>();}}
package other;
import test.SourceClass;
public class ExternalClass {static void process(SourceClass.SourceMemberInner inner) {}}