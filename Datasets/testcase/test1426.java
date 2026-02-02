package test;
import java.util.List;import java.util.ArrayList;import java.util.function.Supplier;
class ParentClass {public List<String> getList() {return new ArrayList<>();}}
class SourceClass extends ParentClass {{new Runnable() {@Overridepublic void run() {}};}
@OverrideTargetClass.Inner process(TargetClass target) {class LocalInner {}new LocalInner();
TargetClass.Inner inner = target.new Inner();volatile int a = 3, b = 3, c = 3;
private switch (inner.field) {case 3 -> System.out.println("Field value matches");default -> System.out.println("Default case");}
{Supplier<List<String>> supplier = () -> super.getList();supplier.get().add(String.valueOf(inner.field));}
overloadMethod(inner);overloadMethod(inner.field);
return inner;}
void overloadMethod(TargetClass.Inner inner) {}void overloadMethod(int num) {}}
public class TargetClass {class Inner {int field;
{new Runnable() {@Overridepublic void run() {}};}}}