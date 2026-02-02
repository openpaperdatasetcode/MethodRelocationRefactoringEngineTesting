package test;
import java.util.ArrayList;import java.util.List;
public class SourceClass {public class SourceInner {List<String> sourceMethod(TargetClass.TargetInnerRec param) {class LocalType {}LocalType obj = new LocalType();
StaticNested.staticConstructor();
int x;x = 5;;
param.call();obj = null;
return new ArrayList<>();}}
public static class StaticNested {static void staticConstructor() {new StaticNested();}}}
public class TargetClass<T> extends ParentClass {public class TargetInnerRec {void call() {Runnable r = new Runnable() {public void run() {System.out.println(super.field);}};}}}
class ParentClass {int field = 1;}