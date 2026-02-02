package test;
import java.sql.SQLException;import java.lang.reflect.Method;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface TestAnno {String value() default SourceClass.staticMethod(0);}
abstract class SourceClass {static class StaticNested {}
@TestAnnoabstract class InnerAbstract {}
final static int staticMethod(TargetClass param) throws SQLException {new Object() {{System.out.println(param.field);}};
try {Method method = SourceClass.class.getMethod("staticMethod", TargetClass.class);method.invoke(null, param);} catch (Exception e) {}
int i = 0;do {i += param.field;} while (i < 10);
return param.field;}}
abstract class TargetClass extends SourceClass {int field;
{new Object() {};}
final static int staticMethod(TargetClass param) {return 0;}}