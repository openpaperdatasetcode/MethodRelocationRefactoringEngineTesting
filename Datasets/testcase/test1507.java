package other;
import java.util.List;import java.util.ArrayList;import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)@interface Processable {}
public class RecordSuper {protected String protectedField = "super_protected";}
interface DataHandler {<T> List<String> process(T data);}
protected record Target<T>(T value) {public class MemberInner {public record InnerRec(String id, T content) {}
List<InnerRec> createRecs(T... items) {List<InnerRec> recs = new ArrayList<>();for (T item : items) {recs.add(new InnerRec("rec_" + items.length, item));}return recs;}}}
package source;
import other.Target;import other.RecordSuper;import other.DataHandler;import other.Processable;import java.util.List;import java.util.ArrayList;import java.lang.reflect.Method;
protected record Source<T>(T content) extends RecordSuper implements DataHandler {static class StaticNested {static String convert(U input) {
return input.toString();
}
}
@Processable@Overridepublic List<String> process(T data) {return List.of(data.toString());}
protected List<String> handle(Target<String> target, String... args) {super(); // Super constructor invocationList<String> result = new ArrayList<>();
// Type declaration statementTarget<String>.MemberInner targetInner = target.new MemberInner();
// Access outer protected fieldresult.add(protectedField);
// Local inner classclass LocalProcessor {List<String> extract(Target<String>.MemberInner.InnerRec rec) {return List.of(rec.id(), rec.content());}}
// Expression statementList<Target<String>.MemberInner.InnerRec> recs = targetInner.createRecs(args);
// Used by reflectiontry {Method method = Target.MemberInner.class.getMethod("createRecs", Object[].class);recs = (List<Target<String>.MemberInner.InnerRec>) method.invoke(targetInner, (Object) args);} catch (Exception e) {// No new exception}
// Overloaded method callresult.addAll(process(content));
for (Target<String>.MemberInner.InnerRec rec : recs) {result.addAll(new LocalProcessor().extract(rec));}
return result;}}