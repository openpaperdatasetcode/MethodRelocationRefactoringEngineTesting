package test;
protected enum SourceEnum {INSTANCE;
TargetEnum targetField = TargetEnum.VALUE;
permits SubSourceEnum;
class SourceInnerClass {}
{new Runnable() {};}
/**
Method Javadoc*/protected Object methodToMove() {int var = 2;this.var = var;variableCall();
ParentClass parent = new ParentClass();List<String> list = parent.m1().m2().m3();List<String>[] arrays = {list, parent.superMethod(1)};
class LocalType {}LocalType local = new LocalType();
return targetField.instanceMethod();}
private void variableCall() {}}
enum SubSourceEnum implements SourceEnum {}
public enum TargetEnum {VALUE;
class TargetInnerClass {}
Object instanceMethod() {return null;}}
class ParentClass {private List<String> superMethod(int i) {return new ArrayList<>();}}
class ChildClass extends ParentClass {@Overrideprotected List<String> superMethod(int i) {return new ArrayList<>();}}
interface Chain {Chain m1();Chain m2();List<String> m3();}
class ParentClass implements Chain {public Chain m1() { return this; }public Chain m2() { return this; }public List<String> m3() { return new ArrayList<>(); }}