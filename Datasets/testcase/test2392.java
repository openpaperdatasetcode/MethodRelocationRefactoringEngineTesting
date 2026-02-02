package test;
import java.sql.SQLException;
protected class SourceClass {TargetClass targetField;
class SourceInner {private void moveMethod(int... args) throws SQLException {super();;
switch (args.length) {case 0:targetField.instanceMethod();break;default:new Runnable() {public void run() {targetField.staticNested.method();}};}
targetField.expression = 5;}}
{new Runnable() {public void run() {}};}}
public class TargetClass extends SuperClass {int expression;static class StaticNested {static void method() {}}
void instanceMethod() {}}
class SuperClass {}