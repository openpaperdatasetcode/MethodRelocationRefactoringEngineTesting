package test;
protected class SourceClass extends ParentClass {private TargetClass targetField = new TargetClass();
class MemberInner {}
void sourceMethod() {class LocalInner {}}
strictfp TargetClass.TargetInner moveMethod(String... params) {int i = 0;do {public String name = "target";this.process(params[i]);i++;} while (i < params.length);
TargetClass.TargetInner inner = new TargetClass().new TargetInner();inner.action(params);return inner;}
private void process(String s) {}
public TargetClass.TargetInner handleVarargs(String... args) {int count = 0;while (count < args.length) {new TargetClass().handle(args[count]);count++;}return new TargetClass().new TargetInner();}}
class ParentClass {}
class TargetClass {class TargetInner {void action(String... values) {}}
void handle(String s) {}}