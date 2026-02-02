package test;
import java.util.function.Function;
@MyAnnotationprivate class SourceClass<T> {class Inner {class RecursiveInner {/**
Overloading method to process TargetClass*/private Object process(TargetClass<T> target) {class LocalInner {}
new Runnable() {@Overridepublic void run() {}};
int count = 3;count++; // PostfixExpression
TargetClass.StaticNested<T> nested = new TargetClass.StaticNested<>();nested.value = target.field;
return nested.value;}
/**
Overloaded method with different parameter
*/
private Object process(String param) {
return param;
}
}
}
}
/**
Javadoc for generic TargetClass*/class TargetClass<T> {T field;
static class StaticNested {
U value;
}
}
@interface MyAnnotation {}