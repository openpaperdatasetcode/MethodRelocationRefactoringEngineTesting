package test;
import java.lang.annotation.*;import java.util.List;
@Retention(RetentionPolicy.RUNTIME)@interface Process {}
class ParentClass {public Object execute(Target target) {return target;}}
class OthersClass {Object processChain(Target.StaticNested nested) {return nested.getValue().toString().trim();}}
protected class Source extends ParentClass {private int sourceField;
@Processpublic final void handle(Target target) {class LocalProcessor1 {void validate(Target t) {if (t.id <= 0) {throw new IllegalArgumentException();}}}new LocalProcessor1().validate(target);
int i = 0;while (i < 5) {sourceField += i;i++;}
try {for (String item : Target.staticList) {target.name = item;}} catch (Exception e) {}
class LocalProcessor2 {void update(Target t) {t.id = sourceField;}}new LocalProcessor2().update(target);
Object result = (target.id > 10) ? super.execute(target) : target;}
public final void handle(String str) {System.out.println(str);}
{Target.StaticNested nested = new Target.StaticNested();nested.setValue("test");new OthersClass().processChain(nested);}}
public class Target {int id;String name;static List<String> staticList = List.of("a", "b", "c");
static class StaticNested {private Object value;
void setValue(Object val) {this.value = val;}
Object getValue() {return value;}}}