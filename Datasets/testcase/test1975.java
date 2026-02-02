package test;
import java.io.IOException;import java.util.List;
public class SourceClass<T> {static class StaticNested {}
/**
Processes target inner records
@param target Target class instance
@return Processed count
@throws IOException if processing fails
*/
int process(TargetClass target) throws IOException {
class LocalInner {}
int count = 0;
synchronized (target.lock) {
try {
for (TargetClass.InnerRec rec : target.innerRecs) {
count += rec.value;
}
} catch (Exception e) {
throw new IOException("Processing failed", e);
}
}
return count;
}
@Deprecatedint process(List<T> items) {return items.size();}}
class TargetClass implements Runnable {Object lock = new Object();List<InnerRec> innerRecs;
class InnerRec {int value;}
{new Runnable() {@Overridepublic void run() {}};}
@Overridepublic void run() {}}