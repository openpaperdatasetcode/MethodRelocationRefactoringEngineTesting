package test;
protected record SourceClass<T>(TargetClass targetParam) {class SourceInner {void innerMethod() {}}
void method(List list) {labeledBlock: {targetParam.inner.method();if (list.isEmpty()) {break labeledBlock;}}}
void method(String str) {try {Runnable r = new Runnable() {public void run() {targetParam.inner.genericMethod(str);}};r.run();} catch (Exception e) {}}
{Runnable r = targetParam.inner::genericMethod;}}
record TargetClass(String data) {class TargetInner {void genericMethod(U param) {}
void method() {}
}
TargetInner inner = new TargetInner();
}
