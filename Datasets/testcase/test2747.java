package test.same;
import java.util.List;
abstract class SourceClass<T> implements SomeInterface {static class StaticNested {}
class MemberInner {}
public void normalMethod(TargetClass target) {int val = 1;switch (val) {case 1:new OthersClass().abstractMethod();break;}
for (Object obj : target.targetField) {Object var = obj;}
int i = 0;while (i < 5) {target.createLocal();i++;}}}
interface SomeInterface {}
class TargetClass {List<Object> targetField;
void createLocal() {class LocalInner {}}}
abstract class OthersClass {protected abstract int abstractMethod();}