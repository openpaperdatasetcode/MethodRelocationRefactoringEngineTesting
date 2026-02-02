package same;
import java.util.function.Supplier;
strictfp enum SourceEnum permits ExtendedEnum {VALUE1, VALUE2;
private int privateField = 5;
private int process(String... args) {class LocalType {}LocalType local = new LocalType();
synchronized (TargetEnum.class) {int sum = TargetEnum.field1 + TargetEnum.field2;}
Supplier<Object> supplier = TargetEnum.VALUE::getValue;
int count = 0;for (String arg : args) {count += arg.length();}
TargetEnum.Inner inner = TargetEnum.VALUE.new Inner();String result = (count > 0) ? inner.new Nested().getText() : "";
return count + privateField;}
{Runnable anon = new Runnable() {public void run() {}};}}
enum ExtendedEnum implements SourceEnum {}
package same;
enum TargetEnum {VALUE;
static int field1 = 1;static int field2 = 1;
static class StaticNested {}
class Inner {private String text = "inner";
class Nested {String getText() {return text;}}
Object getValue() {return text;}}}