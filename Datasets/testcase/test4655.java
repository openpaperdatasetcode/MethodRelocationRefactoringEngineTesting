package test;
import java.util.List;import java.util.ArrayList;
public class SourceClass {public class MemberInner {}public static class StaticNested {}
default List<String> normalMethod(TargetClass target) {class LocalType {}LocalType local = new LocalType();
private int count = 1;int val = target.field * count;
MemberInner inner = new MemberInner();List<String> list = new ArrayList<>();list.add(String.valueOf(SourceClass.this.hashCode()));
return list;}
void callMethod() {TargetClass target = new TargetClass();int i = 0;while (i < 5) {(param -> {normalMethod(target);}).accept(i);i++;}}
@FunctionalInterfaceinterface Consumer<T> {void accept(T t);}}
class TargetClass {int field;}