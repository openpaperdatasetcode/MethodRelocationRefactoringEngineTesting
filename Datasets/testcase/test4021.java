package test;
import java.util.List;import java.util.ArrayList;
protected class TargetClass {class TargetInner {String innerData;
TargetInner(String data) {this.innerData = data;}
void printData() {System.out.println(innerData);}
void printData (int count) {for (int i = 0; i < count; i++) {System.out.println (innerData);}}}}
abstract class SourceClass permits SourceClass.ConcreteSource {public void createLocalInner1() {class SourceLocalInner1 {}}
public void createLocalInner2() {class SourceLocalInner2 {}}
@RefactorAnnotationpublic final List<String> recursiveMethod(TargetClass.TargetInner targetInner, int depth) {List<String> result = new ArrayList<>();if (depth <= 0) {result.add(targetInner.innerData);return result;}
OthersClass others = new OthersClass ();TargetClass newTarget = others.createTargetInstance ("depth-" + depth);TargetClass.TargetInner newInner = newTarget.new TargetInner ("inner-" + depth);
int count = 0;do {result.add (newInner.innerData);count++;} while (count < 2);
TargetClass.TargetInner [] innerArr = new TargetClass.TargetInner [] {targetInner,newInner};for (TargetClass.TargetInner inner : innerArr) {TargetClass.InnerClass.staticMethod (inner);}
result.addAll (recursiveMethod (newInner, depth - 1));return result;}
static class ConcreteSource extends SourceClass {}
static class InnerClass {protected static void staticMethod (TargetClass.TargetInner inner) {inner.printData ();}}}
class OthersClass {protected TargetClass createTargetInstance (String data) {TargetClass target = new TargetClass ();target.new TargetInner (data);return target;}
protected TargetClass createTargetInstance () {return new TargetClass ();}}
@interface RefactorAnnotation {}
