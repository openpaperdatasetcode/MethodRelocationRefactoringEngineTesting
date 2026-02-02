package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
private class SourceClass extends ParentClass implements SomeInterface {static class StaticNested {}
class InnerRec {public List<String> methodToMove(TargetClass.InnerRec param) {class LocalType {}LocalType[] types = new LocalType[3];
StaticNested.field; ;
Supplier<Object> supplier = () -> {SourceClass outer = new SourceClass();return outer.new InnerRec().overrideMethod();};
List<String> list = new ArrayList<>();list.add(param.innerField);StaticNested nested = new StaticNested();return list;}
@Overridepublic Object overrideMethod() {return null;}}
{new Runnable() {public void run() {}};}}
private class TargetClass {class InnerRec {String innerField;}}
class ParentClass {}
interface SomeInterface {Object overrideMethod();}