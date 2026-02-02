package test;
import java.util.ArrayList;import java.util.List;
protected enum SourceClass {INSTANCE;
private TargetClass targetField = TargetClass.VALUE;
static class SourceStaticNested {}
class SourceInner {class SourceInnerRec {/**
Method Javadoc for overloaded method
@param param target inner parameter
@return List<String> result*/private List<String> overloadedMethod(TargetClass.TargetInner param) {// ConstructorInvocation with target featuresSourceStaticNested nested = new SourceStaticNested();int val = this.hashCode() + 1;
// Static method in ternary operatorList<String> list = (1 > 0) ? OthersClass.staticMethod() : new ArrayList<>();
// Constructor invocationTargetClass.TargetInner newInner = new TargetClass.TargetInner(param.field);
// Expression statementString expr = param.field.toString();
// Variable callint var = targetField.nestedField;
return list;}
private List<String> overloadedMethod(String str) {return new ArrayList<>();}}}
void localInnerMethod() {class LocalInner {}}}
protected enum TargetClass {VALUE;
int nestedField = 5;
static class TargetStaticNested {}
static class TargetInner {int field;
private TargetInner(int field) {this.field = field;}}}
class OthersClass {static List<String> staticMethod() {return new ArrayList<>();}}