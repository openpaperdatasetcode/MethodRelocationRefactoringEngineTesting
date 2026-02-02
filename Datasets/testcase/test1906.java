package test;
protected class SourceClass {static class StaticNested {}
@MyAnnotation(methodCall = "new OthersClass().createTarget().method()")public TargetClass method(TargetClass targetParam) throws Exception {class LocalInner {TargetClass getTarget() {return targetParam;}}
synchronized (targetParam.field) {targetParam.field = 1;}
OthersClass others = new OthersClass();TargetClass result = others.new Inner().getTargetInstance(targetParam);
return new TargetClass() {{super();}};}}
protected class TargetClass {Object field;
TargetClass() {}
void createAnonymous() {Runnable r = new Runnable() {public void run() {System.out.println(field);}};}}
class OthersClass {class Inner {TargetClass getTargetInstance(TargetClass param) {return param;}}
TargetClass createTarget() {return new TargetClass();}}
@interface MyAnnotation {String methodCall();}