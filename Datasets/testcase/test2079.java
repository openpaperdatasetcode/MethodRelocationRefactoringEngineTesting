package test;
interface ParentInterface {}
interface SourceInterface<T> {static class StaticNested {}
default void createAnonymous() {Runnable r = new Runnable() {public void run() {}};}
/**
Default accessor method with base type return
@return base type (int)*/default int getValue() {new SuperClass();TargetInterface target = new TargetInterface() {};int result = 0;
for (int i = 0; i < 3; i++) {Object val1 = target.getId();Object val2 = target.getName();Object val3 = target.getStatus();}
switch (target.getCode()) {case 1:target.call();result = 1;break;default:result = 0;break;}
return result;}}
class SuperClass {public SuperClass() {}}
public interface TargetInterface extends ParentInterface {class MemberInner {protected int field;}
default int getCode() { return 0; }default Object getId() { return null; }default Object getName() { return null; }default Object getStatus() { return null; }default void call() {}}