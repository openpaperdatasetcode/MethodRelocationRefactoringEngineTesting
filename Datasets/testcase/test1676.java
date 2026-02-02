package test;
import java.sql.SQLException;import java.lang.reflect.Method;
final class SourceClass {class Inner {class InnerRec {private TargetClass varargsMethod(TargetClass... params) throws SQLException {if (params.length == 0) {throw new IllegalArgumentException();}
TargetClass.StaticNested nested = new TargetClass.StaticNested();variableCall();
try {Method method = InnerRec.class.getMethod("varargsMethod", TargetClass[].class);} catch (NoSuchMethodException e) {}
params[0] = new TargetClass();return params[0];}
private void variableCall() {}}}}
public class TargetClass {static class StaticNested {}}