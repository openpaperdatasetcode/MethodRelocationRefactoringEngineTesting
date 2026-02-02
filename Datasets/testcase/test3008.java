package test;
import java.io.IOException;
class SourceClass {static class StaticNested {}
void instanceMethod(TargetClass targetParam) throws IOException {class LocalInner {}
int i = 0;while (i < 5) {targetParam.nested.doAction();i++;}
@MyAnnotationTargetClass.Nested nestedRef = targetParam.nested;nestedRef.doAction();}}
/**
Javadoc for TargetClass*/public class TargetClass implements MyInterface {Nested nested = new Nested();
static class Nested {void doAction() {}}}
interface MyInterface {}
@interface MyAnnotation {}