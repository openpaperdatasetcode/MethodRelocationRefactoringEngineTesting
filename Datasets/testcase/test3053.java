package test;
import java.util.List;
protected class TargetClass implements Runnable {int targetField;public TargetClass() {Runnable r = new Runnable() {public void run() {}};}public void run() {}}
protected class SourceClass implements Runnable {class MemberInner {}
public void run() {class LocalInner {}}
class SourceInner {@MyAnnotationfinal void methodToMove() {private class NestedType {void process(TargetClass target) {this.field = target.targetField;target.targetField = 3;}int field;}
TargetClass target = new TargetClass();NestedType nested = new NestedType();nested.process(target);
MemberInner inner = new MemberInner();List rawList = null;rawList.add(inner);}}}
@interface MyAnnotation {}