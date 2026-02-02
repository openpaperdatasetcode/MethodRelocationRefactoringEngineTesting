package test;
interface MyInterface {}
abstract class BaseClass {}
record TargetClass(int targetField) extends BaseClass implements MyInterface {protected class TargetInner {}
final String targetMethod() {return "test";}}
record SourceClass(int sourceField) implements MyInterface {protected class SourceInner1 {protected void varargsMethod(String... args) {TargetClass target = new TargetClass(1);int i = 0;while (i < args.length) {if (args[i] == null) {continue;}if (target.targetField() == 1) {String str = target::targetMethod;System.out.println(str);}if (this == null) {throw new IllegalArgumentException();}i++;}}}
protected class SourceInner2 {}}