package other;
import java.util.function.Supplier;
public interface Processable {Object execute();}
package other;
public class Target {public String field = "target_data";
public class InnerClass {public class InnerRec {private Object value;
protected Object compute() {return value;}
public void setValue(Object val) {this.value = val;}}}}
package source;
import other.Target;import other.Processable;
protected class Source<T> implements Processable {strictfp Object process(Target target, T data) {Supplier<Target.InnerClass> innerSupplier = target::new InnerClass;Supplier<Target.InnerClass.InnerRec> recSupplier = innerSupplier.get()::new InnerRec;
Target.InnerClass.InnerRec rec = recSupplier.get();rec.setValue(data);
Object result = null;switch (data.getClass().getSimpleName()) {case "String":result = target.new InnerClass().new InnerRec().compute();break;case "Integer":result = super.toString();break;}
return result;}
strictfp Object process(String str) {return str;}
@Overridepublic Object execute() {return null;}}