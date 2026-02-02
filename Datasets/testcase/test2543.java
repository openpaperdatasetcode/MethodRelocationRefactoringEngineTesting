package same;
import java.lang.reflect.Field;import java.util.List;
private enum SourceEnum implements Runnable {VALUE(10);
private final int value;
SourceEnum(int value) {this.value = value;}
class MemberInner {}
private int process() {class LocalProcessor {}new LocalProcessor();
TargetEnum target = TargetEnum.INSTANCE;int result = target.dataField;
Runnable runnable = () -> System.out.println(this.value);
try {Field field = TargetEnum.class.getField("dataField");result += (int) field.get(target);} catch (Exception e) {}
try {List<String> data = SubClass.getInstance().fetch().filter().collect();result += data.size();} catch (Exception e) {throw new RuntimeException(e);}
return result;}
@Overridepublic void run() {}}
class SubClass {private static SubClass instance;
static SubClass getInstance() {if (instance == null) {instance = new SubClass();}return instance;}
protected SubClass fetch() {return this;}
protected SubClass filter() {return this;}
protected List<String> collect() {return List.of();}}
package same;
private enum TargetEnum {INSTANCE;
int dataField = 5;
TargetEnum() {class LocalInitializer {LocalInitializer() {dataField = 5;}}new LocalInitializer();}}