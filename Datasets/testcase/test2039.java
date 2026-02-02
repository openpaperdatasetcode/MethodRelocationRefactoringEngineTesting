package test;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.Retention;import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)@interface MyAnno {}
interface MyInterface {}
protected class SourceClass {class MemberInner {}
private List<String> methodToMove() {TargetClass target = new TargetClass();int fieldVal = target.targetField;
new MemberInner() {{System.out.println(fieldVal);}};
@MyAnnoList<String> list = new ArrayList<>();
Label: {if (fieldVal < 0) break Label;list.add(String.valueOf(fieldVal));}
super.toString();
switch (fieldVal) {case 1: list.add("one"); break;default: list.add("other");}
@MyAnnoint val = this.instanceMethod(fieldVal);
do {Object result = (param) -> target.privateMethod(param);} while (list.size() < 5);
return list;}
default int instanceMethod(int arg) {return arg * 2;}}
class TargetClass extends SourceClass implements MyInterface {int targetField;
static class StaticNested {}
private Object privateMethod(Object param) {return param;}}