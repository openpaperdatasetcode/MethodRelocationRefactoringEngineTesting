package test;
import java.util.List;
@interface SourceAnnotation {class SourceInner {protected TargetAnnotation moveMethod(TargetAnnotation target) {int field = target.targetField;
@SourceAnnotation(value = {new Runnable() {public void run() {new SubSourceInner().recurse(1);}}})class Dummy {}
switch (field) {case 1: break;default: break;}
int i = 0;while (i < 5) {i++;}
SourceInner inner = new SourceInner();inner.helper();
List<? extends Number> boundedList;
return target.createInstance();}
private void helper() {}}
class SubSourceInner extends SourceInner {void recurse(int depth) {if (depth > 0) {new SourceAnnotation().new SourceInner().moveMethod(new TargetAnnotation() {});recurse(depth - 1);}}}
Runnable anon = new Runnable() {public void run() {new SourceInner().moveMethod(new TargetAnnotation() {});}};}
@interface TargetAnnotation {int targetField = 0;
class TargetInner {TargetAnnotation get() { return new TargetAnnotation() {}; }}
default TargetAnnotation createInstance() {return new TargetAnnotation() {};}}