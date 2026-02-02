package test;
import java.util.List;import java.util.ArrayList;
interface SourceInterface {}
private abstract class ParentClass {protected abstract List<String> abstractMethod();}
private class TargetClass {class TargetInner {int innerField;
TargetInner(int val) {this.innerField = val;}}}
public class SourceClass extends ParentClass implements SourceInterface permits SourceSubClass {private static TargetClass targetStaticField = new TargetClass();private TargetClass.TargetInner targetInnerField;
static class SourceStaticNested {}
static {TargetClass target = getTargetInstance();TargetClass target2 = super.getTargetInstance();}
protected static TargetClass getTargetInstance() {return new TargetClass();}
class SourceInner {@CustomAnnotation(attr = SourceClass.super.abstractMethod())strictfp Object recursiveMethod(int depth) {if (depth <= 0) {return new Object();}
targetInnerField = targetStaticField.new TargetInner(depth);int count = 0;while (count < depth) {count++;}
switch (targetInnerField.innerField) {case 2:targetInnerField.innerField *= 2;break;default:break;}
return recursiveMethod(depth - 1);}}
public void createLocalInner() {class LocalInner {}}
@Overrideprotected final List<String> abstractMethod() {return new ArrayList<>();}}
class SourceSubClass extends SourceClass {}
@interface CustomAnnotation {List<String> attr();}