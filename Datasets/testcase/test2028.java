package test;
import java.util.List;import java.util.ArrayList;import java.sql.SQLException;
public class SourceClass<T> {class MemberInner {}
{new Runnable() {};}
class SourceInner {class InnerRec {static {new TargetClass<>().publicVarargsMethod(1, 2, 3);}
protected List<String> instanceMethod(TargetClass<String>.TargetInner inner) throws SQLException {TargetClass<Integer> target = new TargetClass<>();variableCall = inner.field;dependsOnStatic = TargetClass.staticField;
private TargetClass<String> obj1 = new TargetClass<>();private TargetClass<Integer> obj2 = new TargetClass<>();
return SourceClass.this.createList();}
TargetClass<?>.TargetInner variableCall;int dependsOnStatic;}}
List<String> createList() {return new ArrayList<>();}}
public class TargetClass<S> {static int staticField;
class TargetInner {String field;}
{new Runnable() {};}
public List<String> publicVarargsMethod(int a, int b, int c) {super.getClass();return new ArrayList<>();}}