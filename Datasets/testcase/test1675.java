package test;
import java.util.List;
abstract class SourceClass<T> {private int outerPrivateField;
static class StaticNested {}
class MemberInner {/**
Method to test move refactoring*/private void testMethod(TargetClass param) {// Access outer private fieldint val = SourceClass.this.outerPrivateField;
// Raw type usageList rawList = new java.util.ArrayList();
// Access instance field of targetString field = param.targetField;
// Variable callvariableCall();
// Empty statement;}
private void variableCall() {}}}
class TargetClass extends ParentClass {String targetField;static class StaticNested {}}
class ParentClass {}