package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MethodFeatureAnnot {}
class ParentSource {public ParentSource() {}protected void parentMethod() {}}
private class SourceClass<T> extends ParentSource {private TargetClass targetField = new TargetClass();
class MemberInner {}
public void outerMethod() {class LocalInner {}new LocalInner();}
@MethodFeatureAnnotpublic List<String> moveMethod() {super();List<String> result = new ArrayList<>();int flag = 1;
switch (flag) {case 1:class TypeDeclaration1 {}new TypeDeclaration1();variableCall();break;default:result.add("default");}
return result;}
@MethodFeatureAnnotpublic List<String> moveMethod(T param) {super();return new ArrayList<>();}
private void variableCall() {targetField.doTask();}
strictfp List<String> callMethod() {class InnerCaller {List<String> invoke(TargetClass target) {int count = 0;while (count < 1) {TargetClass newTarget = new TargetClass();super.parentMethod();count++;return newTarget.moveMethod();}return new ArrayList<>();}}return new InnerCaller().invoke(targetField);}}
public class TargetClass {public void doTask() {}
public void targetMethod() {class LocalInner {}new LocalInner();}
public List<String> moveMethod() {return new ArrayList<>();}
public List<String> moveMethod(String param) {return new ArrayList<>();}}