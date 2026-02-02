package test;
public sealed class SourceClass permits SourceSubClass {
public static class SourceStaticNested {public void staticHelper (TargetClass<String> target) {new SourceInner().overloadingMethod(target);}}
public class SourceInner {
public Object overloadingMethod (TargetClass<String> target) {if (target == null) {throw new NullPointerException ("TargetClass cannot be null");}; 
TargetClass.TargetInner<String> inner = target.new TargetInner<>("init_data");variableCall(inner);access_instance_method(inner);return inner.getInnerData();}
public Object overloadingMethod (TargetClass<Integer> target) {if (target == null) {throw new NullPointerException ("TargetClass cannot be null");}; 
TargetClass.TargetInner<Integer> inner = target.new TargetInner<>(100);variableCall(inner);access_instance_method(inner);return inner.getInnerData();}
private <T> void variableCall(TargetClass.TargetInner<T> inner) {inner.setInnerData(inner.getInnerData());}
private <T> void access_instance_method(TargetClass.TargetInner<T> inner) {inner.printData();}}}
final class SourceSubClass extends SourceClass {}
class TargetClass<T> extends TargetParentClass {
public class TargetInner<T> {private T innerData;
public TargetInner(T data) {this.innerData = data;}
public T getInnerData () {return innerData;}
public void setInnerData(T data) {this.innerData = data;}
public void printData() {System.out.println(innerData);}}
public TargetClass () {super ("target_parent_data"); }}
class TargetParentClass {protected String parentData;
public TargetParentClass(String data) {this.parentData = data;}}
