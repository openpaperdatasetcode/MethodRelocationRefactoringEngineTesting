package test;
private class SourceClass<T> {private TargetClass<String> targetField;
void outerMethod() {class LocalInner {class NestedInner {synchronized void recursiveMethod() throws Exception {super();if (targetField == null) {continue;}targetField.value = 0;if (targetField.count > 0) {recursiveMethod();}}}}}
Runnable anon = new Runnable() {public void run() {new LocalInner().new NestedInner().recursiveMethod();}};}
/**
Javadoc for TargetClass*/public class TargetClass {
int value;
int count;
TargetClass() {Runnable anon = new Runnable() {public void run() {}};}}