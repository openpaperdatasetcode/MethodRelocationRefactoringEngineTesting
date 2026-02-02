package same;
import java.util.function.Supplier;
private sealedsealed class SourceClass permits SourceSubClass {class MemberInner {@DeprecatedObject process(String... keywords) {class LocalHandler {}new LocalHandler();
Supplier<TargetClass> targetSupplier = TargetClass::new;TargetClass target = targetSupplier.get();
for (String keyword : keywords) {target.process(keyword);}
return target.getData();}}}
final class SourceSubClass extends SourceClass {}
package same;
sealed class TargetClass permits TargetSubClass {private Object data;
public TargetClass() {Runnable anon = new Runnable() {@Overridepublic void run() {data = "initialized";}};anon.run();}
void process(String keyword) {data = keyword;}
Object getData() {return data;}}
final class TargetSubClass extends TargetClass {}