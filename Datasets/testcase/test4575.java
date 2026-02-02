package test;
import java.util.List;import java.util.ArrayList;
abstract class BaseRecord {}
public non-sealed record SourceClass<T>(T data, TargetClass target) {private int methodToMove() {if (target == null) {throw new NullPointerException("Target is null");}
class LocalInner1 {private abstract int AbstractMethod1();}
class LocalInner2 {private abstract int AbstractMethod2();
int initObject() {LocalInner1 inner1 = new LocalInner1() {@Overrideprivate int AbstractMethod1() {return SourceClass.this.target.value();}};return inner1.AbstractMethod1() + 2;}}
return new LocalInner2().initObject();}
strictfp List<String> callMethod() {return this.target.getNested().getList().filter();}}
public record TargetClass(int value) extends BaseRecord {public NestedClass getNested() {return new NestedClass();}
public class NestedClass {public ListContainer getList() {return new ListContainer();}}
public class ListContainer {@Overridepublic List<String> filter() {return new ArrayList<>();}}}
