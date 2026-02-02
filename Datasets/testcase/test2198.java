package test;
import java.util.ArrayList;import java.util.List;import java.util.function.Supplier;
strictfp class SourceClass<T> implements Supplier<List<String>> {static class StaticNested {}
class Inner {class RecursiveInner {@Overridepublic List<String> get() {int[] array = {1};TargetClass target = new TargetClass();TargetClass.Inner targetInner = target.new Inner();
{Object result = targetInner.super.method(3);}
List<String> list = new ArrayList<>();list.add(String.valueOf(target.field));list.add(String.valueOf(super.toString()));
return list;}
public Object method(int param) {return null;}}}
@Overridepublic List<String> get() {return new Inner().new RecursiveInner().get();}}
class TargetClass {String field;
class Inner extends ParentInner {@Overridepublic Object method(int num) {return super.method(num);}}}
class ParentInner {public Object method(int param) {return param;}}