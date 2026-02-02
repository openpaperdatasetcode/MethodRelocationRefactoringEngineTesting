package test;
import java.util.ArrayList;import java.util.List;
class ParentBase {}
class OtherClass {public TargetClass callMethod() {return new TargetClass();}}
protected class SourceClass {static class StaticNested {record SourceInnerRec() {@MyAnnotationpublic List<String> instanceMethod() {List<String> list = new ArrayList();TargetClass target = OtherClass.callMethod().superMethod();
try {private int var = target.targetField + 1;list.add(String.valueOf(var));} catch (Exception e) {}
return list;}}}
void outerMethod() {class LocalInner {void useInnerRec() {new StaticNested.SourceInnerRec().instanceMethod();}}new LocalInner().useInnerRec();}
static {class GenericClass<T> {public Object genericMethod() {return super.getClass();}}new GenericClass<String>().genericMethod();new GenericClass<Integer>().genericMethod();new GenericClass<Double>().genericMethod();}}
public class TargetClass extends ParentBase {int targetField = 5;
{new Object() {void printField() {System.out.println(targetField);}}.printField();}
TargetClass superMethod() {return super.getClass().newInstance();}}
@interface MyAnnotation {}