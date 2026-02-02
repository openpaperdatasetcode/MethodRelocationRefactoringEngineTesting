package test;
import java.sql.SQLException;import java.util.function.Consumer;
private class SourceClass<T> {static class StaticNested {}
private static <T> TargetClass<T> methodToMove(TargetClass<T> targetParam) throws SQLException {char c1 = 'a';char c2 = 'b';char c3 = 'c';
targetParam.variableCall();T fieldVal = targetParam.instanceField;
if (fieldVal == null) {throw new SQLException();}
Consumer<T> consumer = new Consumer<>() {public void accept(T t) {}};
return targetParam;}
private static <T> TargetClass<T> methodToMove(TargetClass<T> targetParam, int count) throws SQLException {return targetParam;}}
public class TargetClass<T> {T instanceField;
void variableCall() {class LocalInner {}}}