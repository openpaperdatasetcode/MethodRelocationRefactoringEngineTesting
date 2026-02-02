package test;
interface GenericInterface<T> {TargetClass<T> process(TargetClass<T> target);}
private class SourceClass<T> {private T outerData = (T) "outer_value";
public class InnerClass implements GenericInterface<T> {// Overriding method in source_inner with strictfp modifier@Overridepublic strictfp TargetClass<T> process(TargetClass<T> target) {// Local inner class 1class TargetHandler {TargetClass<T> handle(TargetClass<T> t) {return t;}}
// Local inner class 2class ValueChecker {boolean isNull(T value) {return value == null;}}ValueChecker checker = new ValueChecker();
// Empty statement;
// Switch statementswitch (target.data.toString().length()) {case 0:target.data = (T) "empty";break;case 1:target.data = (T) "short";break;default:break;}
// Default NullLiteral (2 occurrences)Object nullVal1 = null;Object nullVal2 = null;if (checker.isNull(target.data)) {target.data = (T) nullVal1;}
// Super keywordSystem.out.println(super.toString());
// Variable call - access target's fieldif (target.data.equals(outerData)) {target.data = (T) "matched";}
// Uses outer thistarget.data = SourceClass.this.outerData;
return new TargetHandler().handle(target);}}}
/**
TargetClass with Javadoc
Generic class to hold data of type T*/private class TargetClass<T> {public T data;
public TargetClass(T data) {this.data = data;}}