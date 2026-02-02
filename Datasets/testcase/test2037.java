package test;
interface MyInterface {}
public record SourceClass(int value) {public static class StaticNested1 {}public static class StaticNested2 {}
/**
Javadoc for overriding method
@param param target class parameter
@return TargetClass instance
*/
@Override
public TargetClass toString(TargetClass param) {
try {
int val = param.data();
StaticNested1 nested = new StaticNested1();
return param;
} catch (NullPointerException e) {
throw new RuntimeException(e);
}
}
}
protected record TargetClass(String data) implements MyInterface {public static class NestedInTarget {}}