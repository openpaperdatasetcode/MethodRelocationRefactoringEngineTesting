package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnnot {}
class SuperTarget {}
class Source {private Target targetField = new Target();
static class StaticNested {int nestedField;}
@TestAnnotfinal int instanceMethod() {try {Target.StaticNested targetStatic = new Target.StaticNested();List<String> methodResult = this.publicInstanceMethod(1);
variableCall(targetField);methodWithLocalInner(targetField);
return targetField.targetField + targetStatic.nestedField;} catch (Exception e) {e.printStackTrace();return 0;}}
public List<String> publicInstanceMethod(int num) {List<String> list = new ArrayList<>();list.add(String.valueOf(num));return list;}
private void variableCall(Target target) {int val = target.targetField;target.targetMethod();}
private void methodWithLocalInner(Target target) {class LocalInner {void localMethod() {int localVal = target.targetField;}}new LocalInner().localMethod();}
class InnerConstructor {InnerConstructor() {this(publicInstanceMethod(1));}
InnerConstructor(List<String> param) {}}}
private class Target extends SuperTarget {int targetField;
static class StaticNested {int nestedField = 5;}
void targetMethod() {}}
